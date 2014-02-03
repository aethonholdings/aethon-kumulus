<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <title>Upload scans | Kumulus</title>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-data-entry">
            <fileuploader:form name="uploader" 
                class="pure-form"
                upload="image" 
                successAction="index"
                successController="document"
                errorAction="error"
                errorController="test"  
                documentId="10"
                disabled="false"
                class="pure-button-enabled"/>      
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
