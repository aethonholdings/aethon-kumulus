<div id="content">
  <div id="header"><p>Welcome <sec:username /></p>
  <h2>Welcome to Kumulus Home</h2>
  <sec:ifLoggedIn>
    <sec:ifAllGranted roles="ROLE_VALIDATE">
      <p>You have ${validationTasks?.count()} tasks pending</p>
      <div class="validationTaskTable">
        <table>
          <thead>
            <g:sortableColumn property="created" title="Created"/>
            <g:sortableColumn property="barcode" title="Container barcode"/>
            <g:sortableColumn property="name" title="Document name"/>
            <g:sortableColumn property="comment" title="Comments"/>
          </thead>
          <g:each in="${validationTasks}" var="task">
            <g:each in="${validationTasks.nodes}" var="node">
              <tr>
                <td>${task.created}</td>
                <td>${node.barcode}</td>
                <td>${node.name}</td>
                <td>${node.comment}</td>
              </tr>
            </g:each>
          </g:each>      
        </table>
      </div>
    </sec:ifAllGranted>
  </sec:ifLoggedIn>
</div>