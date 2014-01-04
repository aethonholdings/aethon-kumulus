<html>
    <head>
        <title>Home | Kumulus</title>
    </head>
    <body>
        <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
        <table class="pure-table" width="100%">
            <thead>
                <tr>
                    <g:sortableColumn property="id" title="Id"/>
                    <g:sortableColumn property="created" title="Time created"/>
                    <g:sortableColumn property="barcode" title="Task"/>
                    <g:sortableColumn property="action" title="Action pending"/>
                </tr>
            </thead>
            <tbody>
                <g:each in="${tasks?.list()}" status="i" var="taskInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                        <td>${taskInstance.id}</td>
                        <td>${taskInstance.created.format("dd/MM/yyyy - HH:mm:ss")}</td>
                        <td>
                            <g:if test="${taskInstance.status==5}">Validate</g:if>
                            <g:if test="${taskInstance.status==4}">Classify</g:if>
                        </td>
                        <td>
                            <g:if test="${taskInstance.status==5}"><g:link controller="home" action="validate">To do</g:link></g:if>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>