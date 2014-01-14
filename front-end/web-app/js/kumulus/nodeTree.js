var state;
var tree;
var selectedNode;
var newNode;

$(document).ready(function(){
    
    // create the node tree
    $('#nodeTree').dynatree({
        initAjax: {
            title: 'Archive structure',
            url: '/front-end/collect/getRoot/'+$('#project').attr('projectID'),
            minExpandLevel: 0,
            autoFocus: true,
            persist: true,
            clickFolderMode: 1, 
            selectMode: 1, 
            rootVisible: true,
            keyboard: true
        },
        onActivate: function(node) {
            if(state=="READY") {
                selectedNode = node;
                refresh_container_information(node);
            }
        }, 
        onLazyRead: function(node) {
            node.appendAjax({
                url: '/front-end/collect/getChildren/', 
                data : {
                    "id": node.data.key
                }
            });
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
    if(node && node.data.id!="ROOT"){
        $('#barcode').val(node.data.barcode);
        $('#name').val(node.data.title);
        $('#type').val(node.data.type);
        $('#comment').val(node.data.comment);
    } else {
        $('#barcode').val('Scan container barcode');
        $('#name').val('Enter name here');
        $('#type').val('');
        $('#comment').val('');
    }
}

function delete_node() {
    if(selectedNode && state=="READY"  && selectedNode.data.id!="ROOT") {
        if(confirm("Please confirm that you would like to delete this archive item")) {
            var data = { id: selectedNode.data.id }
            $.ajax({
                url: "/front-end/collect/delete/",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(data) {
                    selectedNode.remove();
                    tree.reload();
                    ready();
                }
            });
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
        $('#name').val('');
        $('#type').val('Container');
        $('#barcode').prop('disabled', false);
        $('#type').prop('disabled', false);
        $('#comment').prop('disabled', false);
        $('#nodeTree').prop('disabled', true);
        $('#barcode').focus();
        state = "INSERT";
    }
};

// --- EDIT STATE

function update_node() {
    if(selectedNode && state=="READY"  && selectedNode.data.id!="ROOT") {
        enable(false);
        $('#name').focus();
        state = "UPDATE";
    }
};

// --- INPUT INTERFACE ACTIONS

function enable(bool) {
    $('#barcode').prop('disabled', true);
    $('#type').prop('disabled', bool);
    $('#name').prop('disabled', bool); 
    $('#comment').prop('disabled', bool); 
    if(bool==false) tree.disable(); else tree.enable();
}

function cancel() {
    switch(state) {
        case "INSERT":
            newNode.remove();
            
        case "UPDATE":
            refresh_container_information(selectedNode);
            ready();
            break;
    }
}

function save() {
    
    if(!$("#barcode").val() || !$("#type").val() || !$("#name").val()) {
        alert("Please complete all required input fields")
        return(false);
    } else {
        var target, data;
        // need a case selection here
        switch(state) {
            case "UPDATE":
                data = {
                    id: selectedNode.data.id,
                    project: selectedNode.data.project,
                    barcode: $("#barcode").val(), 
                    type: $("#type").val(),
                    name: $("#name").val(),
                    comment: $("#comment").val()
                };
                target = "/front-end/collect/update/";
                break;

            case "INSERT":
                data = {
                    parentID: selectedNode.data.id,
                    project: selectedNode.data.project,
                    barcode: $("#barcode").val(), 
                    type: $("#type").val(),
                    name: $("#name").val(),
                    comment: $("#comment").val()
                };
                target = "/front-end/collect/insert/";
                break;
                
        }
        $.ajax({
            url: target,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
                success: function(data) {
                    tree.reload();
                    ready();
                }
        });
    }
}


