<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'pure-min.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'side-menu.css')}"/>
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
                        <li><a href="">Prepare scans</a></li>
                        <li><a href="">Classify scans</a></li>
                        <li><a href="">Validate data</a></li>
                        <li><a href="">Extract ledger</a></li>
                        <li><a href="">Access archive</a></li>
                    </ul>
                </div>
            </div>
            <div class="content">
                <g:layoutBody/>
            </div>
        </div>
    </body>
</html>
