<div id="content">
  <div id="header"><p>Welcome <sec:username /></p>
  <h2>Welcome to Kumulus Home</h2>
  <sec:ifLoggedIn>
    <sec:ifAllGranted roles="ROLE_VALIDATE">
      <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
      <div class="validationTaskTable">
        <table>
          <thead>
            <g:sortableColumn property="id" title="Id"/>  
            <g:sortableColumn property="created" title="Time created"/>
            <g:sortableColumn property="barcode" title="Task"/>
            <g:sortableColumn property="action" title="Action pending"/>
          </thead>
          <g:each in="${tasks.list()}">
            <tr>
              <td>${it.id}</td>
              <td>${it.created.format("d/M/YYYY - HH:mm:ss")}</td>
              <td>
                <g:if test="${it.type=='TASK_VALIDATE'}">Validate</g:if>
                <g:if test="${it.type=='TASK_CLASSIFY'}">Classify</g:if>
              </td>
              <td><g:link controller="home" action="validate">To do</g:link></td>
            </tr>
          </g:each>
        </table>
      </div>
    </sec:ifAllGranted>
  </sec:ifLoggedIn>
</div>