<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<html>
  <head>
    <title>Home | Kumulus</title>
    <g:javascript src='kumulus/allocate.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">                
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have <span id="userTaskCount">${userTasks.size()}</span> tasks outstanding in your task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task">
              <table id="userTaskList" class='pure-table pure-table-horizontal'>
                <thead>
                  <th>Document</th>
                  <th>Task</th>
                  <th>Created</th>
                </thead>
                <tbody>
                  <g:each var="task" in="${userTasks}">
                    <tr>
                      <td><g:link controller="backOffice" action="performTask" id="${task.id}">${task.document.literal}</g:link></td>
                      <td>${task.type}</td>
                      <td>${task.created.format("dd MMM yyyy hh:mm:ss")}</td>
                    </tr>
                  </g:each>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container">        
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">There are <span id="backOfficeTaskCount">${backOfficeTasks.count}</span> tasks outstanding in the Kumulus task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task">
              <table class='pure-table pure-table-horizontal'>
                <thead>
                  <th>Type</th>
                  <th>Pending</th>
                </thead>
                <tbody>
                  <g:each var="taskType" in="${backOfficeTasks.types}">
                    <tr>
                      <td>${taskType.key}</td>
                      <td class='kumulus-task-count'><span id="${taskType.key}">${taskType.value.size()}</span></td>
                    </tr>
                  </g:each>
                </tbody>
              </table>
              <p><input id="getNext" value="Get next" type="button" class="pure-button"/></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
