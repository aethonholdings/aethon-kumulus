<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div id="kumulus-document-builder-work-area">
      <div class="pure-g">
        <div class="pure-u-1-2">
          <p>Preview</p>
          <div class="kumulus-document-builder-container">
            <div id="preview" ></div>
          </div>
        </div>
        <div class="pure-u-1-6">
          <p>Scanned pages</p>
          <div class="kumulus-document-builder-container">
            <div id="" class="kumulus-thumbnail-filmstrip">
              <ul>
                <g:each var="document" in="${documents}">
                  <g:each var="page" in="${document.pages}">
                    <li><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail" height="140" width="100" onClick="selectPage();"/></li>
                  </g:each>
                </g:each>
              </ul>
            </div>
          </div>
        </div>
        <div class="pure-u-1-6">
          <p>Document</p>
          <div class="kumulus-document-builder-container">
            <div id="" class="kumulus-thumbnail-filmstrip">
              <ul>
                <li></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="pure-u-1-6">
          <p><g:link controller="document" action="save" class="pure-button">Save</g:link></p>
        </div> 
      </div>
    </div>
  </body>
</html>
