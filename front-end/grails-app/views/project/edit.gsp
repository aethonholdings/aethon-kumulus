<html>
  <head>
      <title>Prepare scans | Kumulus</title>
  </head>
  <body>
    <form name="project" action="add" method="POST" class="pure-form pure-form-aligned">
      <fieldset>
        <div class="pure-control-group">
          <label for="projectName">* Project name</label>
          <input id="projectName" type="text" placeholder="Enter project name" class="pure-input-1-2">
        </div>
        <div class="pure-control-group">
          <label for="clientName">* Client name</label>
          <input id="clientName" type="text" placeholder="Client name" class="pure-input-1-2">
        </div>
        <div class="pure-control-group">
          <label for="comment">Comment</label>
          <textarea id="comment" type="text" class="pure-input-1-2" rows="5"></textarea>
        </div>
      </fieldset>
      <p>
        <g:link controller="project" action="list" class="pure-button">Cancel</g:link>
        <g:link controller="project" action="save" class="pure-button pure-button-primary">Save</g:link>
      </p>
    </form>
  </body>
</html>