<html>
    <head>
        <title>${title} | Kumulus</title>
    </head>
    <body>
      <table class='pure-table' width='100%'>
        <thead>
          <tr>
            <th>Time created</th>
            <th>Description</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${tasks}">
            <tr>
              <td>${it?.created}</td>
              <td><g:taskDescription task="${it}"/></td>
              <td><g:link controller="task" action="action" id="${it.id}">Perform</g:link></td>
            </tr>
          </g:each>
        </tbody>
      </table>  
    </body>
</html>