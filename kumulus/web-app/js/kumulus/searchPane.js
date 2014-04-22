function SearchPane(element) {
    instance = this;
    instance.element = $(element);
    instance.output = instance.element.find(".kumulus-search-outputs");
    var address = url("project", "search", "");
    
    this.search = function() {
        var queryString = instance.element.find(".pure-input-search").val();
        var project = instance.element.find(".pure-input-search-project").val();
        var data = {
            q: queryString,
            projectId: project
        };
        request(
                address,
                data,
                function(response) {
                    instance.output.empty();
                    instance.output.append("<p class='.kumulus-search-result'>" + response.total + " search results</p>");
                    instance.output.append("<ul>");
                    // alert(JSON.stringify(response));
                    for(var i=0; i<response.data.length; i++) {
                        instance.output.append("\t<li class='.kumulus-search-result'>" + response.data[i].nodeName + "</li>");
                    }
                    instance.output.append("</ul>");                
                }   
        );
    }
}

