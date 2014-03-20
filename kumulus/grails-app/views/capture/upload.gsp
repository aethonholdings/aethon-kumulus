<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/base.js'/>
    <g:javascript src='kumulus/uploader.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <title>Upload scans | Kumulus</title>
  </head>
  <body>
    <span id="project" projectID="${project.id}"/>  
      <div class="pure-g">
        <div class="pure-u-1-6">
          <div class="kumulus-container kumulus-element-border">
            <div id="nodeTree" class="jstree-draggable"></div>
          </div>
        </div>
        <div class="pure-u-1-2">
          <div class="kumulus-container kumulus-scrollable-x kumulus-scrollable-y kumulus-element-border">
            <div class="kumulus-data-entry"> 
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
                       <g:each in="${nodeTypes}" var="nodeType">
                            <option value="${nodeType?.name}">${nodeType?.name}</option>
                       </g:each> 
                    </select>
                  </div>
                  <div class="pure-control-group">
                    <label for="comment">Comment</label>
                    <textarea id="comment" type="text" class="pure-input-1-2" disabled rows="5"></textarea>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
        </div>
        <div class="pure-u-1-3">
          <div class="kumulus-container kumulus-element-border">
            <div class="kumulus-data-entry">
                <form action="/kumulus/fileUploader/process/-1" method="post" enctype="multipart/form-data" class="kumulus-uploader-form">
                  <input type='hidden' name='upload' value='image' />
                  <input type='hidden' name='errorAction' value='error' />
                  <input type='hidden' name='errorController' value='test' />
                  <input type='hidden' name='successAction' value='upload' />
                  <input type='hidden' name='successController' value='image' />
                  <input type='file' name='file' class='kumulus-uploader pure-button-disabled' disabled/>
                  <input type='submit' name='submit' value='Submit' class='kumulus-uploader pure-button-disabled' disabled/>
		</form>
            </div>
          </div>
        </div>
      </div>
    </span>
  </body>
</html>
