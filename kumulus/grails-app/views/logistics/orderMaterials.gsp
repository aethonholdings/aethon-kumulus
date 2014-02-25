<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Materials</title>
    </head>
    <body>
        <div>
            <table class='pure-table pure-table-horizontal'>
                <thead>
                    <tr>
                        <th width="208px"></th>
                        <th width="208px"></th>
                        <th width="208px">Description</th>
                        <th width="208px">Price</th>
                        <th width="208px">Quantity</th>
                        <th width="208px">Amount</th>
                    </tr>
                </thead>
                <tbody>     
                    <tr>
                        <td><img src="#" class="kumulus-table-image"/></td>
                        <td>Barcode Sheets</td>
                        <td> 70 barcode/ sheets</td>
                         <td> 70 $</td>
                         <td> 70 </td>
                         <td> 4900$ </td>
                      </tr>
                      <tr>
                        <td><img src="#" class="kumulus-table-image"/></td>
                        <td>Boxes</td>
                        <td> Boxe sizes</td>
                         <td> 100$</td>
                         <td> 10 </td>
                         <td> 1000$ </td>
                      </tr>
                               
                </tbody>
            </table>  
        </div>
         <label for="Order Total "style="right: 100px" >Order Total</label>
        <button type="button" id="button-SubmitOrder" class="pure-button pure-button-primary" style="float: right;" >Submit Order</button>
        <button type="button" id="button-cancel" class="pure-button" style="float: right;">Cancel</button>
    </body>
</html>
