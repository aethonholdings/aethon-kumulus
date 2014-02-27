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
          <g:javascript src='jquery/validate/validate.js'/>
          <g:javascript src='kumulus/validation.js'/>
          <g:javascript src='dynatree/jquery.dynatree.js'/>
          <g:javascript src='kumulus/nodeTree.js'/>
          
    </head>
    <body>
        <div class="pure-g kumulus-small-font">
         <div class="pure-u-1-5">
          <div class="kumulus-container-2-3 kumulus-element-border pure-form">
              <div class="kumulus-data-entry">
              <h3>Project Details</h3>
                <g:form name="edit" action="update" id="${project?.id}" class="pure-form">
               <div class="pure-control-group">
                   <label for="projectName">* Project name</label>
                    <input id="projectName" name="projectName" disabled type="text" value="${project?.projectName}" class="pure-u-23-24 kumulus-margin">
               </div>
               <div class="pure-control-group">
                    <label for="clientName">* Client name</label>
                    <input id="ClientName" name="ClientName" disabled type="text" value="${project.client.name}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                </div>
                <div class="pure-control-group">
                    <label for="comment">Comment</label>
                    <textarea id="comment" name="comment" disabled type="text" class="pure-u-23-24 kumulus-margin" rows="5">${project?.comment}</textarea>
                </div>
                <div class="kumulus-margin">
                    <input type="button" id="editProject" value="Edit" class="pure-button pure-button-primary">
                    <input type="submit" id="saveProject" value="Save" disabled class="pure-button pure-button-primary">
                </div>
             </g:form> 
              </div>
           </div>
           <div class="kumulus-container-1-5 kumulus-element-border kumulus-scrollable-y">
               <h3>Status Report</h3>
           </div>
        </div>
        <div class="pure-u-11-24">
             <span id="project" projectID="${project?.id}"/>
                  <div class="kumulus-container kumulus-element-border">
                      <h3>Archive Structure</h3>
                       <div class="kumulus-node-tree kumulus-container-inside-11-24 kumulus-scrollable-y">
                            <div id="nodeTree" class="jstree-draggable"></div>
                       </div>
                       <div class="kumulus-button-bank">
                           <g:link controller="capture" action="collect" params="[id: "${project?.id}"]" class="pure-button">Edit</g:link>
                       </div>
                   
                   <div class="kumulus-container-1-5">
                        <div id="search">
                            <div class="kumulus-search-margin"><label> Search </label>
                                <g:textField name="q" value="${params.q}" class="pure-input" placeholder="Enter search terms" size="30"/> 
                                <input type="submit" value="Search" class="pure-button"/>
                            </div>
                        </div>
                      </div> 
                     </div>
                </div>
                 <div class="pure-u-1-3">
                    <div class="kumulus-container kumulus-element-border">
                        <div class="kumulus-container-inside kumulus-scrollable-y">
                            <div class="pure-form">
                                <table>
                                    <tr>
                                        <td> Type:</td>
                                        <td> <input type="text" readonly=""/> </td>
                                    </tr>
                                    <tr>
                                        <td> Location:</td>
                                        <td> <input type="text" readonly=""/> </td>
                                    </tr>
                                    <tr>
                                        <td> Status:</td>
                                        <td> <input type="text" readonly=""/> </td>
                                    </tr>
                                </table>
                                <div>
                                    <input type="button"  value="Ready" class="pure-button kumulus-float-right"/>
                                </div>
                            </div>
                            
                            <div>
                                <table>
                                    <th>
                                        <td> &nbsp;</td>
                                        <td> Status</td>
                                    </th>
                                    <tr>
                                        <td> &nbsp;</td>
                                        <td> Status</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                            <g:link controller="capture" action="upload" params='[id: "${project?.id}"]' class="pure-button kumulus-float-right">Upload</g:link>
                        </div>  
                 </div>
                  
          </div>
          <div class="kumulus-float-right kumulus-padding kumulus-full-width kumulus-margin-right">
                      <input type="submit" value="Download Ledger" class="pure-button kumulus-matgin"/>
                      <input type="submit" value="Download Document" class="pure-button kumulus-margin"/>
                      <input type="submit" value="Close" class="pure-button kumulus-margin"/>
                      <input type="submit" value="Delete" class="pure-button kumulus-margin"/>
          </div>
    </body>
</html>
