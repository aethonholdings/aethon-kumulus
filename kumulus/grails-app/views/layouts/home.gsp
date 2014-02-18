<!DOCTYPE html>
<html lang='en'>
  <head>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
    <!--<link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'side-menu.css')}'/>-->
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/kumulus', file: 'main.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/jquery/ui/base', file: 'jquery-ui.css')}'/>
    <g:javascript library='jquery' />
    <r:layoutResources />             
    <g:javascript src='jquery/ui/jquery-ui.js'/>
    <g:javascript src='jquery/cookie/jquery.cookie.js'/>
    <g:javascript src='pure/ui.js'/> 
    <g:javascript src='kumulus/base.js'/>
    <g:layoutHead/>
  </head>
  <body>
      <div id='kumulus-header-layout'>
        <div id='kumulus-header'>
           <div class='pure-g'>
               <div class='pure-u-1-2'>
                 <div id='kumulus-logo'></div>
                 <!-- <g:pageTitle text='${pageTitle}'/>-->
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
        </div>
       <div id='layout'>
        <div class='pure-menu pure-menu-open pure-menu-horizontal kumulus-magrin-bottom'>
         <!--<a class='pure-menu-heading' href=''>kumulus</a>-->
             <ul>
               <li><g:link controller='home' action='index'>Home</g:link></li>
               <li class='menu-item-divided'><g:link controller='home' action='manage'>Manage projects</g:link></li>
               <li><a href="#">Review and approve</a></li>
               <li class='menu-item-divided'><g:link controller='home' action='download'>Download transactions</g:link></li>
               <li><g:link controller='home' action='access'>Access archive</g:link></li>
               <li class='menu-item-divided'><g:link controller='home' action='collect'>Collect paperwork</g:link></li>
               <li><g:link controller='home' action='upload'>Upload scans</g:link></li>
               <li><g:link controller='home' action='build'>Build documents</g:link></li>
               <li><g:link controller='home' action='pickup'>Request pickup</g:link></li>
               <li class='menu-item-divided'><a href="#">Order materials</a></li>
               <li><a href="#">Manage account</a></li>
               <li  class='menu-item-divided'><g:link controller='home' action='process'>Process documents</g:link></li>
             </ul>
         </div>
         <div class='content'>
           <div id='kumulus-body'>
             <g:layoutBody/>
           </div>
         </div>
       </div>
  </body>
</html>
