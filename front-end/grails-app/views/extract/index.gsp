<html>
    <head>
        <title>Review tasks | Kumulus</title>
    </head>
    <body>
        <p><b>Review tasks</b></p>
        <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
        <table class="pure-table" width="100%">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Task</th>
                    <th>Time created</th>
                    <th>Action pending</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${tasks?.list()}" status="i" var="taskInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                        <td>${taskInstance.id}</td>
                        <td>
                            <g:if test="${taskInstance.status==5}">Validate</g:if>
                            <g:if test="${taskInstance.status==4}">Classify</g:if>
                        </td>
                        <td>${taskInstance.created.format("dd/MM/yyyy - HH:mm:ss")}</td>
                        <td>
                            <g:if test="${taskInstance.status==5}"><g:link controller="home" action="validate">To do</g:link></g:if>
                            <g:if test="${taskInstance.status==4}"><g:link controller="home" action="classify">To do</g:link></g:if>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>