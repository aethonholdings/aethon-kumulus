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
            <div class="pure-u-2-3">
                <div class="kumulus-container kumulus-padding">
                <g:set var="haveQuery" value="${params.q?.trim()}" />
                <g:set var="haveResults" value="${searchResult?.results}" />
                <p ><h3> Search Result:</h3></p>
                <g:if test="${haveResults}">
                    <g:each in="${searchResult.results}" status="i" var="doc">
                       <g:searchResult result="${doc}"/>
                    </g:each>
                </g:if>
                <div class="kumulus-pagination">
                    <g:if test="${haveResults}"> <!-- or you could use test="${searchResult?.results}" -->
                       <p>
                           <h5> 
                                 Page:
                                <g:paginate params="[q: params.q]" total="${searchResult.total}"/>
                            </h5>
                        </p>
                    </g:if>
                </div>
                <div>
                    <g:if test="${haveQuery && haveResults}">
                        <p>  Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
                        results for :: <strong>${params.q}</strong></p>
                    </g:if>
                    <g:else>
                        &nbsp;
                    </g:else>
                 </div>
                 <g:if test="${haveQuery && !haveResults && !parseException}">
                        <p class="kumulus-margin-left">Nothing matched your query - <strong>${params.q}</strong></p>
                 </g:if>
                </div>
             </div>
             <div class="pure-u-1-3">
                <div class="kumulus-container-3-7 kumulus-element-border">
                    <h3>Container Details</h3>
                </div>
                <div class="kumulus-container-for-collect  kumulus-element-border">
                    <h3>Container Contents</h3>
                    <div class="kumulus-container-inside-for-collect kumulus-scrollable-y">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
