var state;
var tree;
var selectedNode;
var newNode;

$(document).ready(function(){
    $.getJSON("/front-end/collect/refreshTree/"+$('#project').attr('projectID'), function(result){
        // create the node tree
        $('#nodeTree').dynatree({
            onActivate: function(node) {
                // A DynaTreeNode object is passed to the activation handler
                // Note: we also get this event, if persistence is on, and the page is reloaded.
                alert("You activated " + node.data.title);
            },
            persist: true,
            children: [ // Pass an array of nodes.
                {title: "Item 1"},
                {title: "Folder 2", isFolder: true,
                    children: [
                        {title: "Sub-item 2.1"},
                        {title: "Sub-item 2.2"}
                    ]
                },
                {title: "Item 3"}
            ]
        });
        // ready();
    });
});


// --- READY STATE
function ready() {
    toggle_input_disabled(true);
    state = "READY";
}

function refreshContainerInformation(node) {
    if(node){
        $('#barcode').val(node.original.barcode);
        $('#comment').val(node.original.comment);
        $('#type').val(node.original.type);
    } else {
        $('#barcode').val('Scan container barcode');
        $('#comment').val('Enter comments here');
        $('#type').val('Container');
    }
}

function delete_node() {
    selectedNode = tree.get_selected();
    if(selectedNode.length && selectedNode!='ROOT' && state=="READY") {
        if(confirm("Please confirm that you would like to delete this archive item")) {
            tree.delete_node(selectedNode);
            // need to perform JSON action here
            ready();
        }
    }
};

function refresh_tree(data) {
    $('#nodeTree').jstree({
        'core': {
            'data' : data,
            'multiple' : false,
            'check_callback' : true,
        }
    });
    tree = $('#nodeTree').jstree(true);
    tree.refresh(true);
}

// --- ADD NODE STATE

function add_node() {
    var selectedNode = tree.get_selected();
    if(!selectedNode.length) { return false; }
    selectedNode = selectedNode[0];
    newNode = tree.create_node(selectedNode, {'type':'C'});
    tree.open_node(selectedNode);
    if(newNode) {
        tree.redraw(false);
        $('#barcode').val('');
        $('#comment').val('');
        $('#type').val('Container');
        $('#barcode').prop('disabled', false);
        $('#type').prop('disabled', false);
        $('#comment').prop('disabled', false);
        $('#nodeTree').prop('disabled', true);
        $('#barcode').focus();
        state = "CREATE NEW";
    }
};

// --- EDIT STATE

function edit_node() {
    selectedNode = tree.get_selected();
    if(state=="READY" && selectedNode.length && selectedNode!="ROOT") {
        toggle_input_disabled(false);
        state = "EDIT";
    }
};

// --- INPUT INTERFACE ACTIONS

function toggle_input_disabled(bool) {
    $('#barcode').prop('disabled', true);
    $('#type').prop('disabled', bool);
    $('#comment').prop('disabled', bool); 
}

function cancel() {
    if(state=="CREATE NEW" || state=="EDIT") {
        tree.delete_node(newNode);
        tree.redraw(false);
    } else {
        var nodes = tree.get_selected();
        if(nodes.length) {
            var node = tree.get_node(nodes[0]);
            refreshContainerInformation(node);
        }
    }
    ready();
}

function save() {
    
    var data = {
        id: selectedNode.toString(),
        barcode: $("#barcode").val(), 
        type: $("#type").val(),
        comment: $("#comment").val()
    };
    
    if(state!="READY") {
        var target;
        if(state=="EDIT") target = "/front-end/collect/update/"; else target = "/front-end/collect/new/";
        $.ajax({
            url: target,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                refresh_tree(data);
                ready();
            }
        });
    }
}


