<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->
<%@ page import="java.text.SimpleDateFormat;" contentType="text/html;charset=UTF-8" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
          <g:javascript src='kumulus/pickup.js'/>
    </head>
    <body>
           
       <g:form name="document" action="update">
<!--            <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %> -->
        <div class="pure-g">
            <div class="pure-u-3-4">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border kumulus-shipment">
                    <table class="kumulus-shipment-table pure-table-horizontal" id="nodeTable">
                      <thead>
                        <tr>
                            <th>Item</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            
                        </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td colspan="3"><div class="kumulus-shipment-subheader">Delivery</div></td>
                         </tr>
                         <tr>
                             <td colspan="3"><div class="kumulus-shipment-sub-subheader">Containers</div></td>
                             
                         </tr>
                         
                             <g:each var="node"  in="${nodeList}">
                              <g:if test="${node.delivery==1}">
                             <tr >
                                 <td><div class="kumulus-shipment-details">${node.nodeObj.name}</div></td>
                             <td>${node.nodeObj.type.name}</td>
                             <td>${node.quantity}</td>
                             </tr>
                              </g:if>
                         </g:each>  
                        
                        
                          <tr>
                              <td colspan="3"><div class="kumulus-shipment-sub-subheader">Products</div></td>
                             
                         </tr>
                          <g:each var="product"  in="${productList}">
                              <g:if test="${product.delivery==1}">
                              <tr>
                             <td><div class="kumulus-shipment-details">${product.productObj.name}</td>
                             <td>${product.productObj.description}</td>
                             <td>${product.quantity}</td>
                             <td></td>
                             </tr>
                               </g:if>
                         </g:each>  
                         <tr>
                             <td colspan="3"><div  class="kumulus-shipment-subheader">Pickup</div></td>
                             
                         </tr>
                         <tr>
                             <td colspan="3"><div class="kumulus-shipment-sub-subheader">Containers</div></td>
                             
                         </tr>
                         
                             <g:each var="node"  in="${nodeList}">
                                        <g:if test="${node.delivery==2}">
                              <tr>
                                  <td><div  class="kumulus-shipment-details">${node.nodeObj.name}</div></td>
                             <td>${node.nodeObj.type.name}</td>
                             <td>${node.quantity}</td>
                             <td></td>
                             </tr>
                             </g:if>
                         </g:each>  
                        
                        
                          <tr>
                              <td colspan="3"><div class="kumulus-shipment-sub-subheader">Products</div></td>
                             
                         </tr>
                          <g:each var="product"  in="${productList}">
                                     <g:if test="${product.delivery==2}">
                              <tr>
                                  <td><div class="kumulus-shipment-details">${product.productObj.name}</div></td>
                             <td>${product.productObj.description}</td>
                             <td>${product.quantity}</td>
                             <td></td>
                             </tr>
                             </g:if>
                             </g:each>
                     </tbody>
                    </table>
                  
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <div id="divCalendar"></div>
     
                      <div class="kumulus-data-entry">
                        <g:form name="edit" action="update" id="${project?.id}" class="pure-form">
                            <div class="pure-control-group">
                                <label for="projectName">From Company</label>
                                <input id="projectName" name="projectName" disabled type="text" value="${shipmentObj?.fromCompany}" class="pure-u-23-24 kumulus-margin">
                            </div>
                            <div class="pure-control-group">
                                <label for="clientName">To Company</label>
                                <input id="ClientName" name="ClientName" disabled type="text" value="${shipmentObj?.toCompany}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                            </div>
                            <div class="pure-control-group">
                                <label for="comment">Schedule Date</label>

                            </div>
<!--                                 <div class="pure-control-group">
                                <label for="comment">Schedule Date</label>
                               <input id="ClientName" name="ClientName" disabled type="text" value="${}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                            </div> -->
                     
                        </g:form> 
                    </div>
                      <!--<input type="button" id="sendPickupNode" value="Send"  class="pure-button">-->
                </div>
            </div>
        </div>

       </g:form>
    </body>
</html>
