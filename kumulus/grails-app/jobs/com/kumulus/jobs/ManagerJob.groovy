/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.jobs

import com.abbyy.ocrsdk.Client

/**
 *
 * @author theo
 */
class ManagerJob {
  static triggers = {
    simple name: 'Manager Job', startDelay: 0, repeatInterval: 120000  
  }
  def group = "Manager Jobs"
  def execute(){
    print "Job run!"
  }
}
