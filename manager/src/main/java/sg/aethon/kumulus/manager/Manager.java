package sg.aethon.kumulus.manager;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sg.aethon.kumulus.manager.tasks.*;

public class Manager 
{
    public static void main(String[] args)
            throws Exception
    {
        Properties p = new Properties();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new SynchronizeTask());
        tasks.add(new CreateNewTask());
        tasks.add(new UploadTask());
        tasks.add(new BatchInstanceTask());
        tasks.add(new ReviewNotificationTask());
        tasks.add(new ValidationNotificationTask());
        tasks.add(new ErrorNotificationTask());
        for (Task task: tasks)
        {
            new Thread(new Executor(p, task)).start();
        }
    }
}

class Executor implements Runnable
{
    private static final Object lock = new Object();
    private final Log log = LogFactory.getLog(Task.class);
    private final Properties properties;
    private final Task task;

    public Executor(Properties p, Task t)
    {
        this.properties = p;
        this.task = t;
    }
   
    @Override
    public void run()
    {
       log.info("Service Started " + new java.util.Date());
       while (true)
       {
           try
           {
             task.execute(properties);
           }
           catch (Exception e)
           { 
               synchronized(lock) { e.printStackTrace(System.err); }
               log.error(e);
           }
           finally
           {
              synchronized(this)
              {
                 try { this.wait(task.getPeriod(properties)*1000); }
                 catch(InterruptedException ie) { }
              }
           }
       }
   }
	
}
