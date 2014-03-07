<html>
  <head>
      <title>Edit project | Kumulus</title>
      <g:javascript src='jquery/validate/validate.js'/>
      <g:javascript src='kumulus/validation.js'/>
  </head>
  <body>
      <div class="pure-g">
          <div class="pure-u-1">
              <div class="kumulus-container kumulus-scrollable-y kumulus-padding">
                 <g:form name="shipment" action="createShipment"  class="pure-form pure-form-aligned">
                  <div class="pure-control-group">
                    <label for="fromCompany">* From Company</label>
                    <input id="fromCompany" name="fromCompany" type="text" value="${project?.projectName}" class="pure-input-1-2">
                  </div>
                  <div class="pure-control-group">
                    <label for="scheduleDate">* Schedule Date</label>
                    <input id="scheduleDate" name="scheduleDate" type="text"  class="pure-input-1-2 ui-widget"></input>
                  </div>
                 <div class="pure-control-group">
                    <label for="notes">Notes</label>
                    <textarea id="notes" name="notes" type="text" class="pure-input-1-2" rows="5">${project?.comment}</textarea>
                  </div>
                  <div class="pure-controls">
                    <g:link controller="home" action="index" class="pure-button">Cancel</g:link>
                    <input type="submit" id="createShipment" value="Save" class="pure-button pure-button-primary kumulus-button-align" onclick="validate();"></input>
                    <g:submitButton value="Save and Create Shipment Items" name="Save and Create" action="createShipment" params="[flag:'imp']" class="pure-button pure-button-primary kumulus-button-align" onclick="validate();"/>
                  </div>
               </g:form>
              </div>
        </div>
      </div>
  </body>
</html>