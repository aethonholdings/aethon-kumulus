/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;
import sg.aethon.kumulus.manager.Commons;

/**
 *
 * @author theo
 */
public class UploadTask implements Task
{
    private final Log log = LogFactory.getLog(UploadTask.class);

    @Override
    public int getPeriod(Properties p)
    {
        return p.task_period;
    }
    
    @Override
    public void execute(Properties p)
            throws Exception
    {
        JdbcTemplate conn = Commons.getKumulusConnection(p);
        /* iterate per project */
        List<Map<String, Object>> tasks = 
                conn.queryForList("select task.id, task.project_id, project_name "+
                                  "from task inner join project "+
                                  "on task.project_id=project.project_id "+
                                  "where status='?'", new Object[]{Status.READY_FOR_UPLOAD.code});
        for (Map<String, Object> task : tasks)
        {
            final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("task_id").toString()));
            final Integer project_id = Integer.valueOf(Integer.parseInt(task.get("project_id").toString()));
            final String project_name = (String) task.get("project_name");
            log.info("Working on task "+task_id);
            Properties.PerProjectProperties p3 = p.get(project_name);
            Session ssh = Commons.getSSH(p);
            try
            {
                ChannelSftp sftp = (ChannelSftp) ssh.openChannel("sftp");
                sftp.connect();
                try
                {
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
                    String dest_path = p3.stage_path;
                    createDirectories(sftp, dest_path, pdirs);
                    /* upload documents */
                    List<Map<String, Object>> docs = 
                        conn.queryForList("select hierarchy, node_id, name, comment from nodes "+
                                          "where status=2 and type='D' and uploaded=False "+
                                          "and project_id=?", new Object[]{project_id});
                    for (Map<String, Object> doc: docs)
                    {
                        String hier = doc.get("hierarchy").toString();
                        String path = hier.substring(1, hier.length()-1).replaceAll(", ", "/");
                        Integer node_id = Integer.valueOf(Integer.parseInt(doc.get("node_id").toString()));
                        uploadDocument(sftp, conn, p.fs_root, dest_path+"/"+path, node_id,
                                       doc.get("name").toString(), doc.get("comment").toString());
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
            conn.update("update tasks set status=? where id=?", Status.READY_FOR_BATCH_INSTANCE, task_id);
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
    
    public void uploadDocument(ChannelSftp sftp, JdbcTemplate conn, String fsroot, 
                               String sftp_path, Integer doc_node_id, String doc_name,
                               String doc_comment)
            throws Exception
    {
        List<Map<String, Object>> filenames =
                conn.queryForList("select actual_image_name, node_id, uploaded from nodes "+
                                  "where parent_node_id=? "+
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
                conn.update("update nodes set last_update_datetime=now(), last_update_id=null, "+
                            "uploaded=True where node_id=?", new Object[]{page_node_id});
            }
            i++;
        }
        conn.update("update nodes set last_update_datetime=now(), last_update_id=null, "+
                    "uploaded=True where node_id=?", new Object[]{doc_node_id});
    }
    
    public void uploadFileFromString(ChannelSftp sftp, String path, String data, String name)
            throws Exception
    {
        sftp.put(new ByteArrayInputStream(data.getBytes("UTF-8")), path+"/."+name, ChannelSftp.OVERWRITE);
    }
    
}
