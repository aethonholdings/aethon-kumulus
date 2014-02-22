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
                <div class="kumulus-container-half kumulus-scrollable-y kumulus-element-border">
                    <h3>You have ${taskCount} tasks outstanding.</h3>
                    <table class='pure-table pure-table-horizontal'>
                        <thead>
                            <tr>
                                <th>Project</th>
                                <th>Task type</th>
                                <th>Documents to be processed</th>
                            </tr>
                        </thead>
                        <tbody>     
                            <g:each in="${tasks}" var="task">
                                <tr>
                                    <td>${task.project.projectName}</td>
                                    <td><g:if test="${task.taskType==1}">Build</g:if>
                                        <g:elseif test="${task.taskType==2}">OCR</g:elseif>
                                        <g:elseif test="${task.taskType==3}">Review</g:elseif>
                                    </td>
                                    <td><g:link controller="home" action="completeTasks" params='[projectId: "${task.project.id}", taskType: "${task.taskType}"]'>${task.taskCount}</g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>  
                </div>
                <div class="kumulus-container-half kumulus-scrollable-y kumulus-element-border">
                    <h3>You have Z shipment schedule.</h3>
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
                                    <td>${project.projectName}</td>
                                    <td>${project.client.name}</td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>  
                </div>
            </div>
        </div>
    </body>
</html>
