<html>
    <head>
        <title>Extract | Kumulus</title>
    </head>
    <body>
        <p><b>Review data extraction tasks</b></p>
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
                            <g:link controller="extract" action="workflow" batchId="">To do</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>