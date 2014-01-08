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
    <p><b>Prepare documents for scanning > ${project.projectName}</b></p>
    <div class="pure-g">
      <div class="pure-u-1-3">
        <div class="grid-element"><h3>Archive structure</h3></div>
        <div class="grid-element" style="overflow-y:auto;">
          <div id="nodeTree"></div>
        </div>
        <div class="grid-element grid-footer">
          <a class="pure-button pure-button-primary" href="#">Add</a>
          <a class="pure-button pure-button-primary" href="#">Delete</a>
        </div>
      </div>
      <div class="pure-u-2-3">
        <div class="grid-element"><h3>Container information</h3></div>
        <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
          <fieldset>
            <div class="pure-control-group">
              <label for="parent">Parent barcode</label>
              <input id="parent" type="text" placeholder="#" disabled>
            </div>
            <div class="pure-control-group">
              <label for="barcode">Barcode</label>
              <input id="barcode" type="text" placeholder="Scan container barcode">
            </div>
            <div class="pure-control-group">
              <label for="type">Container type</label>
              <select id="type" class="pure-input-1-4">
                <option>Container</option>  
                <option>Box</option>
                <option>Document</option>
              </select>
            </div>
            <div class="pure-control-group">
              <label for="comment">Comment</label>
              <input id="comment" type="text" placeholder="Enter comments here" class="pure-input-1-2">
            </div>
            <div class="grid-element grid-footer">
              <a class="pure-button pure-button-primary" href="#">Save</a>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  </body>
</html>
