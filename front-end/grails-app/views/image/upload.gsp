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
    <fileuploader:form	upload="image" 
			successAction="success"
			successController="test"
			errorAction="error"
			errorController="test"  id="${test.id}"/>
  </body>
</html>
