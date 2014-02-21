<html>
  <head>
      <title>Edit project | Kumulus</title>
       <g:javascript src='Validate/validate.js'/>
        <g:javascript src='kumulus/validation.js'/>
     <resource:autoComplete remote="true" />       
      
  </head>
  <body>
    <g:form name="project" action="update" id="${project?.id}" class="pure-form pure-form-aligned">
      <div class="pure-control-group">
        <label for="projectName">* Project name</label>
        <input id="projectName" name="projectName" type="text" value="${project?.projectName}" class="pure-input-1-2">
      </div>
      <div class="pure-control-group">
        <label for="clientName">* Client name</label>
        <richui:autoComplete  name="clientName" id='ClientName' action="${createLinkTo('dir': 'project/clientList')}" class="pure-input-1-2" />
          
      </div>
      <div class="pure-control-group">
        <label for="comment">Comment</label>
        <textarea id="comment" name="comment" type="text" class="pure-input-1-2" rows="5">${project?.comment}</textarea>
      </div>
      <p>
        <g:link controller="project" action="list" params="[type: 'manage']" class="pure-button">Cancel</g:link>
        <input type="submit" value="Update" class="pure-button pure-button-primary">
      </p>
    </g:form>
  </body>
</html>