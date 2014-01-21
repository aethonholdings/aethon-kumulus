<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
    <g:javascript src='kumulus/review.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-2">
        <div id="inputTable">
          
        </div>
      </div>
      <div class="pure-u-1-12">
        <ul id="thumbnail-sortable" class="kumulus-thumbnail-sortable">
          <g:each in="${pages}">
            <li class="ui-state-default"><g:thumbnail page="${it}" width="100" height="140"/></li>
          </g:each>
        </ul>
      </div>
      <div class="pure-u-5-12">
        <img id="page" src="">
      </div>
    </div>
  </body>
</html>