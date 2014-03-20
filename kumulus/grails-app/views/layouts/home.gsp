<!DOCTYPE html>
<html lang='en'>
  <head>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
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
               <div class='pure-u-1-4'>
                 <div id='kumulus-logo'></div>
               </div>
               <div class='pure-u-3-4'>
                 <div class='kumulus-session-management kumulus-small-font'>
                   <g:userCompany/> |
                   <b><sec:loggedInUserInfo field='username'/></b> |  
                   <g:link controller='logout'> Logout</g:link>
                 </div>
               </div>
            </div>
         </div>
        </div>
        <div class='kumulus-menu'>
         <!--<a class='pure-menu-heading' href=''>kumulus</a>-->
          <ul>
                <li><g:link controller='home' action='index'>Home</g:link></li>
                <li><g:link controller='logistics' action='orderMaterials'>Order materials</g:link></li>
                <li><a href="#">Manage users</a></li>
                <li><a href="#">Billing</a></li>
                <li><a href="#">Manage account</a></li>
          </ul>
       </div>
       <div id='layout'>
         <div class='content'>
           <div id='kumulus-body'>
             <g:layoutBody/>
           </div>
         </div>
       </div>
  </body>
</html>
