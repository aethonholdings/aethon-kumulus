/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

/**
 *
 * @author theo
 */
public class UserCannotWorkException extends Exception
{
    final UserCannotWorkReason reason;
    public UserCannotWorkException(UserCannotWorkReason reason)
    {
        this.reason = reason;
    }
}
