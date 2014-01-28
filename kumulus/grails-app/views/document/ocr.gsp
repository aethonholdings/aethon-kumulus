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
          <div class="kumulus-data-entry kumulus-small-font">
            <g:form name="document" action="update" id="${document.id}" class="pure-form pure-form-aligned">
              <legend>Document tags</legend>
              <fieldset>
                <div class="pure-control-group">
                  <label for="documentType">* Document type</label>
                  <select id="documentType" name="documentType" value="${document.type}" class="pure-input-2-3">
                    <g:each var="currency" in="${currencies}">
                      <option value="${currency.fullName}">${currency.fullName}</option>
                    </g:each>
                  </select>
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

              <legend>Line items</legend>
              <fieldset>
                <div class="pure-control-group">
                  <label for="lineItemDate">Date</label>
                  <input id="lineItemDate" name="lineItemDate" type="date" class="pure-input-2-3"></input>
                </div>              
                <div class="pure-control-group">
                  <label for="lineItemDescription">Description</label>
                  <input id="lineItemDescription" name="lineItemDescription" type="text" class="pure-input-2-3"></input>
                </div>              
                <div class="pure-control-group">
                  <label for="lineItemQuantity">Quantity</label>
                  <input id="lineItemQuantity" name="lineItemQuantity" type="text" class="pure-input-2-3"></input>
                </div>
                <div class="pure-control-group">
                  <label for="lineItemCurrency">Currency</label>
                  <select id="lineItemCurrency" name="lineItemCurrency" class="pure-input-2-3">
                    <option value="test">Test<option>
                  </select>
                </div>
                <div class="pure-control-group">
                  <label for="lineItemPrice">Price</label>
                  <input id="lineItemPrice" name="lineItemPrice" type="text" class="pure-input-2-3"></input>
                </div>
                <div class="pure-control-group">
                  <label for="lineItemAmount">*Amount</label>
                  <input id="lineItemAmount" name="lineItemAmount" type="text" class="pure-input-2-3"></input>
                </div>
                <a class="pure-button" href="#">New</a>
                <a class="pure-button" href="#">Delete</a>
                <a class="pure-button" href="#">Save</a>
              </fieldset>
            </g:form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>