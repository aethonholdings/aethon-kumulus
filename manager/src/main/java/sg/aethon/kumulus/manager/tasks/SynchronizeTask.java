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
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.aethon.kumulus.manager.Commons;
import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;

/**
 *
 * @author theo
 */
public class SynchronizeTask implements Task
{
    private final Map<String, Status> map = new HashMap<>();
    private Timestamp ts;
    
    public SynchronizeTask()
    {
        for (Status status:
                new Status[] {Status.ERROR, Status.READY_FOR_REVIEW,
                              Status.READY_FOR_VALIDATION, Status.FINISHED})
        {
            map.put(status.eph_name, status);
        }
    }
    
    @Override
    public int getPeriod(Properties p)
    {
        return p.task_period;
    }

    @Override
    public void execute(Properties p) throws Exception
    {
        Timestamp new_ts = Commons.now(p);
        JdbcTemplate kum = Commons.getKumulusConnection(p);
        List<Integer> ids = kum.queryForList("select batch_instance_id from task "+
                                             "where status<>? and batch_instance_id is not null "+
                                             ((ts == null) ? "" : "and last_modified > ?"),
                                             Integer.class,
                                             (ts == null) ? new Object[] {Status.FINISHED.code}
                                                          : new Object[] {Status.FINISHED.code, ts});
        String in_part = StringUtils.join(ids, ",");
        JdbcTemplate eph = Commons.getEphesoftConnection(p);
        List<Map<String, Object>> rows =
            eph.queryForList("select id, batch_status from batch_instance where id in (?)",
                             new Object[] {in_part});
        for (Map<String, Object> row: rows)
        {
            int id = ((Number) row.get("id")).intValue();
            Status new_status = map.get(row.get("batch_status").toString());
            kum.update("update task set status=?, reported=null "+
                       "where status<>? and batch_instance_id=?",
                       new Object[] {new_status.code, new_status.code, id});
        }
        ts = new_ts;
    }
    
}
