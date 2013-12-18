/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
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
        List<Map<String, Object>> tasks = 
                conn.queryForList("select task.id, task.project_id, project_name "+
                                  "from task inner join project "+
                                  "on task.project_id=project.project_id "+
                                  "where task.status=?", new Object[]{Status.READY_FOR_UPLOAD.code});
        for (Map<String, Object> task : tasks)
        {
            final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("id").toString()));
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
                    createDirIfNotPresent(sftp, p3.stage_path, task_id.toString());
                    String dest_path = p3.stage_path + "/" + task_id;
                    /* upload documents */
                    List<Map<String, Object>> docs = 
                        conn.queryForList("select hierarchy, node_id, name, comment from nodes "+
                                          "where status=2 and type='D' and uploaded=False "+
                                          "and project_id=?", new Object[]{project_id});
                    for (Map<String, Object> doc: docs)
                    {
                        String hier = doc.get("hierarchy").toString();
                        String path = hier.substring(1, hier.length()-1).replaceAll(", ", "-");
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
            conn.update("update task set status=? where id=?", Status.READY_FOR_BATCH_INSTANCE.code, task_id);
            log.info("Finished task " + task_id);
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
                conn.queryForList("select actual_image_name, name, node_id, uploaded from nodes "+
                                  "where parent_node_id=? and uploaded=False "+
                                  "order by document_sequence_number", new Object[]{doc_node_id});
        for (Map<String, Object> map: filenames)
        {
            String fullname = map.get("actual_image_name").toString().replace('\\', '/');
            String filename = (String) map.get("name");
            long page_node_id = (long) map.get("node_id");
            String src = fsroot + fullname;
            String dest = sftp_path + "-" + filename;
            log.info(src + " --> " + dest);
            sftp.put(src, dest, ChannelSftp.OVERWRITE);
            conn.update("update nodes set last_update_datetime=now(), last_update_id=null, "+
                        "uploaded=True where node_id=?", new Object[]{page_node_id});
        }
        conn.update("update nodes set last_update_datetime=now(), last_update_id=null, "+
                    "uploaded=True where node_id=?", new Object[]{doc_node_id});
    }
    
    public void uploadFileFromString(ChannelSftp sftp, String path, String data, String name)
            throws Exception
    {
        sftp.put(new ByteArrayInputStream(data.getBytes("UTF-8")), path+"/"+name, ChannelSftp.OVERWRITE);
    }
    
}
