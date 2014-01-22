<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <title>Upload scans | Kumulus</title>
  </head>
  <body>
    <span id="project" projectID="${project.id}"/>  
    <div class="pure-g">
      <div class="pure-u-1-6">
        <div class="grid-element"><h3>Archive structure</h3></div>
        <div class="grid-element" style="overflow-y:auto;overflow-x:auto;">
          <div id="nodeTree" class="jstree-draggable"></div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="grid-element"><h3>Container information</h3></div>
        <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
          <fieldset>
            <div class="pure-control-group">
              <label for="barcode">* Barcode</label>
              <input id="barcode" type="text" placeholder="Scan container barcode" disabled>
            </div>
            <div class="pure-control-group">
              <label for="name">* Name</label>
              <input id="name" type="text" placeholder="Enter name here" class="pure-input-1-2" disabled>
            </div>
            <div class="pure-control-group">
              <label for="type">* Type</label>
              <select id="type" class="pure-input-1-4" disabled>
                <option>Container</option>  
                <option>Box</option>
              </select>
            </div>
            <div class="pure-control-group">
              <label for="comment">Comment</label>
              <textarea id="comment" type="text" class="pure-input-1-2" disabled rows="5"></textarea>
            </div>
          </fieldset>
        </form>
      </div>
      <div class="pure-u-1-3">
        <div class="grid-element"><h3>File upload</h3></div>
        <div id="uploader">
          <fileuploader:form  upload="image" 
                              successAction="process"
                              successController="image"
                              errorAction="error"
                              errorController="test"  id=""/>
        </div>
      </div>
    </div>
  </body>
</html>
