<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div class="kumulus-work-area pure-g">
      <div class="pure-u-1-2">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-preview">
            <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
          </div>
        </div>
      </div>
      <div class="pure-u-1-3">
        <div class="kumulus-container kumulus-element-border">
          <div class="pure-g">
            <div class="pure-u-1-2">            
              <div id="page-strip" class="kumulus-filmstrip">
                <ul id="pages" class="connectedSortable">
                  <g:each var="document" in="${documents}">
                    <g:each var="page" in="${document.pages}">
                      <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onClick="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
                    </g:each>
                  </g:each>
                </ul>
              </div>
            </div>
            <div class="pure-u-1-2">
              <div id="document-strip" class="kumulus-filmstrip">
                <ul id="documents" class="connectedSortable"></ul> 
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-1-6">
        <div class="kumulus-container">
          <p><button class="pure-button" onClick="save();">Save</button></p>
        </div>
      </div>
    </div>
  </body>
</html>
