<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div id="kumulus-document-builder-work-area">
      <div class="pure-g">
        <div class="pure-u-1-6">
          <div id="kumulus-document-builder-filmstrip">
            <ul class="kumulus-filmstrip">
              <g:each var="document" in="${documents}">
                <g:each var="page" in="${document.pages}">
                  <li><g:thumbnail page="${page}" height="140" width="100" class="kumulus-thumbnail-sortable"/></li>
                </g:each>
              </g:each>
            </ul>
          </div>
        </div>
        <div class="pure-u-5-6">
          <div id="kumulus-document-build-pane">
            <ul>
              <li>
                <ul>
                  <li></li>
                </ul>
              </li>
            </ul>
          </div>
        </div>    
      </div>
    </div>
  </body>
</html>
