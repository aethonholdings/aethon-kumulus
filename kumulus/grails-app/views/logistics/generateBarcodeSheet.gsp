<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<html>
  <head>
    <meta name="layout" content="main" />
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/kumulus', file: 'main.css')}'/>
    <title>Barcode Print Layout</title>
  </head>
  <body>
      <div id="kumulus-full-width" >
        <g:each var="barcode" in="${barcodes}">
          <!--<g:link controller="logistics" action="barcode" id="${barcode.id}">Barcode</g:link>-->
            <g:img uri="/logistics/barcode/${barcode.id}/" class="kumulus-barcode-layout kumulus-margin2"/>
        </g:each>
    </div>
  </body>
</html>
