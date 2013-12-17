/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;
import sg.aethon.kumulus.manager.Commons;
import sg.aethon.kumulus.manager.Commons.Transaction;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author theo
 */
public class CreateNewTask implements Task
{
    private final Log log = LogFactory.getLog(CreateNewTask.class);

    @Override
    public int getPeriod(Properties p) {
        return p.task_period;
    }

    @Override
    public void execute(Properties p) throws Exception {
        JdbcTemplate conn = Commons.getKumulusConnection(p);
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
            else
            {
                log.info("Configuration found, processing...");
            }
            Integer project_id = Integer.valueOf(Integer.parseInt(project.get("project_id").toString()));
            try (Transaction trans = Commons.createTransaction(p, conn))
            {
                conn.execute("DROP TEMPORARY TABLE selection IF EXISTS");
                /* mark our territory: notice the FOR UPDATE clause */
                conn.execute("CREATE TEMPORARY TABLE selection "+
                             "(select node_id from nodes where status=1 and project_id=? FOR UPDATE)");
                int size = conn.queryForObject("select count(*) from selection", Integer.class);
                if (size > 0)
                {
                    int user_id = conn.queryForObject("select top 1 last_update_id from nodes "+
                                                      "inner join selection on selection.node_id=nodes.node_id "+
                                                      "where node_id in (?)", Integer.class);
                    /* finalized nodes exists, so create a new task */
                    SimpleJdbcInsert ins = new SimpleJdbcInsert(conn).withTableName("tasks").usingGeneratedKeyColumns("id");
                    Map<String, Object> parameters = new HashMap<>();
                    Timestamp now = Commons.now(p);
                    parameters.put("created", now);
                    parameters.put("last_update", now);
                    parameters.put("status", Status.READY_FOR_UPLOAD.code);
                    parameters.put("user_id", user_id);
                    parameters.put("project_id", project_id);
                    int task_id = ins.executeAndReturnKey(parameters).intValue();
                    conn.update("insert into task_nodes (nodes_id, task_id) "+
                                "select node_is, ? "+
                                "from selection", new Object[]{task_id});
                    conn.update("update nodes set last_update_datetime=now(), last_update_id=null, status=2 "+
                                "where node_id in (select node_id from selection)");
                    conn.execute("DROP TEMPORARY TABLE selection");
                    /* commit and release locks */
                    trans.success();
                }
            }
        }
    }
    
}
