<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1">
        <div class="kumulus-container">
          <div class="kumulus-widget-1-2 kumulus-scrollable-y">                
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have ${userTasks.count} tasks outstanding in your task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue userId="${userId}"/></div>
          </div>
          <div class="kumulus-widget-1-2 kumulus-scrollable-y">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">There are ${backOfficeTasks.count} tasks outstanding in the Kumulus task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue userId="${null}"/></div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
