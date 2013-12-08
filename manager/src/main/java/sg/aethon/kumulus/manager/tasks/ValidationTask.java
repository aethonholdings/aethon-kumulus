/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;
import sg.aethon.kumulus.manager.Utilities;

/**
 *
 * @author theo
 */
public class ValidationTask implements Task
{

    @Override
    public void execute(Properties p)
            throws Exception
    {
        
    }

    public JdbcTemplate getConnection(Properties p)
    {
        return Utilities.getConnection(p.eph_db_user, p.eph_db_pass, p.eph_db_url);
    }
}
