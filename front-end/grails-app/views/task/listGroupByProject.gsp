<html>
    <head>
        <title>${title} | Kumulus</title>
    </head>
    <body>
      <table class='pure-table pure-table-horizontal'>
        <thead>
          <tr>
            <th>Project</th>
            <th>Client</th>
            <th>Scans to be processed</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${projectList}">
            <tr>
              <td>${it?.projectName}</td>
              <td>${it?.client.name}</td>
              <td>${tasksByProject.get(it.id)?.tasks?.size()}</td>
              <td><g:link controller="document" action="build" id="${it.id}">Complete</g:link></td>
            </tr>
          </g:each>
        </tbody>
      </table>  
    </body>
</html>