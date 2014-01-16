<html>
    <head>
        <title>Extract | Kumulus</title>
    </head>
    <body>
        <p>Pending data extraction tasks</p>
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
                            <g:if test="${taskInstance.status==4}">Review</g:if>
                        </td>
                        <td>${taskInstance.created.format("dd/MM/yyyy - HH:mm:ss")}</td>
                        <td>
                          <form action="http://test.ephesoft.kumulus.sg:8080/dcma/ReviewValidate.html?batch_id=${taskInstance.batchInstanceUrlId}" method="post" target="_blank">
                            <input type="hidden" name="username" value="ephesoft">
                            <input type="hidden" name="password" value="Ke$haIsGreat666">
                            <input type="submit" class="pure-button pure-button-primary" value="To do">
                          </form>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>