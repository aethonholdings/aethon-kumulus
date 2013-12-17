/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

import sg.aethon.kumulus.manager.Properties;
import sg.aethon.kumulus.manager.Task;

/**
 *
 * @author theo
 */
public class SynchronizeTask implements Task
{

    @Override
    public int getPeriod(Properties p)
    {
        return p.task_period;
    }

    @Override
    public void execute(Properties p) throws Exception
    {
        
    }
    
}
