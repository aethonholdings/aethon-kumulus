<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/jstree', file: 'jstree-min.css')}"/>
        <g:javascript src="jstree.min.js"/>
        <g:javascript src="nodeTree.js"/>
        <script type="text/javascript">
        </script>
        <title>Prepare scans | Kumulus</title>
    </head>
    <body>
        <p><b>Prepare documents for scanning > ${project.projectName}</b></p>
        <div id="nodeTree"></div>
        <div id="event_result"></div>
    </body>
</html>
