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
    <div class="kumulus-container">
      <div class="kumulus-widget kumulus-scrollable-y kumulus-element-border">
        
        <g:form name="edit" action="update" id="${project?.id}" class="pure-form">     
          <% SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); %>
          <g:hiddenField name="shipmentId" id="shipmentId" value="${shipmentObj.id[0]}" />

          <!--Date changer -->  
          <fieldset>
            <legend>Scheduled shipment date</legend>
            <input id="scheduleDated" name="scheduleDated" readonly type="text" value="${shipmentObj.scheduled[0].format('dd/MM/yyyy')}" class="pure-u-1-5 ui-widget kumulus-margin"></input>
          </fieldset>
          <!--Date end -->    

          <fieldset>
            <legend>Shipment items</legend>
            <div class="pure-g">
              
              <!--Pickup content -->      
              <div class="pure-u-1-2">  
                <h3>Pickup</h3>
                <table class="pure-table pure-table-horizontal kumulus-table-shipment">
                  <thead>
                    <tr>
                      <th width="5%">Select</th>
                      <th width="45%">Item</th>
                      <th width="50%">Description</th>
                    </tr>
                  </thead>
                  <tbody>
                    <g:each var="node"  in="${nodeList}">
                      <g:if test="${node.delivery==2}">
                        <tr>
                          <td><input type="checkBox" name="shipItem" id="${node.id}"></td>
                          <td>${node.nodeObj.name}</td>
                          <td>${node.nodeObj.type.name}</td>
                        </tr>
                      </g:if>
                    </g:each>  
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="3">
                        <input type="button" id="removeNode" deliveryId="1" name="Remove" value="Remove" class="button-space pure-button kumulus-float-right"/>
                        <input type="button" id="addNode" deliveryId="1" name="add" value="Add" class="button-space pure-button kumulus-float-right"/>
                      </td>
                    </tr>
                  </tfoot>
                </table>
              </div>
              <!--End pickup content -->

              <!--Delivery content -->
              <div class="pure-u-1-2">  
                <h3>Delivery</h3>
                <table class="pure-table pure-table-horizontal kumulus-table-shipment">
                  <thead>
                    <tr>
                      <th width="5%">Select</th>
                      <th width="45%">Item</th>
                      <th width="50%">Description</th>
                    </tr>
                  </thead>
                  <tbody>
                    <g:each var="node"  in="${nodeList}">
                      <g:if test="${node.delivery==1}">
                        <tr>
                          <td><input type="checkBox" name="shipItem" id="${node.id}"></td>
                          <td><div class="kumulus-shipment-details">${node.nodeObj.name}</div></td>
                          <td>${node.nodeObj.type.name}</td>
                        </tr>
                      </g:if>
                    </g:each>  
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="3">
                        <input type="button" id="removeNode1" deliveryId="1" name="Remove" value="Remove" class="button-space pure-button kumulus-float-right">
                      </td>
                    </tr>
                  </tfoot>
                </table> 
              </div>
              <!--End delivery content -->

            </div>
          </fieldset>
        </g:form>
      </div>
    </div>  
    
    <div class="kumulus-float-right">
      <g:link controller="home" action="index" class="pure-button">Done</g:link>
    </div>
    
    <!--Node selection dialog content -->
    <div id="nodeDialog" title="Add containers to shipment">
      <table id="nodeTableDialog" class="kumulus-shipment-table pure-table-horizontal">
        <thead>
          <tr>
            <th width="10%">Select</th>
            <th width="40%">Name</th>
            <th width="40%">Barcode</th>
          </tr>
        </thead>
        <tbody>
          
        </tbody>
      </table>
    </div>
    <!--End node selection dialog content -->
      
  </body>
</html>

