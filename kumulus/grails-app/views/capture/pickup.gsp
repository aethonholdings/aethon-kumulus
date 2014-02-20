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
        <div style="width:40%">
            <table id="nodeTable" style="border: 1px solid">
            <th>Name</th>
             <th>Barcode</th>
              <th>&nbsp;</th>
            </table>
        </div>
        
       <div id="divCalendar"></div>

       <input type="submit" value="Save">
       </g:form>
    </body>
</html>
