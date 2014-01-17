<!DOCTYPE html>
<html lang='en'>
    <head>
        <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
        <r:layoutResources />
        <g:layoutHead/>
    </head>
    <body>
      <div class='content'>
        <div id='kumulus-header' class='kumulus-gradient-blue'>
          <div class='pure-g'>
            <div class='pure-u-1-2'></div>
            <div class='pure-u-1-2'>
              <div id='kumulus-session-management'>
                Welcome <b><sec:loggedInUserInfo field='username'/></b> |
                Logout
              </div>
            </div>
          </div>
        </div>
        <div id='kumulus-body'>
          <g:layoutBody/>
        </div>
      </div>
    </body>
</html>