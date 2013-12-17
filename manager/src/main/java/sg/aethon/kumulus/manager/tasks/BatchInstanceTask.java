/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Commons;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;

/**
 *
 * @author theo
 */
public class BatchInstanceTask implements Task
{
    private final Log log = LogFactory.getLog(BatchInstanceTask.class);

    @Override
    public int getPeriod(Properties p)
    {
        return p.task_period;
    }

    @Override
    public void execute(Properties p) throws Exception
    {
        JdbcTemplate conn = Commons.getKumulusConnection(p);
        List<Map<String, Object>> tasks = 
                conn.queryForList("select task.id, task.project_id, project_name, last_batch_instance_id "+
                                  "from task inner join project "+
                                  "on task.project_id=project.project_id "+
                                  "where status='?' order by task.id",
                                  new Object[]{Status.READY_FOR_BATCH_INSTANCE.code});
        for (Map<String, Object> task : tasks)
        {
            final int task_id = ((Number) task.get("task_id")).intValue();
            final Number last_batch_instance_id = (Number) task.get("last_batch_instance_id");
            if (last_batch_instance_id != null)
            {
                /* we have already submitted a folder to Ephesoft */
                if (last_batch_instance_id.intValue()+1 == getLastBatchInstanceID(p))
                {
                    /* and the batch instance has been created */
                    conn.update("update task "+
                                "set batch_instance_id=?, last_batch_instance_id=null, status=? "+
                                "where task_id=?",
                                new Object[]{last_batch_instance_id.intValue()+1, Status.CREATED.code, task_id});
                }
                /* restart from the beginning */
                return;
            }
        }
        /* no folder submitted, make a new submission if a task exists */
        int last_batch_instance_id = getLastBatchInstanceID(p);
        /* take the first available: see the order by clause in the query */
        Map<String, Object> task = tasks.get(0);
        final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("task_id").toString()));
        final String project_name = (String) task.get("project_name");
        Properties.PerProjectProperties p3 = p.get(project_name);
        String src_path = p3.stage_path + "/" + task_id;
        String dest_path = p3.eph_path;
        String command = String.format("mv %s %s", src_path, dest_path);
        execute(Commons.getSSH(p), command);
        conn.update("update task set last_batch_instance_id=? where task_id=?",
                    new Object[]{last_batch_instance_id, task_id});
    }
    
    private int getLastBatchInstanceID(Properties p)
    {
        JdbcTemplate conn = Commons.getEphesoftConnection(p);
        return conn.queryForObject("select max(id) from batch_instance", Integer.class);
    }
    
    private void execute(Session ssh, String command)
            throws Exception
    {
        boolean success = false;
        try
        {
            try
            {
                ChannelExec sh = (ChannelExec) ssh.openChannel("exec");
                InputStream is = new ByteArrayInputStream((command+"\n").getBytes());
                sh.setInputStream(is);
                OutputStream os = new ByteArrayOutputStream();
                sh.setOutputStream(os);
                sh.setErrStream(os);
                sh.connect();
                try
                {
                    Thread.sleep(10*1000);
                    String out = os.toString();
                    success = (out.length() == 0);
                    log.info("Command Execution Output: " + out);
                }
                finally
                {
                    sh.disconnect();
                }
            }
            finally
            {
                ssh.disconnect();
            }
        }
        catch (Exception ex)
        {
            /* if command execution was successful, 
             * we should not throw an exception even if one is raised */
            if (!success) throw ex;
            else ex.printStackTrace();
        }
    }
    
}
