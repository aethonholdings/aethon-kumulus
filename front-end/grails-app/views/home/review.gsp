<html>
  <head>
    <title>Review | Kumulus</title>
  </head>
  <body>
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
              <td><g:link controller="document" action="review" id="${taskInstance.id}">To do</g:link></td>
            </tr>
        </g:each>
      </tbody>
    </table>
  </body>
</html>