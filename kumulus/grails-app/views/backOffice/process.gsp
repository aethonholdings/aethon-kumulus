<html>
  <head>
    <title>Process document | Kumulus</title>
    <g:javascript src='jquery/validate/jquery.validate.js'/>
    <g:javascript src='kumulus/process.js'/>
  </head>
  <body>
    <form id="inputForm" class="pure-form pure-form-stacked">      
      <div class="kumulus-widget-base kumulus-widget-3-5">
        <div class="pure-g">
          <div class="pure-u-3-4">
            <div class="kumulus-preview">
              <iframe src='http://localhost:8080/kumulus/document/view/${document.id}#view=FitH&pagemode=thumbs&scrollbar=1&toolbar=1'
                      type='application/pdf' 
                      width='100%' 
                      height='95%'>
              </iframe>
            </div>
          </div>
          <div class="pure-u-1-4">
            <div class="kumulus-data-entry">
              <fieldset>
                <input type="hidden" id ="taskId" value="${task.id}"/>
                <input type="hidden" id ="taskType" value="${task.type}"/>
                <input type="hidden" id ="documentId" value="${document.id}"/>
                <input type="hidden" id ="pageCount" value="${document.pages.size()}"/>
                <div class="pure-control-group">
                  <label for="documentType">* Document type</label>
                  <g:select id="documentType" name="documentType" optionKey="id" optionValue="name" from="${documentTypes}"  value="${document.type.id.toString()}" class="pure-input-1" />
                </div>
                <div class="pure-control-group">
                  <label for="company">* Issuing company</label>
                  <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-1"></input>
                </div>
                <div class="pure-control-group">
                  <label for="date">* Date</label>
                  <input name ="date" placeholder="Select a date" type="date" value="${document.date?.format('yyyy-MM-dd')}" class="pure-input-1"/>
                </div>
                <div class="pure-control-group">
                  <label for="documentId">* Identifier</label>
                  <input id="documentId"  name="identifier" type=text value="${document.identifier}" class="pure-input-1"></input>
                </div>
              </fieldset>
            </div>
          </div>
        </div>
      </div>
    </form>
    <form id="lineItemForm" class="pure-form">
      <div class="kumulus-widget-base kumulus-widget-2-5 kumulus-scrollable-y">
        <div class="kumulus-line-item-table">
          <table id="lineItems" class="pure-table-horizontal">
            <thead>
              <th style="width:5%">*Page</th>
              <th style="width:40%">*Description</th>                  
              <th style="width:5%">Date</th>
              <th style="width:10%">Quantity</th>
              <th style="width:10%">*Currency</th>
              <th style="width:10%">Price</th>
              <th style="width:10%">*Amount</th>
              <th style="width:10%">Action</th>
            </thead>
            <tbody>
              <g:each var="page" in="${document?.pages.sort{it.number}}">
                <g:each var="lineItem" in="${page?.lineItems.sort{it.id}}">
                  <tr lineItemId="${lineItem.id}">
                    <td><input name="page-${lineItem.id}" type="text" value="${lineItem.page.number}" class="kumulus-column-page pure-input-1 kumulus-column-number"/></td>
                    <td><input name="description-${lineItem.id}" type="text" value="${lineItem.description}" class="kumulus-column-description pure-input-1"/></td>
                    <td><input name="date-${lineItem.id}" type="date" value="${lineItem.date?.format('yyyy-MM-dd')}" class="kumulus-column-date pure-input-1"/></td>
                    <td><input name="quantity-${lineItem.id}" type="text" value="${lineItem.quantity}" class="kumulus-column-quantity pure-input-1 kumulus-column-number"/></td>
                    <td><g:select name="currency-${lineItem.id}" from="${currencies}" class="kumulus-column-currency pure-input-1" optionKey="id" optionValue="shortName" value="${lineItem.currency.id}"/></td>
                    <td><input name="price-${lineItem.id}" type="text" value="${lineItem.price}" class="kumulus-column-price pure-input-1 kumulus-column-number"/></td>
                    <td><input name="amount-${lineItem.id}" type="text" value="${lineItem.amount}" class="kumulus-column-amount pure-input-1 kumulus-column-number"/></td>
                    <td><a class="remove" href="#">Remove</a></td>
                  </tr>
                </g:each> 
              </g:each>  
            </tbody>
          </table>
        </div>
        <p><input id="add" type="button" value="Add" class="pure-button"/></p>
      </div> 
    </form>
    <div class="kumulus-button-bank">
      <input type="button" id ="saveAndNext" value="Save and next" class="pure-button"></input>
      <input type="button" id ="save" value="Save" class="pure-button"></input>
    </div>
    <div id="template">
      <table>
        <tr id="template" lineItemId="">
          <td><input name="page!0" type="text" class="kumulus-column-page pure-input-1 kumulus-column-number"/></td>
          <td><input name="description!0" type="text" class="kumulus-column-description pure-input-1"/></td>
          <td><input name="date!0" type="date" class="kumulus-column-date pure-input-1"/></td>
          <td><input name="quantity!0" type="text" class="kumulus-column-quantity pure-input-1 kumulus-column-number" /></td>
          <td><g:select name="currency!0" from="${currencies}" class="kumulus-column-currency pure-input-1" optionKey="id" optionValue="shortName" value="127"/></td>
          <td><input name="price!0" type="text" class="kumulus-column-price pure-input-1 kumulus-column-number"/></td>
          <td><input name="amount!0" type="text" class="kumulus-column-amount pure-input-1 kumulus-column-number"/></td>
          <td><a class="remove" href="#">Remove</a></td>      
        </tr>
      </table>
    </div>
  </body>
</html>