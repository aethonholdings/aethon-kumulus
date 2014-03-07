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

        <div id="nodeDialog" title="Add Nodes">
            <table id="nodeTableDialog" class="kumulus-shipment-table pure-table-horizontal">
                <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>Name</th>
                    </tr>
                </thead>
            </table>
        </div>
        <g:form name="document" action="update">
            <% SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); %>
            <div class="pure-g">
                <div class="pure-u-3-4">
                    <div class="kumulus-container kumulus-scrollable-y kumulus-element-border kumulus-shipment">
                        <div class="kumulus-container-1-2 kumulus-scrollable-y kumulus-element-border">
                        <table class="kumulus-shipment-table pure-table-horizontal" id="nodeTable">
                            <h3>Delivery</h3>
                            <thead>
                                <tr>
                                    <th width="10%">&nbsp;</th>
                                    <th>Item</th>
                                    <th>Description</th>
                                    <th>Quantity</th>

                                </tr>
                            </thead>
                            <tbody>
                                 <tr>
                                    <td colspan="4"><div class="kumulus-shipment-sub-subheader">Containers</div></td>
                                </tr>

                                <g:each var="node"  in="${nodeList}">
                                    <g:if test="${node.delivery==1}">
                                        <tr>
                                            <td><input type="checkBox" name="shipItem" id="${node.id}"></td>
                                            <td><div class="kumulus-shipment-details">${node.nodeObj.name}</div></td>
                                            <td>${node.nodeObj.type.name}</td>
                                            <td>${node.quantity}</td>
                                        </tr>
                                    </g:if>
                                </g:each>  
                                <tr>
                                     <td colspan="4"><input type="button" id="removeNode" deliveryId="1" name="Remove" value="Remove" class="button-space pure-button kumulus-float-right">
                                        <g:hiddenField name="shipmentId" id="shipmentId" value="${shipmentObj.id[0]}" />
                                        <input type="button" id="addNode" deliveryId="1" name="add" value="Add" class="button-space pure-button kumulus-float-right"></td>
                                </tr>
<!--                                <tr>
                                    <td colspan="4"><div class="kumulus-shipment-sub-subheader">Products</div></td>
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
                                    <td colspan="4">
                                           <g:link controller="home" action="index"  class="button-space pure-button kumulus-float-right">Remove</g:link>
                                           <g:link controller="logistics" action="orderMaterials"  params='[shipmentId: "${shipmentObj.id[0]}"]' class="button-space pure-button kumulus-float-right">Add</g:link>
                                    </td>
                                </tr>-->
                               </tbody>
                             </table>    
                           </div>
                           <div class="kumulus-container-1-2 kumulus-scrollable-y kumulus-element-border">
                              <h3>Pickup</h3> 
                            <table class="kumulus-shipment-table pure-table-horizontal" id="nodeTable">
                                <thead>
                                <tr>
                                    <th width="10%">&nbsp;</th>
                                    <th>Item</th>
                                    <th>Description</th>
                                    <th>Quantity</th>

                                </tr>
                            </thead>
                                 <tbody>    
                       
                                <tr>
                                    <td colspan="4"><div class="kumulus-shipment-sub-subheader">Containers</div></td>
                                </tr>
                                <g:each var="node"  in="${nodeList}">
                                    <g:if test="${node.delivery==2}">
                                        <tr>
                                            <td><input type="checkBox" name="shipItem" id="${node.id}"></td>
                                            <td>${node.nodeObj.name}</td>
                                            <td>${node.nodeObj.type.name}</td>
                                            <td>${node.quantity}</td>
                                            <td></td>
                                        </tr>
                                    </g:if>
                                </g:each>  
                                <tr>
                                    <td colspan="4">
                                        <input type="button" id="removeNode1" deliveryId="1" name="Remove" value="Remove" class="button-space pure-button kumulus-float-right">
                                        <input type="button" id="addNode1" deliveryId="2" name="add" value="Add" class="button-space pure-button kumulus-float-right">
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="4"><div class="kumulus-shipment-sub-subheader">Products</div></td>
                                </tr>
                                <g:each var="product"  in="${productList}">
                                    <g:if test="${product.delivery==1}">
                                        <tr>
                                            <td><input type="checkBox" name="prodItem" id="${product.id}"></td>
                                            <td>${product.productObj.name}</td>
                                            <td>${product.productObj.description}</td>
                                            <td>${product.quantity}</td>
                                            <td></td>
                                        </tr>
                                    </g:if>
                                </g:each>
                                    <tr>
                                     <td colspan="4">
                                          <g:link controller="home" action="index"  class="button-space pure-button kumulus-float-right">Remove</g:link>
                                           <g:link controller="logistics" action="orderMaterials"  params='[shipmentId: "${shipmentObj.id[0]}"]' class="button-space pure-button kumulus-float-right">Add</g:link>
                                               
<!--                                        <input type="button" id="removeProducts" deliveryId="1" name="removeProducts" value="Remove" class="button-space pure-button kumulus-float-right">
                                        <input type="button" id="addProducts" deliveryId="2" name="addProducts" value="Add" class="button-space pure-button kumulus-float-right">-->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                      </div>  
                    </div>
                </div>
                <div class="pure-u-1-4">
                    <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                         <div class="kumulus-div kumulus-data-entry">
                            <g:form name="edit" action="update" id="${project?.id}" class="pure-form">

                                <div class="pure-control-group">
                                    <label for="comment">Schedule Date</label>
                                    <input id="scheduleDated" name="scheduleDated" readonly type="text" value="${}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>                             
                                </div>

                            </g:form> 
                            
                        </div>
                           <g:link controller="home" action="index"  class="pure-button kumulus-float-right">Done</g:link>
                     </div>
                </div>
            </div>

        </g:form>
    </body>
</html>

