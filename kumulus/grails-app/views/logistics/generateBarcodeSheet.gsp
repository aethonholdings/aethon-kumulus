<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
    <g:each var="barcode" in="${barcodes}">
      <g:link controller="logistics" action="barcode" id="${barcode.id}">Barcode</g:link>
    </g:each>
  </body>
</html>
