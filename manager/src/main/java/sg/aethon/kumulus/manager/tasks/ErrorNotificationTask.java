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
public class ErrorNotificationTask extends NotificationTask
{
    public ErrorNotificationTask()
    {
        super(Status.ERROR,
                "An error has occured",
                "Please fix as soon as possible!",
                true);
    }
}