<%--
  Created by IntelliJ IDEA.
  User: damyant
  Date: 12/10/13
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: damyant
  Date: 12/10/13
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>HomePage</title>
</head>
<body>
<div class="header">

    <div class="headerBlock">
        <div class="logo">

        </div>
        <div class="sessionManagement">
            Welcome
            <sec:loggedInUserInfo field="username"/> |
            <g:link controller="#" action="#">Manage User</g:link>|
            <g:link controller="#" action="#">Logout</g:link>
        </div>

    </div>
</div>
<div class='main'>
    <div class='leftContentDiv'>

        <div class='menu'>
            <button class="actionButton" onclick="captureDocument()">CAPTURE DOCUMENTS</button>
        </div>
        <div class='menu'>
            <button class="actionButton" onclick="validateDocument()">VALIDATE DOCUMENTS</button>
        </div>
        <div class='menu'>
            <button class="actionButton" onclick="searchDocument()">SEARCH FOR DOCUMENTS</button>
        </div>
        <div class='menu'>
            <button class="actionButton" onclick="downloadDocument()">DOWNLOAD LEDGER </button>
        </div>
    </div>
    <div class="rightContentDiv">
        <div class='captureDocumentDiv' id='captureDocumentDiv'>
            <div>
                content for capture document...
            </div>
        </div>
        <div class='validateDocumentDiv' id='validateDocumentDiv'>
            <div>
                content for validate document...
            </div>
        </div>
        <div class='searchForDocumentDiv' id='searchForDocumentDiv'>
            <div>
                content for search document...
            </div>
        </div>
        <div class='DownloadDocumentDiv' id='DownloadDocumentDiv'>
            <div>
                content for download document...
            </div>
        </div>
    </div>
</div>
</body>
</html>