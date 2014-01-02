<html>
    <head>
        <meta name="layout" content="main"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'side-menu.css')}"/>
        <g:javascript src="ui.js"/>
        <title>Home | Kumulus</title>
    </head>
    <body>
        <div id="layout">
            <div id='menu'>
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading" href="">kumulus</a>
                    <ul>
                        <li><a href="">Start new project</a></li>
                        <li><a href="">Classify scans</a></li>
                        <li><a href="">Validate data</a></li>
                        <li><a href="">Extract ledger</a></li>
                        <li><a href="">Access archive</a></li>
                    </ul>
                </div>
            </div>
            <div class="content">
                <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
                <table class="pure-table" width="100%">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="Id"/>
                            <g:sortableColumn property="created" title="Time created"/>
                            <g:sortableColumn property="barcode" title="Task"/>
                            <g:sortableColumn property="action" title="Action pending"/>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${tasks?.list()}" status="i" var="taskInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                                <td>${taskInstance.id}</td>
                                <td>${taskInstance.created.format("dd/MM/yyyy - HH:mm:ss")}</td>
                                <td>
                                    <g:if test="${taskInstance.type=='TASK_VALIDATE'}">Validate</g:if>
                                    <g:if test="${taskInstance.type=='TASK_CLASSIFY'}">Classify</g:if>
                                </td>
                                <td>
                                    <g:if test="${taskInstance.type=='TASK_VALIDATE'}"><g:link controller="home" action="validate">To do</g:link></g:if>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>