<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'main.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'side-menu.css')}"/>
        <g:javascript src="ui.js"/>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
        <div id='layout'>
            <div id='menu'>
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading" href="">kumulus</a>
                    <ul>
                        <li><a href="">Classify scans</a></li>
                        <li><a href="">Validate data</a></li>
                        <li><a href="">Extract ledger</a></li>
                        <li><a href="">Access archive</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="header">
                    <div id="logo" class="header-element"></div>
                    <div id="session-management" class="header-element">Welcome <b><sec:loggedInUserInfo field="username"/></b></div>
                </div>
                <div id="layoutBody">
                    <g:layoutBody/>
                </div>
            </div>
        </div>
    </body>
</html>
