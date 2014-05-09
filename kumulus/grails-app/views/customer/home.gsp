<!--
  Customer home
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
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">You are working on ${projectList.size} projects</span>
              <span class="kumulus-widget-header-action"><g:link controller="customer" action="create">Create new</g:link></span>
            </div>
            <table class='pure-table pure-table-horizontal'>
              <thead>
                <tr>
                  <th>Project Name</th>
                  <th>Client</th>
                  <th>Status</th>
                  <th>Created</th>
                  <th>Closed</th>
                </tr>
              </thead>
              <tbody>     
                <g:each var="project" in="${projectList}">
                  <tr>
                    <td><g:link controller="customer" action="collect" id="${project.id}">${project.projectName}</g:link></td>
                    <td>${project.client.name}</td>
                    <td>${project.status()}</td>
                    <td>${project.created.format("dd MMM yyyy")}</td>
                    <g:if test="${project.closed}">
                      <td>${project.closed.format("dd MMM yyyy")}</td>
                    </g:if>
                    <g:else>
                      <td></td>
                    </g:else>
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
