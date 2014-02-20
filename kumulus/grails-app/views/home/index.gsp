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
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <g:set var="size" value="${0}"/>
                    <g:each in="${taskList}"  var="tlist">
                        <g:set var="size" value="${tlist.count}"/>
                    </g:each>
                    <h3>You have ${size} tasks outstanding.</h3>
                    <table class='pure-table pure-table-horizontal'>
                        <thead>
                            <tr>
                                <th>Created Date</th>
                                <th>Type</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>     
                            <g:each in="${workItemList}"  var="workItem">
                                <tr>   
                                    <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${task.created}"/></td>
                                    <td><g:if test="${workItem.type=WorkItem.BUILD_DOCUMENT}">Build</g:if>
                                        <g:elseif test="${workItem.type=WorkItem.OCR_DOCUMENT}">OCR</g:elseif>
                                        <g:elseif test="${workItem.type=WorkItem.REVIEW_DOCUMENT}">Review</g:elseif>
                                    </td>
                                     <td><g:link controller="task" action="complete">Complete</g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>  
                </div>
            </div>
            <div class="pure-u-1-2">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <g:set var="psize" value="${0}"/>
                    <g:each in="${projectList}"  var="plist">
                        <g:set var="psize" value="${plist.count}"/>
                    </g:each>
                    <h3>You are working on ${psize} projects.</h3>
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
