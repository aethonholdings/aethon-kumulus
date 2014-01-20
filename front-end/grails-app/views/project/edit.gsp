<html>
  <head>
      <title>Edit project | Kumulus</title>
  </head>
  <body>
    <g:form name="project" action="update" id="${project?.id}" class="pure-form pure-form-aligned">
      <div class="pure-control-group">
        <label for="projectName">* Project name</label>
        <input id="projectName" name="projectName" type="text" value="${project?.projectName}" class="pure-input-1-2">
      </div>
      <div class="pure-control-group">
        <label for="clientName">* Client name</label>
        <input id="clientName" name="clientName" type="text" value="${project?.client}" class="pure-input-1-2"></input>
      </div>
      <div class="pure-control-group">
        <label for="comment">Comment</label>
        <textarea id="comment" name="comment" type="text" class="pure-input-1-2" rows="5">${project?.comment}</textarea>
      </div>
      <p>
        <g:link controller="project" action="list" class="pure-button">Cancel</g:link>
        <input type="submit" value="Update" class="pure-button pure-button-primary">
      </p>
    </g:form>
  </body>
</html>