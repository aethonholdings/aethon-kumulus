<html>
  <head>
    <title>OCR data entry | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div class="kumulus-work-area pure-g">
      <div class="pure-u-1-8">
        <div class="kumulus-container kumulus-element-border">
          <div id="page-strip" class="kumulus-filmstrip">
            <ul id="pages">
              <g:each var="page" in="${document.pages}">
                <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onClick="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
              </g:each>
            </ul>
          </div>
        </div>
      </div>
      <div class="pure-u-3-8">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-preview">
            <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
          </div>
        </div>
      </div>
      <div class="pure-u-1-2">
        <div class="kumulus-container kumulus-element-border">
          <g:form name="document" action="update" id="${document.id}" class="pure-form pure-g">
            <div class="pure-u-1-3"><label for="documentType">* Type</label></div>
            <div class="pure-u-2-3"><input id="documentType" name="documentType" type="text" value="${document.type}"></input></div>
            <g:each var="${page}" in="${document.pages}">
              <g:each var="${lineItem}" in="${page.lineItems}">
              </g:each>
            </g:each>
          </g:form>
        </div>
      </div>
    </div>
  </body>
</html>