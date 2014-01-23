<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div id="kumulus-document-builder-work-area">
      <div class="pure-g">
        <div class="pure-u-1-2">
          <h3>Preview</h3>
          <div class ="kumulus-document-builder-container"></div>
        </div>
        <div class="pure-u-1-6">
          <h3>Scanned pages</h3>
          <div id="kumulus-document-builder-pagestrip" class="kumulus-document-builder-container">
            <ul class="kumulus-thumbnail-filmstrip kumulus-thumbnail-filmstrip-vertical">
              <g:each var="document" in="${documents}">
                <g:each var="page" in="${document.pages}">
                  <li><g:kumulusImg image="${page.thumbnailImage}" height="140" width="100" class="kumulus-thumbnail-sortable" onClick="selectPage();"/></li>
                </g:each>
              </g:each>
            </ul>
          </div>
        </div>
        <div class="pure-u-1-6">
          <h3>Document</h3>
          <div id="kumulus-document-builder-pagestrip" class="kumulus-document-builder-container">
            <ul class="kumulus-thumbnail-filmstrip kumulus-thumbnail-filmstrip-vertical">
              <li></li>
            </ul>
          </div>
        </div>
        <div class="pure-u-1-6">
          <h3>Commit</h3>
          <p><g:link controller="document" action="save" class="pure-button">Save</g:link></p>
        </div> 
      </div>
    </div>
  </body>
</html>
