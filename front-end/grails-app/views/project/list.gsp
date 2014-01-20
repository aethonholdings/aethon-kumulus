<html>
    <head>
        <title>Manage projects | Kumulus</title>
    </head>
    <body>
      <table class='pure-table' width='100%'>
        <thead>
          <tr>
            <th>Id</a>
            <th>Project name</th>
            <th>Client</th>
            <th>Status</th>
            <th colspan="3">Action</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${projectList}">
            <tr>
              <td>${it.id}</td>
              <td>${it.projectName}</td>
              <td>${it.clientLDAPId}</td>
              <td>${it.status}</td>
              <td>Edit</td>
              <td>Delete</td>
              <td>Close</td>
            </tr>
          </g:each>
        </tbody>
      </table>     
      <p><g:link controller="project" action="create" class="pure-button pure-button-primary">Create new</g:link></p>
    </body>
</html>
