/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

/**
 *
 * @author theo
 */
public class ReviewNotificationTask extends NotificationTask
{
    public ReviewNotificationTask()
    {
        super(Status.READY_FOR_REVIEW,
                "Pending review work",
                "Please login to your kumulus account and get it done!",
                false);
    }
    
}
