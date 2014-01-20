<!DOCTYPE html>
<html lang='en'>
  <head>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'side-menu.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/kumulus', file: 'main.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/jquery/jbreadcrumb', file: 'BreadCrumb.css')}'/>
    <g:javascript library='jquery' />
    <r:layoutResources />             
    <g:javascript src='jquery/ui/jquery-ui.js'/>
    <g:javascript src='jquery/cookie/jquery.cookie.js'/>
    <g:javascript src='pure/ui.js'/> 
    <g:javascript src='jquery/easing/jquery.easing.1.3.js'/> 
    <g:javascript src='jquery/jbreadcrumb/jquery.jBreadCrumb.1.1.js'/> 
    <script>
      jQuery(document).ready(function()
{
	jQuery("#breadCrumb").jBreadCrumb();
})
    </script>
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
            <li><g:link controller='home' action='collect'>Collect documents</g:link></li>
            <li><a href=''>Import documents</a></li>
            <li><g:link controller='home' action='review'>Review data</g:link></li>
            <li><g:link controller='home' action='extract'>Download ledger</g:link></li>
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
