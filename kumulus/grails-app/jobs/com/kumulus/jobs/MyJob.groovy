/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.jobs

/**
 *
 * @author theo
 */
class MyJob {
  static triggers = {
    simple name: 'mySimpleTrigger', startDelay: 60000, repeatInterval: 1000  
  }
  def group = "MyGroup"
  def execute(){
    print "Job run!"
  }
}
