<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <div class="pure-g">
            <div class="pure-u-1 kumulus-element-border kumulus-padding">
            <g:set var="haveQuery" value="${params.q?.trim()}" />
            <g:set var="haveResults" value="${searchResult?.results}" />
            <g:if test="${haveResults}">
                <p><strong>
                    Search Result:
                </strong></p>
                <g:each in="${searchResult.results}" status="i" var="doc">
//                    ${doc.class}
 //             </g:each>
            </g:if>
            <g:else>
                No Search Result
            </g:else>
            <div>
                <span>
                    <g:if test="${haveQuery && haveResults}">
                        Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
                        results for <strong>${params.q}</strong>
                    </g:if>
                    <g:else>
                        &nbsp;
                    </g:else>
                </span>
             </div>
             <g:if test="${haveQuery && !haveResults && !parseException}">
                    <p>Nothing matched your query - <strong>${params.q}</strong></p>
             </g:if>
             </div>
        </div>
    </body>
</html>
