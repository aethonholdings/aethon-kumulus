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
   void execute(Properties p)
           throws Exception;
}
