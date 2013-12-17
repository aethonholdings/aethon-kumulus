/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager;

/**
 *
 * @author theo
 */

public interface Task
{
    enum Status
    {
        ERROR(-1),
        READY_FOR_UPLOAD(1),
        READY_FOR_BATCH_INSTANCE(2),
        CREATED(3),
        READY_FOR_REVIEW(4),
        READY_FOR_VALIDATION(5),
        FINISHED(6);
        
        public final int code;
        Status(int code) { this.code = code; }
    }
    
    int getPeriod(Properties p);
   
    void execute(Properties p) 
            throws Exception;
}
