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
                    <h3>You have ${userTasks?.total} tasks outstanding.</h3>
                    <g:if test="${userTasks.type.BUILD.total>0}">
                        ${userTasks?.type?.BUILD.total} scans to be reviewed
                            <table class="pure-table pure-table-horizontal">
                            <g:each var="taskType" in="${userTasks.type.BUILD.list}">
                                <thead>
                                    <th>Project</th>
                                    <th>Scans</th>
                                    <th></th>
                                </thead>
                                <tr>
                                    <td>${taskType.project.projectName}</td>
                                    <td>${taskType.taskCount}</td>
                                    <td><g:link controller="capture" action="build" params='[projectId: "${taskType.project.id}"]'>Action</g:link></td>
                                </tr>
                            </g:each>
                        </table>
                    </g:if>
                  
                </div>
                <div class="kumulus-container-1-3 kumulus-scrollable-y kumulus-element-border">
                    <h3>There are ${backOfficeTasks.total} tasks in kumulus's queue</h3>
                    <ul>
                      <li>${backOfficeTasks.type.OCR.total} documents to be OCRed</li>
                      <li>${backOfficeTasks.type.PROCESS.total} documents to be processed - <g:link controller="structure" action="process">Action</g:link></li>
                    </ul>
                </div>
                <div class="kumulus-container-1-3 kumulus-scrollable-y kumulus-element-border">
                      <h3>You have Z shipments schedule.</h3>
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
