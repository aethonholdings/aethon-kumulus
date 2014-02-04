<html>
    <head>
        <title>Access | Kumulus</title>
    </head>
    <body>
      <p>Search your archive</p>
      <div id="search">
        <g:form url='[controller: "search", action: "index"]' id="searchableForm" name="searchableForm" method="get">
          <g:textField name="q" value="${params.q}" class="pure-input" placeholder="Enter search terms" size="50"/> <input type="submit" value="Search" class="pure-button"/>
        </g:form>
      </div>
      <div id="advancedSearch">
        <a href="#" onclick="show_advanced();">Advanced search</a>
      </div>
    </body>
</html>