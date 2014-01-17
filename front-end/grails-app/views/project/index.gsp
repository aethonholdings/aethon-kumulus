<html>
    <head>
        <title>Manage projects | Kumulus</title>
    </head>
    <body>
        <p>Manage projects</p>
        <g:projectTable class="pure-table" width="100%" projects="${projects}" controller="project" action="changeStatus" actionText="Change status"/>
    </body>
</html>