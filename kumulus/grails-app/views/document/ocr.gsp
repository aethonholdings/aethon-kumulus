<html>
  <head>
    <title>OCR data entry | Kumulus</title>
    <g:javascript src='kumulus/preview.js'/>
    <g:javascript src='kumulus/autocomplete.js'/>
    <g:javascript src='kumulus/lineItems.js'/>
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
              <legend>Document tags</legend>
              <fieldset>
                <div class="pure-control-group">
                  <label for="documentType">* Document type</label>
                  <select id="documentType" name="documentType" value="${document.type}" class="pure-input-2-3">
                    <g:each var="documentType" in='${documentTypes}'>
                      <option value="${documentType.id}" <g:if test="${documentType.id==4}">selected</g:if>>${documentType.name}</option>
                    </g:each>
                  </select>
                </div>
                <div class="pure-control-group">
                  <label for="company">* Issuing company</label>
                  <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-2-3 ui-widget"></input>
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
                <input id="lineItemId" name="lineItemId" type="hidden" value=""></input>
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
                    <g:each var="currency" in="${currencies}">
                      <option value="${currency.fullName}" <g:if test="${currency.shortName=='SGD'}">selected</g:if>>${currency.fullName}</option>
                    </g:each>
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
                <div class="kumulus-button-bank">
                  <a class="pure-button" href="#" onclick="addLineItem();">Save</a>
                </div>
              </fieldset>
            </g:form>
            <div class="kumulus-line-item-table">
              <table id="lineItems">
                <thead>
                  <th>Id</th>
                  <th>Page</th>
                  <th>Date</th>
                  <th>Description</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Currency</th>
                  <th>Amount</th>
                </thead>
                <tbody>
                  <g:each var="page" in="${document.pages}">
                    <tr>
                      <td>hihi</td>
                    </tr>
                  </g:each>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="kumulus-button-bank">
          <a class="pure-button" href="#">Done</a>
        </div>
      </div>
    </div>
  </body>
</html>