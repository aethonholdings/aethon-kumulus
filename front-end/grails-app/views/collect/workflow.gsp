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
        <div id="nodeTree"></div>
      </div>
      <div class="pure-u-2-3">
        <table class="pure-table">
          <tr>
            <td>Barcode</td>
            <td></td>
          </tr>
          <tr>
            <td>Comment</td>
            <td></td>
          </tr>
          <tr>
            <td>Type</td>
            <td></td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>
