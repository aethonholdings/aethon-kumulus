<!--
  Logistics home
-->
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <g:javascript src='kumulus/schedule.js'/>
    <title>Logistics schedule | Kumulus</title>
  </head>
  <body>
    <div class="kumulus-container">
      <div class="kumulus-widget-base kumulus-widget">
        <div class="kumulus-widget-header">
          <span class="kumulus-widget-header-title">Schedule</span>
          <span class="kumulus-widget-header-action"></span>
        </div>
        <div id="logisticsScheduler" class="pure-g">
          <div id="serviceRequests" class="pure-u-1-6">
            <h3 class="kumulus-scheduler-header">Service requests</h3>
            <div class="kumulus-scrollable-y kumulus-scheduler">
              <ul id="unscheduled" class="connectedSortable">
                <g:each var="pickup "in="${pickups}">
                  <li class="">
                    <input class="kumulus-customerId" type="hidden" value="${pickup.key.id}"/>
                    ${pickup.key.name}
                  </li>
                </g:each>
              </ul>
            </div>
          </div>
          <g:each status="i" var="day" in="${calendar}">
            <div class="pure-u-1-6">
              <h3 class="kumulus-scheduler-header">${day.format("EEE MMM dd")}</h3>
              <div class="kumulus-scheduler kumulus-scrollable-y">
                <ul id="scheduleDate${i}" date="${day.getDateTimeString()}" class="scheduled connectedSortable">
                  
                </ul>
              </div>
            </div>
          </g:each>
        </div>
        <div class="kumulus-button-bank">
          <a href="#" id="save" class="pure-button">Save</a>
        </div>
      </div>
    </div>
  </body>
</html>