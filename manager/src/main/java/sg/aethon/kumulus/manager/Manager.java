package sg.aethon.kumulus.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sg.aethon.kumulus.manager.tasks.UploadTask;

public class Manager 
{
    public static void main(String[] args)
            throws Exception
    {
        Properties p = new Properties();
        new Thread(new Executor(p, new UploadTask())).start();
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
             log.info("Service Executing " + new java.util.Date());
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
                 try { this.wait(30000); }
                 catch(InterruptedException ie) { }
              }
           }
       }
   }
	
}
