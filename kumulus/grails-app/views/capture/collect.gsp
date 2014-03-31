<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <g:javascript src='kumulus/containerViewer.js'/>
    <g:javascript src='kumulus/disableDiv.js'/>
    <title>Prepare scans | Kumulus</title>
  </head>
  <body>
    <span id="project" projectID="${project.id}"></span>  
      <div class="pure-g">
        <div class="pure-u-1-4">
          <div class="kumulus-container">
            <div class="kumulus-widget kumulus-scrollable-y kumulus-scrollable-x">
              <div class="kumulus-widget-header">
                <span class="kumulus-widget-header-title">Archive structure</span>
                <span class="kumulus-widget-header-action"></span>
              </div>
              <div class="kumulus-node-tree">
                <div id="nodeTree" class="jstree-draggable"></div>
              </div>            
            </div>
          </div>
          <div class="kumulus-button-bank">
            <button type="button" id="button-add" class="pure-button kumulus-margin-bottom" onclick="add_node();">Add</button>
            <button type="button" id="button-edit" class="pure-button kumulus-margin-bottom" onclick="update_node();">Edit</button>
            <button type="button" id="button-delete" class="pure-button kumulus-margin-bottom" onclick="delete_node();">Delete</button>
            <button type="button" id="button-search" class="pure-button kumulus-margin-bottom" onclick="search_node();">Search</button>
          </div>
        </div>
        <div class="pure-u-1-2">
          <div class="kumulus-container">
            <div class="kumulus-widget">
              <div class="kumulus-widget-header">
                <span class="kumulus-widget-header-title">Workflow</span>
                <span class="kumulus-widget-header-action"></span>
              </div>
              <div class="kumulus-data-entry">
                <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
                  <fieldset>
                    <div class="kumulus-hight kumulus-control-group">
                      <div class="kumulus-barcode-image"> <img src="../../images/barcode.png" class="kumulus-image"  alt="no image"/> </div>
                      <label for="barcode" class="kumulus-label">* Stick the Barcode sticker on the container or Box and Scan the Barcode</label>
                      <input id="barcode" type="text" placeholder="Scan container barcode" disabled>
                    </div>  
                    <div class="kumulus-hight kumulus-control-group">
                      <div class="kumulus-barcode-image"> <img src="../../images/container-type.jpg" class="kumulus-image"  alt="no image"/> </div>
                      <label for="type" class="kumulus-label">* Please select a container type</label>
                      <select id="type" disabled>
                        <option value=""  selected="selected">Please Select Container Type</option>
                        <g:each in="${nodeTypes}" var="nodeType">
                          <option value="${nodeType?.name}">${nodeType?.name}</option>
                        </g:each>                  
                      </select>
                    </div>
                    <div class="kumulus-hight kumulus-control-group">
                      <div class="kumulus-barcode-image"> <img src="../../images/container-name.jpg" class="kumulus-image"  alt="no image"/> </div>
                      <label for="name" class="kumulus-label">* Please enter the container name</label>
                      <input id="name" type="text" placeholder="Enter name here" disabled>
                    </div>
                    <div class="kumulus-hight kumulus-control-group">
                      <div class="kumulus-barcode-image"> <img src="../../images/comment.png" class="kumulus-image"  alt="no image"/> </div>
                      <label for="comment" class="kumulus-label">Comments</label>
                      <textarea id="comment" type="text" disabled rows="5"></textarea>
                    </div>
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
          <div class="kumulus-button-bank">
            <button type="button" disabled id="button-save" class="pure-button pure-button-primary" onclick="save(); " >Save </button>
            <button type="button" id="button-cancel" class="pure-button" onclick="cancel();" disabled="disabled">Cancel</button>
          </div>
        </div>
        <div class="pure-u-1-4">
          <div class="kumulus-container">
            <div class="kumulus-widget-1-3">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Container Details</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-margin-left">
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
          </div>
          <div class="kumulus-widget-2-3 kumulus-scrollable-y">
            <div class="kumulus-container-for-collect">
              <div class="kumulus-widget-header">
                <span class="kumulus-widget-header-title">Container contents</span>
                <span class="kumulus-widget-header-action"><g:link controller="capture" action="upload" params='[id: "${project?.id}"]' class="kumulus-float-right">Upload</g:link></span>
              </div>
              <div class="kumulus-scrollable-y">
                <table id="pageInfo" class="pure-table pure-table-horizontal">
                  <thead>
                    <tr>
                      <th class="kumulus-td-width">Thumbnail</th>
                      <th>Status</th>
                    </tr>
                  </thead>
                  <tbody> 
                  </tbody>
                </table>
              </div>
            </div>
          </div>    
        </div>
        <div class="kumulus-button-bank">
          <g:link class="pure-button" controller="project" action="view" id="${project.id}">Project Home</g:link>
        </div>
      </div>            
    </div>
  </body>
</html>
