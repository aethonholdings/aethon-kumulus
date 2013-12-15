/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;
import sg.aethon.kumulus.manager.Utilities;

/**
 *
 * @author theo
 */
public abstract class NotificationTask implements Task
{
    private final Status to_monitor;
    private final String subject;
    public NotificationTask(Status to_monitor, String subject)
    {
        this.to_monitor = to_monitor;
        this.subject = subject;
    }
    
    @Override
    public int getPeriod(Properties p)
    {
        return p.task_period;
    }

    @Override
    public void execute(Properties p)
            throws Exception
    {
        JdbcTemplate conn = Utilities.getKumulusConnection(p);
        /* iterate per project */
        List<Map<String, Object>> tasks = 
                conn.queryForList("select task.id, user_email "+
                                  "from task inner join user "+
                                  "on task.user_id=user.user_id "+
                                  "where task.status='?' and task.reported=false",
                                  new Object[]{to_monitor.code});
        for (Map<String, Object> task : tasks)
        {
            final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("task_id").toString()));
            final String user_email = task.get("user_email").toString();
            try (Utilities.Transaction tran = Utilities.createTransaction(p, conn))
            {
                conn.update("update task set reported=true where task_id=?", new Object[]{task_id});
                Utilities.sendEmail(p, user_email,
                                    subject,
                                    "Please login to your kumulus account and get it done!");
                tran.success();
            }
        }
    }
    
}
