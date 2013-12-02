package syncservice;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Simple service class that writes a
 * message to standard out every minute.
 */
public class SyncService {

   /**
    * Single static instance of the service class
    */
   private static SyncService serviceInstance = new SyncService();
   
   private Log log = LogFactory.getLog(SyncService.class);
	
   /**
    * Static method called by prunsrv to start/stop
    * the service.  Pass the argument "start"
    * to start the service, and pass "stop" to
    * stop the service.
    */
   public static void windowsService(String args[])
   {
        String cmd = "start";
        if(args.length > 0)
        {
           cmd = args[0];
        }

        if("start".equals(cmd))
        {
           serviceInstance.start();
        }
        else
        {
           serviceInstance.stop();
        }
   }

   /**
    * Flag to know if this service
    * instance has been stopped.
    */
   private volatile boolean stopped = false;
	
	
   /**
    * Start this service instance
    */
   public void start() 
   {
      stopped = false;
      log.info("My Service Started " + new java.util.Date());
      while (!stopped)
      {
          try
          {
            Properties p = new Properties();
            log.info("My Service Executing " + new java.util.Date());
            task(p);
          }
          catch (Exception e)
          { 
              e.printStackTrace();
              log.error(e);
          }
          finally
          {
             synchronized(this)
             {
                try { this.wait(30000); }
                catch(InterruptedException ie) { }
             }
          }
      }
      log.info("My Service Finished " + new java.util.Date());
   }
	
   /**
    * Stop this service instance
    */
   public void stop()
   {
      stopped = true;
      synchronized(this)
      {
         this.notify();
      }
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        serviceInstance.start();
    }
    
    public void task(final Properties p) throws Exception
    {
        JdbcTemplate conn = getConnection(p);
        /* iterate per project */
        List<Map<String, Object>> projects = 
                conn.queryForList("select project_id, project_name from project where status='A'");
        for (Map<String, Object> project : projects)
        {
            final String project_name = (String) project.get("project_name");
            log.info("Working on project "+project_name);
            Properties.PerProjectProperties p3 = p.get(project_name);
            if (p3 == null)
            {
                log.info("Not configuration for that project, ignoring...");
                continue;
            }
            Session ssh = getSSH(p, p3);
            try
            {
                ChannelSftp sftp = (ChannelSftp) ssh.openChannel("sftp");
                sftp.connect();
                try
                {
                    Integer project_id = Integer.valueOf(Integer.parseInt(project.get("project_id").toString()));
                    /* mark our territory */
                    conn.update("update nodes set last_update_datetime=now(), last_update_id=null, status=2 where status=1 and project_id=?",
                                new Object[]{project_id});
                    /* create remote directories */
                    List<String> dirs = 
                        conn.queryForList("select distinct hierarchy from nodes where status=2 and type='D' "+
                                          "and uploaded=False and project_id=?",
                                          String.class, new Object[]{project_id});
                    List<String> pdirs = new ArrayList<>();
                    for (String dir: dirs)
                    {
                        dir = dir.substring(1, dir.length()-1).replaceAll(", ", "/");
                        pdirs.add(dir);
                    }
                    String dest_path = p.get(project_name).dest_path;
                    createDirectories(sftp, dest_path, pdirs);
                    /* upload documents */
                    List<Map<String, Object>> docs = 
                        conn.queryForList("select hierarchy, node_id, name, comment from nodes where status=2 and type='D' "+
                                          "and uploaded=False and project_id=?", new Object[]{project_id});
                    for (Map<String, Object> doc: docs)
                    {
                        String hierarchy = doc.get("hierarchy").toString();
                        String path = hierarchy.substring(1, hierarchy.length()-1).replaceAll(", ", "/");
                        Integer node_id = Integer.valueOf(Integer.parseInt(doc.get("node_id").toString()));
                        uploadDocument(sftp, conn, p.fs_root, dest_path+"/"+path, node_id, doc.get("name").toString(), doc.get("comment").toString());
                    }
                }
                finally
                {
                    sftp.exit();
                }
            }
            finally
            {
                ssh.disconnect();
            }
        }
    }
    
    public void createDirectories(ChannelSftp sftp, String path, List<String> dirs)
            throws Exception
    {
        /* create a tree of hashmaps */
        Map root = new HashMap();
        for (String dir: dirs)
        {
            Map current = root;
            String[] comps = dir.split("/");
            for (String comp: comps)
            {
                if (!current.containsKey(comp))
                {
                    current.put(comp, new HashMap());
                }
                current = (Map) current.get(comp);
            }
        }
        
        /* traverse it to create the directories */
        traverse(sftp, path, root);
    }

    public void traverse(ChannelSftp sftp, String path, Map current)
            throws Exception
    {
        for (Object d: current.keySet())
        {
            String dir = (String) d;
            createDirIfNotPresent(sftp, path, dir);
            traverse(sftp, path+"/"+dir, (Map) current.get(d));
        }
    }

    
    public void createDirIfNotPresent(ChannelSftp sftp, String path, final String dir)
            throws Exception
    {
        class MutableBoolean
        {
            boolean b = false;
        }
        final MutableBoolean exists = new MutableBoolean();
        
        sftp.ls(path, 
              new ChannelSftp.LsEntrySelector() {
                @Override
                public int select(ChannelSftp.LsEntry le) {
                    /* TODO: check if it is not a directory */
                    if (le.getFilename().equals(dir))
                    {
                        exists.b = true;
                        return ChannelSftp.LsEntrySelector.BREAK;
                    }
                    else
                    {
                        return ChannelSftp.LsEntrySelector.CONTINUE;
                    }
                }
              });
        
        if (!exists.b)
        {
            sftp.mkdir(path+"/"+dir);
        }
    }
    
    public void uploadDocument(ChannelSftp sftp, JdbcTemplate conn, String fsroot, String sftp_path, Integer doc_node_id, String doc_name, String doc_comment)
            throws Exception
    {
        List<Map<String, Object>> filenames =
                conn.queryForList("select actual_image_name, node_id, uploaded from nodes where parent_node_id=? "+
                                  "order by document_sequence_number", new Object[]{doc_node_id});
        int i = 1;
        for (Map<String, Object> map: filenames)
        {
            boolean uploaded = (boolean) map.get("uploaded");
            if (!uploaded)
            {
                String filename = (String) map.get("actual_image_name");
                filename = filename.replace('\\', '/');
                int page_node_id = (int) map.get("node_id");
                log.info(fsroot+filename+" --> "+sftp_path+"/."+i);
                sftp.put(fsroot+filename, sftp_path+"/."+i, ChannelSftp.OVERWRITE);
                conn.update("update nodes set last_update_datetime=now(), last_update_id=null, uploaded=True where node_id=?", new Object[]{page_node_id});
            }
            i++;
        }
        /* put metadata */
        uploadFileFromString(sftp, sftp_path, doc_comment, "comment");
        /* put an empty file to signify end of upload */
        uploadFileFromString(sftp, sftp_path, "", "0");
        /* document has been uploaded */
        conn.update("update nodes set last_update_datetime=now(), last_update_id=null, uploaded=True where node_id=?", new Object[]{doc_node_id});
    }
    
    public void uploadFileFromString(ChannelSftp sftp, String path, String data, String name)
            throws Exception
    {
        sftp.put(new ByteArrayInputStream(data.getBytes("UTF-8")), path+"/."+name, ChannelSftp.OVERWRITE);
    }
    
    public Session getSSH(Properties p, Properties.PerProjectProperties p3) throws Exception
    {
        JSch.setConfig("StrictHostKeyChecking", "yes");
        JSch jsch = new JSch();
        jsch.setKnownHosts(p.known_hosts);
        jsch.addIdentity(p3.dest_sshkey);
        Session session = jsch.getSession(p3.dest_user, p3.dest_host, p3.dest_port);
        session.connect(10000);
        return session;
    }

    public JdbcTemplate getConnection(Properties p)
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername(p.db_username);
        ds.setPassword(p.db_password);
        ds.setUrl(p.db_url);
        return new JdbcTemplate(ds);
    }
    
}
