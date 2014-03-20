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
                    <h3>Project Details</h3>
                    <div class="kumulus-data-entry">
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
                    <div class="pure-u-2-3">
                        <div class="kumulus-node-tree kumulus-container-inside-11-24 kumulus-scrollable-y">
                            <div id="nodeTree" class="jstree-draggable"></div>
                        </div>
                    </div>
                    <div class="pure-u-7-24">
                        <div class="kumulus-button-bank">
                            <g:link controller="capture" action="collect" params='[id: "${project?.id}"]' class="pure-button">Collect paperwork</g:link>
                            </div>
                        </div>
                        <div class="kumulus-container-1-5">
                            <div id="search">
                                <div class="kumulus-search-margin"><label> Search </label>
                                <g:form url='[controller: "project", action: "search"]' id="searchableForm" name="searchableForm" method="get">
                                    <g:textField name="q" value="${params.q}" class="pure-input" placeholder="Enter search terms" size="30"/> 
                                    <input type="submit" value="Search" class="pure-button" onclick="show_advanced();"/>
                                </g:form>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            <div class="pure-u-1-3">
                <div class="kumulus-container-3-7 kumulus-element-border">
                    <h3>Container Details</h3>
                    <div class="pure-u-1-2 kumulus-margin-left">
                        <div class="pure-form kumulus-margin-bottom">
                            <div class="pure-control-group kumulus-margin-bottom">
                                <label for="barcode" class="pure-u-1-4">Barcode:</label>
                                <input name="barcode" type="text" id="nodeBarcode" class="pure-u-17-24" readonly=""/>
                            </div>
                            <div class="pure-control-group kumulus-margin-bottom">
                                <label for="type" class="pure-u-1-4">Type:</label>
                                <input name="type" type="text" id="nodeType" class="pure-u-17-24" readonly=""/>
                            </div>
                            <div class="pure-control-group kumulus-margin-bottom">
                                <label for="location" class="pure-u-1-4">Location:</label>
                                <input name="location" type="text" id="nodeLocation" class="pure-u-17-24" readonly=""/>
                            </div>
                            <div class="pure-control-group kumulus-margin-bottom">
                                <label for="status" class="pure-u-1-4">Status:</label>
                                <input name="status" type="text" id="nodeStatus" class="pure-u-17-24" readonly=""/>
                            </div>
                        </div>
                    </div>
                    <div class="pure-u-1-4 kumulus-margin-left">
                        <input id="button-readyfortransfer" type="button"  value="Ready for transport" class="pure-button" onclick="containerToTransport();" disabled/>
                        <input id="button-fetchFromStorage" type="button"  value="fetch From Storage" class="pure-button kumulus-margin-top" onclick="fetchFromStorage();" />
                        
                    </div>
                </div>
                <div class="kumulus-container-for-collect  kumulus-element-border">
                    <h3>Container Contents</h3>
                    <div class="kumulus-container-inside-for-collect kumulus-scrollable-y">

                        <table id="pageInfo" class="pure-table pure-table-horizontal">
                            <thead>
                                <tr>
                                    <th class="kumulus-td-width"> &nbsp;</th>
                                    <th> Status</th>
                                </tr>
                            </thead>
                            <tbody> 

                            </tbody>
                        </table>
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
