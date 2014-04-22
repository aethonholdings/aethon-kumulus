<!--
  Capture user homepage
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
          <div class="kumulus-widget-base kumulus-widget-1-3 kumulus-scrollable-y">                
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have ${userTasks.count} tasks outstanding in your task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue userId="${userId}"/></div>
          </div>
          <div class="kumulus-widget-base kumulus-widget-2-3 kumulus-scrollable-y">     
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Upload scans for projects</span>
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
