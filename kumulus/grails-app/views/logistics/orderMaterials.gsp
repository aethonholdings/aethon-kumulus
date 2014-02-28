<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <g:javascript src='kumulus/process.js'/>
        <title>Order Materials</title>
    </head>
    <body>
        <div class="pure-g kumulus-small-font">
            <div class="pure-u-1 kumulus-container kumulus-element-border">
                <h3>Specify the material you would like to order</h3>
                <div class="kumulus-container-ordermaterial-inside kumulus-scrollable-y">
                <table class='pure-table pure-table-horizontal' id="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Currency</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>    
                        <g:each var="product" in="${products}">
                            <tr onkeydown="send(this)" >
                            <td><g:img dir="images/products" file="/${product.imagePath}" class="kumulus-table-image"/></td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td><input id="quantity" type="text" value="" onblur="calculateTotalAmount(${product.price})" class="kumulus-small-input-box" onkeydown="CheckNumericWithoutDec(event)"></td>
                            <td>SGD</td>
                            <td><label id="totalAmount">0</label></td>
                          </tr> 
                         </g:each>
                          <tr class="kumulus-bold-font" onClick="send(this)">
                              <td> <label for="Order Total "style="right: 100px" class="kumulus-margin-left">Order Total</label></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td> <label id="grandtotal" class="">0</label></td>
                          </tr>
                    </tbody>
                </table> 
                 <div class="kumulus-float-right kumulus-margin-top">
                    <button type="button" id="button-SubmitOrder" class="pure-button pure-button-primary" style="float: right;" >Submit Order</button>
                    <button type="button" id="button-cancel" class="pure-button" style="float: right;">Cancel</button>
                 </div>
                </div>
            </div>
        </div>
    </body>
</html>
