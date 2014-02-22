<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
    <g:javascript src='kumulus/preview.js'/>
  </head>
  <body>
    <div class="pure-g">
         <div class="pure-u-1-4">
          <div class="kumulus-container kumulus-element-border pure-form">
              <div class="kumulus-data-entry">
               <fieldset>
                <div class="pure-control-group">
                  <label for="barcode">Barcode:</label>
                  <input id="barcode" type="textbox" name="barcode" class="pure-input-1" readonly></input>
                </div>
                <div class="pure-control-group">
                  <label for="containername">Container Name:</label>
                 <input id="containername" type="textbox" name="containername" class="pure-input-1" readonly/>
                <div class="pure-control-group">
                  <label for="type">Container Type:</label>                
                  <input id="containertype" type="textbox" name="type" class="pure-input-1" readonly></input>
                </div>
                <div class="pure-control-group">
                  <label for="comment">Comment:</label>
                  <input id="comment" type="textbox" name="comment" class="pure-input-1" readonly/>
                </div>
              </fieldset>
             </div>
          </div>
      </div>
      <div class="pure-u-2-5">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-preview">
            <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
          </div>
        </div>
      </div>
      <div class="pure-u-1-3">
        <div class="kumulus-container kumulus-element-border">
          <div class="pure-g">
            <div class="pure-u-7-8">    
              <div class="pure-g">
                <div class="pure-u-1-2">
                  <div class="kumulus-container kumulus-scrollable-y">
                    <div id="page-strip" class="kumulus-filmstrip">
                      <ul id="pages" class="connectedSortable">
                        <g:each var="task" in="${tasks}">
                          <g:each var="page" in="${task.document.pages.sort { it.number }}">
                            <li taskId ="${task.id}" documentId="${task.document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" /></li>
                          </g:each>
                        </g:each>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="pure-u-1-2">
                  <div class="kumulus-container kumulus-scrollable-y">
                    <div id="document-strip" class="kumulus-filmstrip">
                      <ul id="documents" class="connectedSortable"></ul> 
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="pure-u-1-8">
              <p><button class="pure-button" onClick="save();">Save</button></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
