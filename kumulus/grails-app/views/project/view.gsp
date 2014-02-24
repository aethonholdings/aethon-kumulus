<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
         <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
          <g:javascript src='Validate/validate.js'/>
          <g:javascript src='kumulus/validation.js'/>
          <g:javascript src='dynatree/jquery.dynatree.js'/>
          <g:javascript src='kumulus/nodeTree.js'/>
          
    </head>
    <body>
        <div class="pure-g">
         <div class="pure-u-2-5">
          <div class="kumulus-container kumulus-element-border pure-form">
             <g:form name="edit" action="" id="${project?.id}" class="pure-form pure-form-aligned">
               <div class="pure-control-group">
                    <label for="projectName">* Project name</label>
                    <input id="projectName" name="projectName" disabled type="text" value="${project?.projectName}" class="pure-input-1-2">
               </div>
               <div class="pure-control-group">
                    <label for="clientName">* Client name</label>
                    <input id="ClientName" name="ClientName" disabled type="text"  class="pure-input-1-2 ui-widget"></input>
                </div>
                <div class="pure-control-group">
                    <label for="comment">Comment</label>
                    <textarea id="comment" name="comment" disabled type="text" class="pure-input-1-2" rows="5">${project?.comment}</textarea>
                </div>
                <div class="pure-controls">
                    <g:link controller="project" action="list" params="[type: 'manage']" class="pure-button">Cancel</g:link>
                    <input type="button" id="editProject" value="Edit" class="pure-button pure-button-primary">
                    <input type="submit" id="saveProject" value="Save" disabled class="pure-button pure-button-primary">
                </div>
             </g:form> 
           </div>
        </div>
        <div class="pure-u-1-3">
             <span id="project" projectID=1/>
                  <div class="kumulus-container-half kumulus-element-border ">
                       <div class="kumulus-node-tree kumulus-container-inside-half kumulus-scrollable-y">
                            <div id="nodeTree" class="jstree-draggable"></div>
                       </div>
                       <div class="kumulus-button-bank">
                           <g:link controller="capture" action="collect" params="[id: 1]" class="pure-button">Edit</g:link>
                       </div>
                   </div>
                   <div class="kumulus-container-half kumulus-element-border">
                        <div id="search">
                            <div class="kumulus-search-magrin"><label> Search </label>
                                <g:textField name="q" value="${params.q}" class="pure-input" placeholder="Enter search terms" size="30"/> 
                            </div>
                            <div class="kumulus-float-right"><input type="submit" value="Search" class="pure-button"/></div>
                        </div>
                      </div>    
                </div>
                 <div class="pure-u-1-4">
                    <div class="kumulus-container kumulus-element-border">
                        <div class="kumulus-container-inside kumulus-scrollable-y">
                        </div>
                            <input type="submit" value="Delete" class="pure-button pure-button-primary kumulus-float-right"/>
                        </div>  
                 </div>
          </div>
    </body>
</html>
