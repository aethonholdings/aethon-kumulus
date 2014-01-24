<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div id="kumulus-document-builder">
      <div class="kumulus-container">
        <div id="preview" scanId="" class="kumulus-preview"></div>
      </div>
      <div class="kumulus-container kumulus-element-border">
        <div id="page-strip" class="kumulus-filmstrip">
          <ul>
            <g:each var="document" in="${documents}">
              <g:each var="page" in="${document.pages}">
                <li><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onClick="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
              </g:each>
            </g:each>
          </ul>
        </div>
      </div>
      <div class="kumulus-container kumulus-element-border">
        <div id="document-strip" class="kumulus-filmstrip">
          <ul>
            <li></li>
          </ul>
        </div>
      </div>
      <div class="kumulus-container">
        <p><g:link controller="document" action="save" class="pure-button">Save</g:link></p>
      </div>
    </div>
  </body>
</html>
