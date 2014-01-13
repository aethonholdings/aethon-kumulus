var state;
var tree;
var selectedNode;
var newNode;

$(document).ready(function(){
    
    // create the node tree
    $('#nodeTree').dynatree({
        initAjax: {
            title: 'Archive structure',
            url: '/front-end/collect/getProject/'+$('#project').attr('projectID'),
            minExpandLevel: 1,
            autoFocus: true,
            persist: true,
            clickFolderMode: 1, 
            selectMode: 1
        },
        onActivate: function(node) {
            if(state=="READY") {
                selectedNode = node;
                refresh_container_information(node);
            }
        }
    });
    tree = $('#nodeTree').dynatree("getTree");
    ready();
});


// --- READY STATE
function ready() {
    enable(true);
    state = "READY";
}

function refresh_container_information(node) {
    if(node){
        $('#barcode').val(node.data.barcode);
        $('#comment').val(node.data.title);
        $('#type').val(node.data.type);
    } else {
        $('#barcode').val('Scan container barcode');
        $('#comment').val('Enter comments here');
        $('#type').val('');
    }
}

function delete_node() {
    if(selectedNode && state=="READY") {
        if(confirm("Please confirm that you would like to delete this archive item")) {
            selectedNode.remove();
            // need to perform JSON action here
            ready();
        }
    }
};

// --- ADD NODE STATE

function add_node() {
    if(!selectedNode) { return false; }
    newNode = selectedNode.addChild({
        title: "New",
        isFolder: true
    });
    selectedNode.expand();
    enable(false);
    if(newNode) {
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
    if(selectedNode && state=="READY") {
        enable(false);
        $('#type').focus();
        state = "EDIT";
    }
};

// --- INPUT INTERFACE ACTIONS

function enable(bool) {
    $('#barcode').prop('disabled', true);
    $('#type').prop('disabled', bool);
    $('#comment').prop('disabled', bool); 
    if(bool==false) tree.disable(); else tree.enable();
}

function cancel() {
    switch(state) {
        case "CREATE NEW":
            newNode.remove();
            break;
            
        case "EDIT":
            break;
            
        
    }

    refresh_container_information(selectedNode);
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


