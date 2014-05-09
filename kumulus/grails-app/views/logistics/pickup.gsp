<!--
  Pickup screen
-->
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
    <g:javascript src='kumulus/classes/ContainerViewer.js'/>
    <g:javascript src='kumulus/pickup.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-2-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Shipment information</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="pure-form pure-form-aligned">
              <div class="pure-control-group">
                <label for="barcodeScanner">Barcode</label>
                <input id="barcodeScanner" type="text" class="pure-u-1-2" placeholder="Scan a barcode to begin" autofocus/>
              </div>
              <div class="pure-control-group">
                <label for="company">Company</label>
                <input id="company" type="text" class="pure-u-1-2" disabled>
              </div>
              <div class="pure-control-group">
                <label for="date">Shipment date</label>
                <input id="date" type="date" class="pure-u-1-2" disabled>
              </div>
            </div>
            <div id="containerViewer" class="pure-form pure-form-aligned">
              <div class="pure-control-group">
                <label for="type">Container type:</label>
                <input name="type" type="text" id="nodeType" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="location">Location:</label>
                <input name="location" type="text" id="nodeLocation" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="status">Status:</label>
                <input name="status" type="text" id="nodeStatus" class="pure-u-1-2" readonly=""/>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-3-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Item handover checklist</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="pure-g">
              <div class="pure-u-1-2">
                Customer
              </div>
              <div class="pure-u-1-2">
                Kumulus
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
