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
          <div class="kumulus-data-entry">
            <g:form name="document" action="update" id="${document.id}" class="pure-form pure-form-aligned">
              <fieldset>
                <div class="pure-control-group">
                  <label for="documentType">* Document type</label>
                  <input id="documentType" name="documentType" type="text" value="${document.type}" class="pure-input-2-3"></input>
                </div>
                <div class="pure-control-group">
                  <label for="company">* Issuing company</label>              
                  <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-2-3"></input>
                </div>
                <div class="pure-control-group">
                  <label for="date">* Date</label>                
                  <input id="date" name="date" type="date" value="${document.date}" class="pure-input-2-3"></input>
                </div>
                <div class="pure-control-group">
                  <label for="documentId">* Identifier</label>
                  <input id="documentId" name="documentId" type="text" value="${document.identifier}" class="pure-input-2-3"></input>
                </div>
              </fieldset>
              
              <table class="pure-table pure-table-horizontal">
                <thead>
                  <th> </th>
                  <th>Date</th>
                  <th>Description</th>
                  <th>Currency</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Amount</th>
                </thead>
                <g:each var="${page}" in="${document.pages}">
                  <g:each var="${lineItem}" in="${page.lineItems}">
                  </g:each>
                </g:each>
                <tr>
                  <td></td>
                  <td><input id="date" name="date" type="date" class="kumulus-data-entry-input"></input></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
              </table>
            </g:form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>