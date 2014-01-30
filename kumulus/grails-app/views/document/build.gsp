<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
    <g:javascript src='kumulus/preview.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-2">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-preview">
            <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container kumulus-element-border">
          <div class="pure-g">
            <div class="pure-u-7-8">    
              <div class="pure-g">
                <div class="pure-u-1-2">
                  <div class="kumulus-container kumulus-scrollable-y">
                    <div id="page-strip" class="kumulus-filmstrip">
                      <ul id="pages" class="connectedSortable">
                        <g:each var="document" in="${documents}">
                          <g:each var="page" in="${document.pages}">
                            <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onmousedown="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
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
