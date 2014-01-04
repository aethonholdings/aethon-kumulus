<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/jstree', file: 'jstree-min.css')}"/>
        <g:javascript src="jstree.min.js"/>
        <g:javascript src="nodeTree.js"/>
        <title>Prepare scans | Kumulus</title>
    </head>
    <body>
        <p><b>Prepare documents for scanning > ${project.projectName}</b></p>
        <div id="nodeTree">
            <ul>
                <li>Root node 1</li>
                <li>Root node 2</li>
            </ul>
        </div>
        <p>${nodes}</p>
    </body>
</html>
