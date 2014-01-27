<html>
    <head>
        <title>Projects | Kumulus</title>
    </head>
    <body>
      <table class='pure-table' width='100%'>
        <thead>
          <tr>
            <th>Project name</th>
            <th>Client</th>
            <th>Status</th>
            <th colspan="${actions.size()}">Action</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${projectList}">
            <tr>
              <td>${it.projectName}</td>
              <td>${it.client.name}</td>
              <td>${it.status}</td>
              <g:each var="action" in="${actions}">
                <td>
                  <g:if test="${action=="Edit" && it.status=="A"}">
                    <g:link controller="project" action="edit" id="${it.id}">Edit</g:link>
                  </g:if>
                  <g:if test="${action=="Delete" && it.status=="A"}">
                    <g:link controller="project" action="delete" id="${it.id}">Delete</g:link>
                  </g:if>
                  <g:if test="${action=="Close" && it.status=="A"}">
                    <g:link controller="project" action="close" id="${it.id}">Close</g:link>
                  </g:if>
                  <g:if test="${action=="Download"}">
                    <g:link controller="project" action="download" id="${it.id}">Download</g:link>
                  </g:if>
                  <g:if test="${action=="Access"}">
                    <g:link controller="project" action="access" id="${it.id}">Download</g:link>
                  </g:if>
                  <g:if test="${action=="Collect"}">
                    <g:link controller="node" action="collect" id="${it.id}">Collect</g:link>
                  </g:if>
                  <g:if test="${action=="Upload"}">
                    <g:link controller="image" action="upload" id="${it.id}">Upload</g:link>
                  </g:if>
                </td>
              </g:each>
            </tr>
          </g:each>
        </tbody>
      </table>
      <g:if test="${actions.containsAll("Create")}">
        <p><g:link controller="project" action="create" class="pure-button">Create new</g:link></p>
      </g:if>
    </body>
</html>
