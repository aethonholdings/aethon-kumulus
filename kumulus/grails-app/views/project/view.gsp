<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='jquery/validate/validate.js'/>
    <g:javascript src='kumulus/validation.js'/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <title>View project | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-5">
        <div class="kumulus-container">
          <div class="kumulus-widget">
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
                <div class="kumulus-button-bank">
                  <input type="button" id="editProject" value="Edit" class="pure-button pure-button-primary">
                  <input type="submit" id="saveProject" value="Save" disabled class="pure-button pure-button-primary">
                </div>
              </g:form> 
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-2-5">
        <span id="project" projectID="${project?.id}"></span>
        <div class="kumulus-container">
          <div class="kumulus-widget-4-5">
            <g:link controller="capture" action="collect" params='[id: "${project?.id}"]' class="kumulus-float-right kumulus-margin-top">Collect paperwork</g:link>
            <h3>Archive Structure</h3>
            <div class="kumulus-node-tree kumulus-scrollable-y">
              <div id="nodeTree" class="jstree-draggable"></div>
            </div>
          </div>
          <div class="kumulus-widget-1-5">
            <div id="search">
              <h3>Archive search</h3>
              <g:form url='[controller: "project", action: "search"]' id="searchableForm" name="searchableForm" method="get">
                <g:textField name="q" value="${params.q}" class="pure-input" placeholder="Enter search terms" size="45"/> 
                <input type="submit" value="Search" class="pure-button" onclick="show_advanced();"/>
              </g:form>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-2-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-2-5">
            <h3>Container Details</h3>
            <div class="pure-form pure-form-aligned">
              <div class="pure-control-group">
                <label for="barcode">Barcode:</label>
                <input name="barcode" type="text" id="nodeBarcode" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="type">Type:</label>
                <input name="type" type="text" id="nodeType" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="location">Location:</label>
                <input name="location" type="text" id="nodeLocation" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="status">Status:</label>
                <input name="status" type="text" id="nodeStatus" class="pure-u-1-2" readonly=""/>
              </div>
            </div>
            <div class="kumulus-button-bank">
              <input id="button-readyfortransfer" type="button"  value="Ready for transport" class="pure-button" onclick="containerToTransport();" disabled/>
              <input id="button-fetchFromStorage" type="button"  value="Fetch from storage" class="pure-button kumulus-margin-top" onclick="fetchFromStorage();" />
            </div>
          </div>
          <div class="kumulus-widget-3-5 kumulus-scrollable-y">
            <div class="kumulus-container-for-collect">
              <g:link controller="capture" action="upload" params='[id: "${project?.id}"]' class="kumulus-float-right">Upload</g:link>
              <h3>Container Contents</h3>
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
      </div>
    </div>
    <div class="kumulus-float-right">
      <input type="submit" value="Download Ledger" class="pure-button"/>
      <input type="submit" value="Download Document" class="pure-button"/>
      <input type="submit" value="Close" class="pure-button"/>
      <input type="submit" value="Delete" class="pure-button"/>
    </div>
  </body>
</html>
