<div id="content">
    <div id="header">
        %{--<p>Welcome <sec:username /></p>--}%
        %{--<h2>Welcome to Kumulus Home</h2>--}%
        <sec:ifLoggedIn>
            <sec:ifAllGranted roles="ROLE_VALIDATE">
                <p>You have ${tasks?.count()} task<g:if test="${tasks?.count()>1}">s</g:if> pending</p>
                <div class="validationTaskTable">
                    <table>
                        <thead class='thclass'>
                        <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="created" title="Time created"/>
                        <g:sortableColumn property="barcode" title="Task"/>
                        <g:sortableColumn property="action" title="Action pending"/>
                        </tr>
                        </thead>
                        <tbody class='tdclass'>
                        <g:each in="${tasks.list()}" status="i" var="taskInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                                <td>${taskInstance.id}</td>
                                <td>${taskInstance.created.format("d/M/YYYY - HH:mm:ss")}</td>
                                <td>
                                    <g:if test="${taskInstance.type=='TASK_VALIDATE'}">Validate</g:if>
                                    <g:if test="${taskInstance.type=='TASK_CLASSIFY'}">Classify</g:if>
                                </td>
                                <td><g:link controller="home" action="validate" style="color:blue">To do</g:link></td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
            </sec:ifAllGranted>
        </sec:ifLoggedIn>
    </div>
</div>