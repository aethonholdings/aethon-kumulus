$(document).ready(function(){    
    
    // initialise the view objects
    var containerViewer = new ContainerViewer("#containerViewer");
    var containerContentViewer = new ContainerContentViewer("#containerContentViewer");
    var nodeWorkflow = new NodeWorkflow("#workflow");
    
    var searchPane = new SearchPane("#search", function(searchResult, type){
        tree.expandKeypath(searchResult.keypath);
        if(type=="document" && searchResult.documentId!=-1) {        
            new DocumentViewer(searchResult.documentId);
        }
    });
    
    var logisticsController = new LogisticsController("#nodeActions", function() {
        var node = tree.getSelectedNode();
        tree.reloadChildren(node.getParent(), function(){
            tree.getTree().activateKey(node.data.key);
        });
    });
    
    var tree = new NodeTree("#nodeTree", $('#project').attr('projectID'), function(node) {
        containerViewer.update(node);
        containerContentViewer.update(node);
        logisticsController.update(node);
    });
    
    // perform event bindings
    $("#button-add").click(function(){
        var parent = tree.getSelectedNode();
        if(!parent) { 
            alert("Please select a parent node first");
            return false; 
        }
        parent.expand();
        nodeWorkflow.add(parent, function() { tree.reloadChildren(parent, function(){
                tree.getTree().activateKey(parent.data.key);
            });
        });
    });
    
    $("#button-edit").click(function(){
        var node = tree.getSelectedNode();
        if(!node) { 
            alert("Please select a node first");
            return false; 
        }
        nodeWorkflow.update(node, function() { tree.reloadChildren(node.getParent(), function() {
                tree.getTree().activateKey(node.data.key);
            }); 
        });
    });
    
    $("#button-delete").click(function(){
        var node = tree.getSelectedNode();
        if(!node) { 
            alert("Please select a node first");
            return false; 
        } else { 
            nodeWorkflow.delete(node);
            tree.removeNode(node);
        }
    });
    
});
