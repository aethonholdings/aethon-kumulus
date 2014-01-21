<html>
    <head>
        <title>Manage projects | Kumulus</title>
    </head>
    <body>
      <table class='pure-table' width='100%'>
        <thead>
          <tr>
            <th>Project name</th>
            <th>Client</th>
            <th>Status</th>
            <th colspan="3">Action</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${projectList}">
            <tr>
              <td>${it.projectName}</td>
              <td>${it.client.name}</td>
              <td>${it.status}</td>
              <td><g:link controller="project" action="edit" id="${it.id}">Edit</g:link></td>
              <td>
                <g:if test="${it.status=="A"}"><g:link controller="project" action="delete" id="${it.id}">Delete</g:link></g:if>
              </td>
              <td>
                <g:if test="${it.status=="A"}"><g:link controller="project" action="close" id="${it.id}">Close</g:link></g:if>
              </td>
            </tr>
          </g:each>
        </tbody>
      </table>     
      <p><g:link controller="project" action="create" class="pure-button">Create new</g:link></p>
    </body>
</html>
