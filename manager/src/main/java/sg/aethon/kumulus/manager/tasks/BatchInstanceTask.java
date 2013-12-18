/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Commons;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;

class RemoteCommandException extends Exception { }

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
                                  "where task.status=? order by task.id",
                                  new Object[]{Status.READY_FOR_BATCH_INSTANCE.code});
        for (Map<String, Object> task : tasks)
        {
            final int task_id = ((Number) task.get("id")).intValue();
            final Number last_batch_instance_id = (Number) task.get("last_batch_instance_id");
            if (last_batch_instance_id != null)
            {
                /* we have already submitted a folder to Ephesoft */
                if (last_batch_instance_id.intValue()+1 == getLastBatchInstanceID(p))
                {
                    /* and the batch instance has been created */
                    conn.update("update task "+
                                "set batch_instance_id=?, last_batch_instance_id=null, status=? "+
                                "where task.id=?",
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
        final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("id").toString()));
        final String project_name = (String) task.get("project_name");
        Properties.PerProjectProperties p3 = p.get(project_name);
        String src_path = p3.eph_src_path + "\\" + task_id + "\\" + project_name;
        String dest_path = p3.eph_dest_path;
        String command = String.format("move \"%s\" \"%s\"", src_path, dest_path);
        log.info(command);
        execute(Commons.getSSH(p), command);
        conn.update("update task set last_batch_instance_id=? where id=?",
                    new Object[]{last_batch_instance_id, task_id});
        log.info("Moved files for task " + task_id);
    }
    
    private int getLastBatchInstanceID(Properties p)
    {
        JdbcTemplate conn = Commons.getEphesoftConnection(p);
        return conn.queryForObject("select max(id) from batch_instance", Integer.class);
    }
    
    private void execute(Session ssh, String command)
            throws Exception
    {
        try
        {
            ChannelExec channel = (ChannelExec) ssh.openChannel("exec");
            try
            {
                channel.setCommand(command);
                channel.setInputStream(null);
                ByteArrayOutputStream err = new ByteArrayOutputStream();
                channel.setErrStream(err);
                InputStream in = channel.getInputStream();
                channel.connect();
                byte[] tmp = new byte[1024];
                StringBuilder buf = new StringBuilder();
                while(true)
                {
                    while (in.available()>0)
                    {
                        int i = in.read(tmp, 0, 1024);
                        if (i<0)
                            break;
                        buf.append(new String(tmp, 0, i));
                    }
                    if (channel.isClosed())
                    {
                        log.info("STDOUT: " + buf.toString());
                        log.info("STDERR: " + new String(err.toByteArray(), "UTF-8"));
                        int ec = channel.getExitStatus();
                        log.info("exit-status: " + ec);
                        if (ec > 0)
                            throw new RemoteCommandException();
                        break;
                    }
                    Thread.sleep(1000);
                }
            }
            finally
            {
                channel.disconnect();
            }
        }
        finally
        {
            ssh.disconnect();
        }
    }
}
