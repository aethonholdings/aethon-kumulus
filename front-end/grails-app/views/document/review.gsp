<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-3"></div>
      <div class="pure-u-1-6">
        <g:each in="${documents}">
          <img src="/front-end/download/root/${it.node.project.folderName}/${it.folderName}/thumbs/1.jpg">
        </g:each>
      </div>
      <div class="pure-u-1-2"></div>
    </div>
  </body>
</html>