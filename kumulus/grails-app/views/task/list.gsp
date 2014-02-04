<html>
    <head>
        <title>${title} | Kumulus</title>
    </head>
    <body>
      <table class='pure-table pure-table-horizontal'>
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
              <td><g:link controller="dataProcessing" action="process" id="${it.id}">Complete</g:link></td>
            </tr>
          </g:each>
        </tbody>
      </table>  
    </body>
</html>