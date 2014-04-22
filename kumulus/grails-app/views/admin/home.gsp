<!--
  Admin user homepage
-->

<%@ page import="java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget-1-3 kumulus-scrollable-y">                
            <div class="kumulus-widget-base kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have ${userTasks.count} tasks outstanding in your task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue taskQueue="${userTasks}"/></div>
          </div>
          <div class="kumulus-widget-base kumulus-widget-1-3 kumulus-scrollable-y">    
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">There are ${backOfficeTasks.count} tasks outstanding in the Kumulus task queue</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-task"><g:taskQueue taskQueue="${backOfficeTasks}"/></div>
          </div>
          <div class="kumulus-widget-base kumulus-widget-1-3 kumulus-scrollable-y">     
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You have ${shipmentList.size} document shipments arranged</span>
              <span class="kumulus-widget-header-action"><g:link controller="shipment" action="create">Create new</g:link></span>
            </div>
            <table class='pure-table pure-table-horizontal'>
              <thead>
                <tr>
                  <th>Scheduled Date</th>
                  <th>Items</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
              <tbody>    
                <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %> 
                <g:each var="shipment" in="${shipmentList}">
                  <tr>
                    <td><g:link controller="shipment" action="view" id="${shipment.id}">${dateFormat.format((java.util.Date)shipment.scheduled)}</g:link></td>
                    <td class="kumulus-task-count">${shipment.shipmentItems.size()}</td>
                    <td class="kumulus-task-queue-action"><g:link controller="shipment" action="remove" id="${shipment.id}">Cancel</g:link></td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You are working on ${projectList.size} projects</span>
              <span class="kumulus-widget-header-action"><g:link controller="project" action="create">Create new</g:link></span>
            </div>
            <table class='pure-table pure-table-horizontal'>
              <thead>
                <tr>
                  <th>Project Name</th>
                  <th>Client</th>
                </tr>
              </thead>
              <tbody>     
                <g:each var="project" in="${projectList}">
                  <tr>
                    <td><g:link controller="customer" action="viewProject" id="${project.id}">${project.projectName}</g:link></td>
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
