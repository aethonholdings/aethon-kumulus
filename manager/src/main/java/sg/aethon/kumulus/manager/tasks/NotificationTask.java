/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

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
public abstract class NotificationTask implements Task
{
    private final Log log = LogFactory.getLog(NotificationTask.class);

    
    private final Status to_monitor;
    private final String subject;
    private final String body;
    private final boolean send_to_admin;
    
    public NotificationTask(Status to_monitor, String subject, String body,
                            boolean send_to_admin)
    {
        this.to_monitor = to_monitor;
        this.subject = subject;
        this.body = body;
        this.send_to_admin = send_to_admin;
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
        JdbcTemplate conn = Commons.getKumulusConnection(p);
        /* iterate per project */
        List<Map<String, Object>> tasks = 
                conn.queryForList("select task.id, user_email "+
                                  "from task inner join user "+
                                  "on task.user_id=user.user_id "+
                                  "where task.status=? and task.reported=false",
                                  new Object[]{to_monitor.code});
        for (Map<String, Object> task : tasks)
        {
            final Integer task_id = Integer.valueOf(Integer.parseInt(task.get("id").toString()));
            log.info("Sending email for task " + task_id);
            final String user_email = task.get("user_email").toString();
            try (Commons.Transaction tran = Commons.createTransaction(p, conn))
            {
                conn.update("update task set reported=true where id=?", new Object[]{task_id});
                Commons.sendEmail(p, send_to_admin ? p.admin_email : user_email, subject, body);
                tran.success();
            }
        }
    }
    
}
