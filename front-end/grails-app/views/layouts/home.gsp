<!DOCTYPE html>
<html lang='en'>
  <head>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'side-menu.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/kumulus', file: 'main.css')}'/>
    <g:javascript library='jquery' />
    <r:layoutResources />             
    <g:javascript src='jquery/ui/jquery-ui.js'/>
    <g:javascript src='jquery/cookie/jquery.cookie.js'/>
    <g:javascript src='pure/ui.js'/> 
    <g:layoutHead/>
  </head>
  <body>
    <div id='layout'>
      <div id='menu'>
        <div class='pure-menu pure-menu-open'>
          <a class='pure-menu-heading' href=''>kumulus</a>
          <ul>
            <li><g:link controller='home' action='index'>Home</g:link></li>
            <li><a href=''>Order materials</a></li>
            <li><g:link controller='home' action='manage'>Manage projects</g:link></li>
            <li><g:link controller='home' action='collect'>Collect paperwork</g:link></li>
            <li><g:link controller='home' action='upload'>Upload scans</g:link></li>
            <li><g:link controller='home' action='create'>Create documents</g:link></li>
            <li class='menu-item-divided'><g:link controller='home' action='process'>Process documents</g:link></li>
            <li class='menu-item-divided'><g:link controller='home' action='review'>Review documents</g:link></li>
            <li><g:link controller='home' action='extract'>Download transactions</g:link></li>
            <li><a href=''>Schedule pickup</a></li>
            <li><g:link controller='home' action='access'>Access archive</g:link></li>
            <li class='menu-item-divided'><a href=''>Manage account</a></li>
          </ul>
        </div>
      </div>
      <div class='content'>
        <div id='kumulus-header'>
          <div class='pure-g'>
            <div class='pure-u-1-2'>
              <g:pageTitle text='${pageTitle}'/>
            </div>
            <div class='pure-u-1-2'>
              <div id='kumulus-session-management'>
                <g:userCompany/> |
                <b><sec:loggedInUserInfo field='username'/></b> |  
                <g:link controller='logout'> Logout</g:link>
              </div>
            </div>
          </div>
        </div>
        <div id='kumulus-body'>
          <g:layoutBody/>
        </div>
      </div>
    </div>
  </body>
</html>
