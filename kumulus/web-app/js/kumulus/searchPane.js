function SearchPane(element, callbackFunction) {
    var instance = this;
    instance.element = $(element);
    instance.output = instance.element.find(".kumulus-search-outputs");
    instance.results = [];
    instance.callbackFunction = callbackFunction;
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
                    instance.results = [];
                    instance.output.append("<p class='kumulus-search-result'>" + response.total + " search results</p>");
                    instance.output.append("<ol class='kumulus-search-result'>");
                    for(var i=0; i<response.data.length; i++) {
                        var result = new SearchResult(response.data[i], instance.callbackFunction);
                        instance.results.push(result);
                        instance.output.append(result.toString());
                        instance.output.find("li").filter(":last").find("a").filter(":first").bind("click", result.openNode);
                        instance.output.find("li").filter(":last").find("a").filter(":last").bind("click", result.openDocument);
                    }
                    instance.output.append("</ol>");
                });
    }
    instance.element.find("form").submit(function(e){
        instance.search();
        return false;
    });

}

function SearchResult(responseData, callbackFunction) {
    var instance = this;
    instance.nodeId = responseData.nodeId;
    instance.nodeName = responseData.nodeName;
    instance.nodeBarcode = responseData.nodeBarcode;
    instance.documentId = responseData.documentId;
    instance.callbackFunction = callbackFunction;
    instance.keypath = responseData.keypath;
    instance.documentName = responseData.documentName;
    instance.window;
    
    instance.open = function() {
        if(instance.documentId!=-1) {
            instance.window = window.open(url("document", "view", instance.documentId));
        }
    }
    
    instance.toString = function() {
        var output = "";
        output = output + "\t<li class='kumulus-search-result'>";
        output = output + "<b>Container:</b> <a href='#' class='kumulus-search-result-container'>";
        output = output + instance.nodeName;
        output = output + "</a>";

        if(instance.documentId!=-1) {
            // instance is a document, open the relevant document
            output = output + "<br><b>Document:</b> <a href='#' class='kumulus-search-result-document'>";
            output = output + instance.documentName;
            output = output + "</a>";
        }
        output = output +  "</li>";
        return(output);
    }
    
    instance.openNode = function() {
        instance.callbackFunction(instance, "node");
    }
    
    instance.openDocument = function() {
        instance.callbackFunction(instance, "document");
    }

}

