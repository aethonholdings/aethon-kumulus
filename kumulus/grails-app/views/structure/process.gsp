<%@ page import="java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>OCR data entry | Kumulus</title>
        <g:javascript src='jquery/validate/validate.js'/>
        <g:javascript src='kumulus/preview.js'/>
        <g:javascript src='kumulus/process.js'/>
        <g:javascript src='kumulus/validation.js'/>
    </head>
    <body>
        <g:form name="structure" action="save" id="${document.id}" class="pure-form pure-form-stacked">
            <% SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); %>
            <div class="pure-g">
                <div class="pure-u-1-8">
                    <div class="kumulus-container kumulus-element-border kumulus-scrollable-y">
                        <div id="page-strip" class="kumulus-filmstrip">
                            <ul id="pages">
                                <g:each var="page" in="${document.pages.sort{it.number}}">
                                    <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" pageId="${page.id}" pageNumber="${page.number}"/></li>
                                    </g:each>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="pure-u-7-8">
                    <div class="kumulus-container kumulus-element-border">
                        <div class="pure-g">
                            <div class="pure-u-3-4">
                                <div class="kumulus-container-half kumulus-element-border kumulus-scrollable-y">
                                    <div class="kumulus-preview">
                                        <img id="preview-img" onClick="zoom();">
                                    </div>
                                </div>
                                <div class="pure-u-1-4">
                                    <div class="kumulus-container-half kumulus-element-border">
                                        <div class="kumulus-data-entry">
                                            <fieldset>
                                                <input type="hidden" name ="taskId" value="${task.id}"/>
                                                <input type="hidden" name ="documentId" value="${document.id}"/>
                                                <div class="pure-control-group">
                                                    <label for="documentType">* Document type</label>
                                                    <g:select id="documentType" name="documentType" optionKey="id" optionValue="name" from="${documentTypes}"  value="${document.type.id.toString()}" class="pure-input-1" />

                                                </div>
                                                <div class="pure-control-group">
                                                    <label for="company">* Issuing company</label>
                                                    <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-1 ui-widget"></input>
                                                </div>
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
                                                    <g:select id="currency" name="currency" optionKey="id" optionValue="shortName" from="${currencies}" value="${document.pages.lineItems.currency.id[0][0]}" class="pure-input-1" />
                                                </div>
                                            </fieldset>
                                          </div>
                                       </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="kumulus-container-half-1 kumulus-element-border kumulus-margin-bottom kumulus-scrollable-y">
                            <div class="kumulus-line-item-table">
                                <table id="lineItems" class="pure-table-horizontal">
                                    <thead>
                                    <th>ID</th>
                                    <th>*Page</th>
                                    <th>*Description</th>                  
                                    <th>Date</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>*Amount</th>
                                    <th>Actions</th>
                                    </thead>
                                    <tbody class="kumulus-vertical-align-top">
                                        <g:each var="lineItem" in="${document.pages.lineItems[0]}">
                                           
                                            <tr class="new">
                                                <td><input id="lineItemId" name="lineItemId" size="4" type="text" value="" class="kumulus-column-id new" readonly></input></td>
                                                <td>
                                                    <input id="pageNo" name="pageNo" size="2" type="text" value="" class="kumulus-column-page new" onkeypress="CheckNumeric(event)" ></input>
                                                    <input id="pageId" name="pageId" type="hidden" value=""></input>
                                                </td>
                                                <td><input id="focus" name="description" size="25" type=text value="${lineItem.description}" class="kumulus-column-description new" ></input></td>
                                                <td><input id="lineItemDate" name="lineItemDate" size="4" type="date" value="${dateFormat.format((java.util.Date) lineItem.date)}" class="kumulus-column-date new"></input></td>
                                                <td><input id="quantity" name="quantity" type=text  size="6" value="${lineItem.quantity}" class="kumulus-column-quantity new" onkeydown="CheckNumeric(event)"></input></td>
                                                <td><input id="price" name="price" size="6" type="text" value="${lineItem.price}" class="kumulus-column-price new" onkeydown="CheckNumeric(event)" onchange="total(this)"></input></td>
                                                <td><input id="amount" name ="amount" size="6" type="text"  value="${lineItem.amount}" class="kumulus-column-amount new" onkeydown="CheckNumeric(event)" id="test"></input></td>
                                                <td><a class="remove" href="#">Remove</a></td>
                                            </tr>
                                        </g:each>  
                                    </tbody>
                                </table>
                                <div>
                                    <input type="button" id="add" value="Add" class="pure-button kumulus-float-right"></input>
                                </div>
                            </div>
                        </div>
                    </div>   
                    <div class="kumulus-button-bank">
                        <input type="button" id ="save" value="Save and next" class="pure-button"></input>
                        <input type="button" id ="only_save" value="Save" class="pure-button"></input>
                        <button type="button" id="button-cancel" class="pure-button">Cancel</button>

                    </div>
                </div>
            </g:form>
    </body>
</html>