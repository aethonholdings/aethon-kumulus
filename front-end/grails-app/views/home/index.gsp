<%--
  Created by IntelliJ IDEA.
  User: damyant
  Date: 12/10/13
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Home | Kumulus </title>
    </head>
    <body>
        <div class="header">

            <div class="headerBlock">
                <div class="logo">

                </div>
                <div class="sessionManagement" style="color:white">
                    Welcome
                    <sec:loggedInUserInfo field="username"/> |
                    <g:link controller="#" action="#">Manage User</g:link>|
                    <g:link controller="#" action="#">Logout</g:link>
                </div>

            </div>
        </div>
        <div class='main'>
            <div class='leftContentDiv'>

            </div>
            <div class="rightContentDiv">
                                <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
                <div class="validationTaskTable">
                    <table>
                        <thead class='thclass'>
                        <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="created" title="Time created"/>
                        <g:sortableColumn property="barcode" title="Task"/>
                        <g:sortableColumn property="action" title="Action pending"/>
                        </tr>
                        </thead>
                        <tbody class='tdclass'>
                        <g:each in="${tasks.list()}" status="i" var="taskInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                                <td>${taskInstance.id}</td>
                                <td>${taskInstance.created.format("d/M/YYYY - HH:mm:ss")}</td>
                                <td>
                                    <g:if test="${taskInstance.type=='TASK_VALIDATE'}">Validate</g:if>
                                    <g:if test="${taskInstance.type=='TASK_CLASSIFY'}">Classify</g:if>
                                </td>
                                <td>
                                    <g:if test="${taskInstance.type=='TASK_VALIDATE'}"><g:link controller="home" action="validate">To do</g:link></g:if>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>