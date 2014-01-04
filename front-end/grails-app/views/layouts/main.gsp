<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'pure-min.css')}"/>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
        <div id="layout">
            <div id="header">
                <div id="logo" class="header-element"></div>
                <div id="session-management" class="header-element">Welcome <b><sec:loggedInUserInfo field="username"/></b></div>
            </div>
            <div id="layoutBody">
                <g:layoutBody/>
            </div>
        </div>            
    </body>
</html>
