<html>
  <head>
    <title>OCR data entry | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
  </head>
  <body>
    <div class="kumulus-work-area">
      <div class="kumulus-container kumulus-element-border">
        <div id="page-strip" class="kumulus-filmstrip">
          <ul id="pages">
            <g:each var="page" in="${document.pages}">
              <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onClick="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
            </g:each>
          </ul>
        </div>
      </div>
      <div class="kumulus-container kumulus-element-border">
        <div class="kumulus-preview">
          <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
        </div>
      </div>
      <div class="kumulus-container kumulus-element-border">
        <g:form name="document" action="update" id="${document.id}" class="pure-form pure-form-stacked">
          <div class="pure-control-group">
            <label for="documentType">* Identifier</label>
            <input id="documentType" name="documentType" type="text" value="${document.type}" class="pure-input-1-2"></input>
          </div>
          <div class="pure-control-group">
            <label for="documentID">* Document ID</label>
            <input id="documentID" name="documentID" type="text" value="${document.identifier}" class="pure-input-1-2"></input>
          </div>
          <div class="pure-control-group">
            <label for="company">* Issuing company</label>
            <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-1-2"></input>
          </div>
          <div class="pure-control-group">
            <label for="documentID">* Document Date</label>
            <input id="documentID" name="documentID" type="text" value="${document.identifier}" class="pure-input-1-2"></input>
          </div>
          <g:each var="${page}" in="${document.pages}">
            
            <g:each var="${lineItem}" in="${page.lineItems}">
              
            </g:each>
          </g:each>
        </g:form>
      </div>
    </div>
  </body>
</html>