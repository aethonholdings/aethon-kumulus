<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/jstree', file: 'jstree-min.css')}"/>
        <g:javascript src="jstree.min.js"/>
        <g:javascript src="nodeTree.js"/>
        <script type="text/javascript">

$(document).ready ( function(){
  $('#nodeTree').jstree();
  $.getJSON("/front-end/collect/refreshTree", function(result){
    $.each(result, function(i, field){
      $("div").append(field + " ");
    });
  });
});

        </script>
        <title>Prepare scans | Kumulus</title>
    </head>
    <body>
        <p><b>Prepare documents for scanning > ${project.projectName}</b></p>
        <div id="nodeTree">
            <ul>
                <li>Root node 1</li>
            </ul>
        </div>
    </body>
</html>
