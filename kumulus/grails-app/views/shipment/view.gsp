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
            <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
        <div class="pure-g">
            <div class="pure-u-3-4">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <table class="pure-table pure-table-horizontal" id="nodeTable">
                      <thead>
                        <tr>
                            <th>Item</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            
                        </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td colspan="3">Containers</td>
                             
                         </tr>
                         
                          <g:each var="node" status ="i" in="${nodeObj}">
                              <tr>
                             <td>${node.type.name}</td>
                             <td>${node.name}</td>
                             <td>${node}</td>
                         </tr>
                         </g:each>    
                        
                          <tr>
                             <td colspan="3">Products</td>
                             
                         </tr>
                          <g:each var="product"  in="${productObj}">
                              <tr>
                             <td>${product.name}</td>
                             <td>${product.description}</td>
                             <td></td>
                         </tr>
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
                                <input id="projectName" name="projectName" disabled type="text" value="${shipmentObj?.fromCompany[0]}" class="pure-u-23-24 kumulus-margin">
                            </div>
                            <div class="pure-control-group">
                                <label for="clientName">To Company</label>
                                <input id="ClientName" name="ClientName" disabled type="text" value="${shipmentObj?.toCompany[0]}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                            </div>
                            <div class="pure-control-group">
                                <label for="comment">Schedule Date</label>
                               <input id="scheduleDated" name="scheduleDate" readonly type="text" value="${dateFormat.format((java.util.Date)shipmentObj?.scheduled)}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                            </div>
<!--                                 <div class="pure-control-group">
                                <label for="comment">Schedule Date</label>
                               <input id="ClientName" name="ClientName" disabled type="text" value="${}"  class="pure-u-23-24 ui-widget kumulus-margin"></input>
                            </div>-->
                     
                        </g:form> 
                    </div>
                      <!--<input type="button" id="sendPickupNode" value="Send"  class="pure-button">-->
                </div>
            </div>
        </div>

       </g:form>
    </body>
</html>
