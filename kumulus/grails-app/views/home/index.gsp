<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
  </head>
  <body>
      <div class="pure-g kumulus-small-font">
      <div class="pure-u-1-2">
           <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
               <h3>You Have X Task Outstanding.</h3>
                <table class='pure-table pure-table-horizontal'>
                <thead>
                  <tr>
                    <th>Created Date</th>
                    <th>Type</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each in="${tasks}" var="task">
                    <tr>
                      <td>${task.created}</td>
                      <td>${task.type}</td>
                      <td><g:link controller="structure" action="process" id="${task.id}">Complete</g:link></td>
                    </tr>
                  </g:each>
                </tbody>
              </table>  
           </div>
      </div>
      <div class="pure-u-1-2">
         <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
            <h3>You are working on Y Projects.</h3>
             <table class='pure-table pure-table-horizontal'>
                <thead>
                  <tr>
                    <th>Date created</th>
                    <th>Type</th>
                    <th>Completed Task</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each var="task" in="${tasks}">
                    <tr>
                      <td>${task.created}</td>
                      <td>${task.type}</td>
                      <td><g:link controller="structure" action="" id="${task.id}">${task.status}</g:link></td>
                    </tr>
                  </g:each>
                </tbody>
              </table>  
         </div>
      </div>
     </div>
  </body>
</html>
