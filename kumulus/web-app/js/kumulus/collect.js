var containerViewer;
var nodeWorkflow;
var tree
var searchPanel;

function add_node() {
    if(!tree.getSelectedNode()) { 
        alert("Please select a parent node first");
        return false; 
    }
    tree.getSelectedNode().expand();
    nodeWorkflow.show(null)
};

function update_node() {
    var node = tree.getSelectedNode()
    if(!node) { 
        alert("Please select a node");
        return false; 
    }
    nodeWorkflow.show(node)
};

function delete_node() {
    var selectedNode = tree.getSelectedNode()
    if(selectedNode && selectedNode.data.id!="ROOT") {
        if(confirm("Please confirm that you would like to delete this item")) {
            var data = { id: selectedNode.data.id }
            $.ajax({
                url: url('node', 'delete', ''),
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(data) {
                    tree.removeNode(selectedNode);
                }
            });
        }
    }
};

function search_node() {
    var barCode = prompt("Please scan barcode");
    var data = {barCode: barCode}
    $.ajax({
        url: url('node', 'searchByBarcode', ''),
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {
            if(data!="") {
                var keypath = "#";
                for(var i=data.length-1; i>=0; i--) {
                    keypath = keypath + "/" + data[i].key.toString();
                }
                tree.expandKeypath(keypath)
            } else {
                alert("Barcode not found in this project archive");
            }
        }
    });
}

function save_node(formData) {
    var nodeId, parentId, target, data;
    var selectedNode = tree.getSelectedNode();
    var keypath = selectedNode.getKeyPath();
    var key = selectedNode.data.key;
    var project = selectedNode.data.project;

    if(formData.node) {
        nodeId = selectedNode.data.id;
        parentId = selectedNode.data.parentId;
        target = url('node', 'update', '');
    } else {
        // this is a new node
        nodeId = null;
        parentId = selectedNode.data.id;
        target = url('node', 'insert', '');
    }

    data = {
        id: nodeId,
        project: project,
        parentId: parentId, 
        barcode: formData.barcode,
        type: formData.type,
        name: formData.name,
        comment: formData.comment
    };
    
    $.ajax({
        url: target,
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {
            if (selectedNode.data.key != '#') {
                if(formData.node) {
                    selectedNode.getParent().reloadChildren();
                } else {
                    selectedNode.reloadChildren();
                }
            }
            else {
                tree.reload();
            }
        }
    });
    tree.selectKey(key);
}

function search() {
    searchPanel.search();
}

function selectSearchResult(searchResult, type) {
    tree.expandKeypath(searchResult.keypath);
    if(type=="document" && searchResult.documentId!=-1) {        
        new DocumentViewer(searchResult.documentId);
    }
}


$(document).ready(function(){    
    containerViewer = new ContainerViewer("WRITE");
    nodeWorkflow = new NodeWorkflow("#workflow", save_node);
    searchPanel = new SearchPane("#search", selectSearchResult);
    tree = new NodeTree("#nodeTree", $('#project').attr('projectID'), function(node){
        containerViewer.update(node);
    });
});
