/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;
import sg.aethon.kumulus.manager.Utilities;
import sg.aethon.kumulus.manager.Utilities.Transaction;

/**
 *
 * @author theo
 */
public class ValidationTask implements Task
{
    private final Log log = LogFactory.getLog(ValidationTask.class);

    @Override
    public int getPeriod(Properties p)
    {
        return 30;
    }

    @Override
    public void execute(Properties p)
            throws Exception
    {
        JdbcTemplate conn = getConnection(p);
        try (Transaction trans = Utilities.createTransaction(p, conn))
        {
            List<String> list = conn.queryForList("select batch_name from batch_instance", String.class);
            for (String item: list)
            {
                log.info(item);
            }
            trans.success();
        }
        Utilities.sendEmail(p, p.smtp_from, Utilities.getDate(p).toString(), "Email was sent!");
    }

    public JdbcTemplate getConnection(Properties p)
    {
        return Utilities.getConnection(p.eph_db_user, p.eph_db_pass, p.eph_db_url);
    }
}
