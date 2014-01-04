<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'pure-min.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'side-menu.css')}"/>
        <g:javascript library="jquery" plugin="jquery"/>
        <g:javascript src="ui.js"/>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
        <div id="layout">
            <div id='menu'>
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading" href="">kumulus</a>
                    <ul>
                        <li><g:link controller="home" action="index">Review tasks</g:link></li>
                        <li><g:link controller="collect" action="index">Prepare scans</g:link></li>
                        <li><a href="">Extract data</a></li>
                        <li><a href="">Access archive</a></li>
                        <li><a href="">Order materials</a></li>
                    </ul>
                </div>
            </div>
            <div class="content">
                <g:layoutBody/>
            </div>
        </div>
    </body>
</html>
