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
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget-1-3 kumulus-scrollable-y">                
            <h3>You have ${userTasks.count} tasks outstanding</h3>
            <div class="kumulus-task"><g:taskQueue userId="${userId}"/></div>
          </div>
          <sec:ifAnyGranted roles="ROLE_PROCESS">
            <div class="kumulus-widget-1-3 kumulus-scrollable-y">
              <h3>There are ${backOfficeTasks.count} tasks outstanding in the Kumulus queue</h3>
              <div class="kumulus-task"><g:taskQueue userId="${null}"/></div>
            </div>
          </sec:ifAnyGranted>
          <sec:ifNotGranted roles="ROLE_PROCESS">
            <div class="kumulus-widget-1-3 kumulus-scrollable-y">
              <h3>There are ${backOfficeTasks.count} tasks from you in the Kumulus queue</h3>
            </div>
          </sec:ifNotGranted>
          <div class="kumulus-widget-1-3 kumulus-scrollable-y">
            <g:link controller="shipment" action="createShipment" class="kumulus-float-right kumulus-margin-top">Create new</g:link>
            <h3>You have ${shipmentList.size} document shipments arranged</h3>
            <table class='pure-table pure-table-horizontal'>
              <thead>
                <tr>
                  <th>Shipment Name</th>
                  <th>Schedule Date</th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
              <tbody>    
                <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %> 
                <g:each var="shipment" in="${shipmentList}">
                  <tr>
                    <td><g:link controller="shipment" action="view" id="${shipment.id}">${shipment.toCompany}</g:link></td>
                    <td>${dateFormat.format((java.util.Date)shipment.scheduled)}</td>
                    <td><g:link controller="shipment" action="remove" id="${shipment.id}">Remove</g:link></td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container">
          <div class="kumulus-widget kumulus-scrollable-y">
            <g:link controller="project" action="create" class="kumulus-float-right kumulus-margin-top">Create new</g:link>
              <h3>You are working on ${projectList.size} projects.</h3>                      
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
                    <td><g:link controller="project" action="view" id="${project.id}">${project.projectName}</g:link></td>
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
