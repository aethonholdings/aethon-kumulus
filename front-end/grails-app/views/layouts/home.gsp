<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/pure', file: 'pure-min.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/pure', file: 'side-menu.css')}"/>
        <g:javascript src="pure/ui.js"/>
        <g:javascript library='jquery' />
        <r:layoutResources />
        <g:layoutHead/>
    </head>
    <body>
        <div id="layout">
            <div id='menu'>
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading" href="">kumulus</a>
                    <ul>
                        <li><g:link controller="home" action="index">Home</g:link></li>
                        <li><g:link controller="collect" action="index">Prepare scans</g:link></li>
                        <li><g:link controller="extract" action="index">Extract data</g:link></li>
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
