<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/jstree', file: 'jstree-min.css')}"/>
    <g:javascript src="jstree/jstree.min.js"/>
    <g:javascript src="kumulus/nodeTree.js"/>
    <script type="text/javascript">
    </script>
    <title>Prepare scans | Kumulus</title>
  </head>
  <body>
    <p><g:link action='index'>Prepare documents for scanning</g:link> > ${project.projectName}</p>
    <div class="pure-g">
      <div class="pure-u-1-3">
        <div class="grid-element"><h3>Archive structure</h3></div>
        <div class="grid-element" style="overflow-y:auto;overflow-x:auto;">
          <div id="nodeTree" class="jstree-draggable"></div>
        </div>
        <div class="grid-element grid-footer">
          <a id="button-add" class="pure-button pure-button-primary" onclick="add_node();">Add</a>
          <a id="button-edit" class="pure-button pure-button-primary" onclick="edit_node();">Edit</a>
          <a id="button-delete" class="pure-button pure-button-primary" onclick="delete_node();">Delete</a>
        </div>
      </div>
      <div class="pure-u-2-3">
        <div class="grid-element"><h3>Container information</h3></div>
        <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
          <fieldset>
            <div class="pure-control-group">
              <label for="barcode">Barcode</label>
              <input id="barcode" type="text" placeholder="Scan container barcode" disabled>
            </div>
            <div class="pure-control-group">
              <label for="type">Type</label>
              <select id="type" class="pure-input-1-4" disabled>
                <option>Container</option>  
                <option>Box</option>
              </select>
            </div>
            <div class="pure-control-group">
              <label for="comment">Comment</label>
              <input id="comment" type="text" placeholder="Enter comments here" class="pure-input-1-2" disabled>
            </div>
            <div class="grid-element grid-footer">
              <a id="button-save" class="pure-button pure-button-primary" href="#">Save</a>
              <a id="button-cancel" class="pure-button pure-button-primary" onclick="cancel();">Cancel</a>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  </body>
</html>
