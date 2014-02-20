<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
     <g:javascript src='kumulus/pickup.js'/>
    </head>
    <body>
       <g:form name="document" action="update">
        <div class="pure-g">
            <div class="pure-u-3-4">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <table class="pure-table pure-table-horizontal">
                      <thead>
                        <tr>
                            <th>Name</th>
                            <th>Barcode</th>
                            <th>&nbsp;</th>
                        </tr>
                     </thead>
                      <tbody>
                       <tr>   
                          <td></td>
                          <td></td>
                          <td></td>
                       </tr>
                      </tbody>
                    </table>
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
                    <div id="divCalendar" ></div>
                    <input type="submit" value="Save">
                </div>
            </div>
        </div>
       </g:form>
    </body>
</html>
