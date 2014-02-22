 <html>
  <head>
    <title>OCR data entry | Kumulus</title>
    <g:javascript src='Validate/validate.js'/>
    <g:javascript src='kumulus/preview.js'/>
    <g:javascript src='kumulus/process.js'/>
    <g:javascript src='kumulus/validation.js'/>
   
</script>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-8">
        <div class="kumulus-container kumulus-element-border kumulus-scrollable-y">
          <div id="page-strip" class="kumulus-filmstrip">
            <ul id="pages">
              <g:each var="page" in="${document.pages.sort { it.number }}">
                <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" pageNumber="${page.number}"/></li>
              </g:each>
            </ul>
          </div>
        </div>
      </div>
      <div class="pure-u-7-8">
     <g:form name="document" action="update" id="${document.id}" class="pure-form pure-form-stacked">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-container-half">
            <div class="pure-g">
              <div class="pure-u-3-4">
                <div class="kumulus-container-half kumulus-element-border kumulus-scrollable-y">
                  <div class="kumulus-preview">
                    <img id="preview-img" onClick="zoom();">
                  </div>
                </div>
              </div>
              <div class="pure-u-1-4">
                <div class="kumulus-container-half kumulus-element-border">
                  <div class="kumulus-data-entry">
                
                      <fieldset>
                        <div class="pure-control-group">
                          <label for="documentType">* Document type</label>
                          <select id="documentType" name="documentType" value="${document.type}" class="pure-input-1">
                            <g:each var="documentType" in='${documentTypes}'>
                              <option value="${documentType.id}" <g:if test="${documentType.id==4}">selected</g:if>>${documentType.name}</option>
                            </g:each>
                          </select>
                        </div>
                        <div class="pure-control-group">
                          <label for="company">* Issuing company</label>
                          <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-1 ui-widget"></input>
                        <div class="pure-control-group">
                          <label for="date">* Date</label>                
                          <input type="date" name ="date" value="${document.date}" class="pure-input-1"></input>
                        </div>
                        <div class="pure-control-group">
                          <label for="documentId">* Identifier</label>
                          <input id="documentId"  name="identifier" type=text value="${document.identifier}" class="pure-input-1"></input>
                        </div>
                        <div>
                       <label for="documentId">*Currency</label>   
                      <select class="pure-input-1">
                        <g:each var="currency" in="${currencies}">
                          <option value="${currency.shortName}" <g:if test="${currency.shortName=='SGD'}">selected</g:if>>${currency.shortName}</option>
                        </g:each>
                      </select>
                    </td>
                         </div>
                      </fieldset>
                  
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="kumulus-container-half kumulus-element-border">
            <div class="kumulus-line-item-table">
              <table id="lineItems" class="pure-table-horizontal">
                <thead>
                  <th>Id</th>
                  <th>*Page</th>
                  <th>*Description</th>                  
                  <th>Date</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>*Amount</th>
                  <th>Actions</th>
                </thead>
               <tbody class="kumulus-vertical-align-top">
                <tr class="new" onclick="send($(this))">  
                    <td><input size="4" type="text" value="" class="kumulus-column-id new" disabled="true"></input></td>
                    <td><input size="2" type="text" value=" " class="kumulus-column-page new" id="pageNo" disabled="true"></input></td>
                    <td><input id="focus" name="description" size="25" type=text  value="" class="kumulus-column-description new" ></input></td>
                    <td><input size="4" type="date" value="" class="kumulus-column-date new"></input></td>
                    <td><input  type=text  size="6" value="" class="kumulus-column-quantity new" onkeypress="CheckNumeric(event)"></input></td>
               
                    <td><input size="6" type="text" value="" class="kumulus-column-price new" onkeypress="CheckNumeric(event)" onchange="total($(this))"></input></td>
                    <td><input size="6" type="text"  name ="tamount"value="" class="kumulus-column-amount new" onkeypress="CheckNumeric(event)" id="test"></input></td>
                    <td><a class="add" href="#" >Add</a></td>
                  </tr>
                </tboot>
              </table>
            </div>
          </div>
        </div>   
  
        <div class="kumulus-button-bank">
          <a class="pure-button" href="#">Next document</a>
         <input type="submit" value="Save" class="pure-button"></input>
         
          <!--<g:link class="pure-button" controller="document" action="update" id="${document.id}">Save and exit</g:link> -->
          <a class="pure-button" href="#">Exit</a>
        </div>
  </g:form>
      </div>
    </div>
  </body>
</html>