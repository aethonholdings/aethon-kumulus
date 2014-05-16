<!--
  Capture user homepage
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
              <span class="kumulus-widget-header-title">${scanCount} scans to be assembled into documents</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task">
              <table class='pure-table pure-table-horizontal'>
                <thead>
                  <th width="80%">Project name</th>
                  <th width="20%">Scans</th>
                </thead>
                <tbody>
                  <g:each var="project" in="${userTasks}">
                    <tr>
                      <td><g:link controller="capture" action="build" id="${project.key.id}">${project.key.projectName}</g:link></td>
                      <td>${project.value.size()}</td>
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
              <span class="kumulus-widget-header-title">Upload scans manually</span>
            </div>
            <table class='pure-table pure-table-horizontal'>
              <thead>
                <tr>
                  <th>Project Name</th>
                  <th>Customer</th>
                </tr>
              </thead>
              <tbody>     
                <g:each var="project" in="${projectList}">
                  <tr>
                    <td><g:link controller="capture" action="upload" id="${project.id}">${project.projectName}</g:link></td>
                    <td>${project.client.name}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>  
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
