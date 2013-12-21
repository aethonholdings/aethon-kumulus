<%--
  Created by IntelliJ IDEA.
  User: damyant
  Date: 12/10/13
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home | Kumulus </title>
</head>
<body>
<div class="header">

    <div class="headerBlock">
        <div class="logo">

        </div>
        <div class="sessionManagement" style="color:white">
            Welcome
            <sec:loggedInUserInfo field="username"/> |
            <g:link controller="#" action="#">Manage User</g:link>|
            <g:link controller="#" action="#">Logout</g:link>
        </div>

    </div>
</div>
<div class='main'>
    <div class='leftContentDiv'>

        <div class='menu' style="margin-top: 5px">
            <span class="actionButton" onclick="myHome()" >MY HOME</span>
        </div>
        <div class='menu'>
            <span class="actionButton" onclick="captureDocument()">CAPTURE DOCUMENTS</span>
        </div>
        <div class='menu'>
            <span class="actionButton" onclick="validateDocument()">VALIDATE DOCUMENTS</span>
        </div>
        <div class='menu'>
            <span class="actionButton" onclick="searchDocument()">SEARCH DOCUMENTS</span>
        </div>
        <div class='menu'>
            <span class="actionButton" onclick="downloadDocument()">DOWNLOAD LEDGER </span>
        </div>
    </div>
    <div class="rightContentDiv">
        <div class='myHomeDiv' id='myHomeDiv'>
            <div id="states">
                <g:render template="myHomeContent"
                          model="[user: user, tasks:tasks]"
                         />
            </div>
        </div>
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