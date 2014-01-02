<html>
    <head>
        <meta name="layout" content="main"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'index.css')}"/>
        <title>Welcome | Kumulus</title>
    </head>
    <body>
        <div id="layout">
            <div id="header">
                <div id="logo" class="header-element"></div>
                <div id="session-management" class="header-element">Welcome <b><sec:loggedInUserInfo field="username"/></b></div>
            </div>
            <div id="layoutBody">
                <h1>Welcome to Kumulus</h1>
                <p><g:link controller="login">Log in</g:link></p>
            </div>
        </div>
    </body>
</html>