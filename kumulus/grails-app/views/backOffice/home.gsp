<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<html>
  <head>
    <title>Home | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">                
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have ${userTasks.count} tasks outstanding in your task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue taskQueue="${userTasks}"/></div>
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container">        
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">There are ${backOfficeTasks.count} tasks outstanding in the Kumulus task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue taskQueue="${backOfficeTasks}"/></div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
