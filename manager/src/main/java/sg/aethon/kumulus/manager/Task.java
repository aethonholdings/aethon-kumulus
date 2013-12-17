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
        ERROR(-1, "ERROR"),
        READY_FOR_UPLOAD(1),
        READY_FOR_BATCH_INSTANCE(2),
        CREATED(3),
        READY_FOR_REVIEW(4, "READY_FOR_REVIEW"),
        READY_FOR_VALIDATION(5, "READY_FOR_VALIDATION"),
        FINISHED(6, "FINISHED");
        
        public final int code;
        public final String eph_name;
        Status(int code) { this(code, null); }
        Status(int code, String eph_name)
        {
            this.code = code;
            this.eph_name = eph_name;
        }
    }
    
    int getPeriod(Properties p);
   
    void execute(Properties p) 
            throws Exception;
}
