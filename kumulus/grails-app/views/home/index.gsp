<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Kumulus</title>
    </head>
    <body>
        <div class="pure-g kumulus-small-font">
            <div class="pure-u-1-2">
                <div class="kumulus-container-1-3 kumulus-scrollable-y kumulus-element-border">
                  <h3>You have ${userTasks.count} tasks outstanding</h3>
                  <g:taskQueue userId="${userId}"/>
                </div>
                <div class="kumulus-container-1-3 kumulus-scrollable-y kumulus-element-border">
                  <h3>There are ${backOfficeTasks.count} tasks outstanding in the Kumulus queue</h3>
                  <g:taskQueue userId="${null}"/>
                </div>
                <div class="kumulus-container-1-3 kumulus-scrollable-y kumulus-element-border">
                  <h3>You have Z shipments scheduled</h3>
                  <table class='pure-table pure-table-horizontal'>
                        <thead>
                            <tr>
                                <th>Project</th>
                                <th>Task type</th>
                                <th>Documents to be processed</th>
                            </tr>
                        </thead>
                        <tbody>     
                           <tr>
                                 <td></td>
                                 <td></td>
                                 <td></td>
                          </tr>
                        </tbody>
                    </table>  
                </div>
            </div>
            <div class="pure-u-1-2">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
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
                    <g:link controller="project" action="create" class="pure-button kumulus-float-right kumulus-margin-top">Create new</g:link>
                </div>
            </div>
        </div>
    </body>
</html>
