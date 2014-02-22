var state;
var tree;
var selectedNode;
var newNode;

$(document).ready(function(){

    // create the node tree
    $('#nodeTree').dynatree({
        initAjax: {
            title: 'Archive structure',
            url: url('node', 'getRoot', $('#project').attr('projectID')),
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
                url: url('node', 'getChildren', ''), 
                data : {
                    "id": node.data.key
                }
            });

        },      
     dnd: {
      onDragStart: function(node) {
       
        logMsg("tree.onDragStart(%o)", node);
        return true;
      },
      onDragStop: function(node) {
       
        logMsg("tree.onDragStop(%o)", node);
      },
      autoExpandMS: 1000,
      preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
      onDragEnter: function(node, sourceNode) {
       
        logMsg("tree.onDragEnter(%o, %o)", node, sourceNode);
        return true;
      },
      onDragOver: function(node, sourceNode, hitMode) {
        
        logMsg("tree.onDragOver(%o, %o, %o)", node, sourceNode, hitMode);
        
        if(node.isDescendantOf(sourceNode)){
          return false;
        }
        
        if( !node.data.isFolder && hitMode === "over" ){
          return "after";
        }
      },
      onDrop: function(node, sourceNode, hitMode, ui, draggable) {
        
        logMsg("tree.onDrop(%o, %o, %s)", node, sourceNode, hitMode);
        sourceNode.expand(true);
      
      var data = { 
            id: selectedNode.data.id,
            targetId: node.data.id,
            hitMode:hitMode,
        }
      
             $.ajax({
             url: url('node', 'move', ''),
             type: 'post', 
             data: JSON.stringify(data),
             contentType: 'application/json; charset=utf-8',
             dataType: 'json',
             async: false,
             success: function(data) {
                 sourceNode.move(node, hitMode);
                 sourceNode.expand(true);
                 ready();
                }
            });
      },
      onDragLeave: function(node, sourceNode) {
        
        logMsg("tree.onDragLeave(%o, %o)", node, sourceNode);
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
    if(node && node.data.id!="ROOT"){
        $('#barcode').val(node.data.barcode);
        $('#name').val(node.data.title);
        $('#type').val(node.data.type);
        $('#comment').val(node.data.comment);
        if ($('#nodeId')) {
            $('#nodeId').attr('value', node.data.id);
            $('.kumulus-uploader-form').attr('action', url('fileUploader', 'process', node.data.id));
            $('.kumulus-uploader').prop('disabled', false);
            $('.kumulus-uploader').removeClass('pure-button-disabled', false);
            $('.kumulus-uploader').addClass('pure-button-enabled', false);
        }
    } else {
        $('#barcode').val('Scan container barcode');
        $('#name').val('Enter name here');
        $('#type').val('');
        $('#comment').val('');
        if ($('#nodeId')) {   
            $('#nodeId').attr('value', '');
            $('.kumulus-uploader-form').attr('action', url('fileUploader', 'process', ''));
            $('.kumulus-uploader').prop('disabled', true);
            $('.kumulus-uploader').removeClass('pure-button-enabled', false);
            $('.kumulus-uploader').addClass('pure-button-disabled', false);
        }
    }
}

function delete_node() {
    if(selectedNode && state=="READY"  && selectedNode.data.id!="ROOT") {
        if(confirm("Please confirm that you would like to delete this archive item")) {
            var data = { id: selectedNode.data.id }
            $.ajax({
                url: url('node', 'delete', ''),
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
        $('#type').val('');
        $('#barcode').prop('disabled', false);
        $('#type').prop('disabled', true);
        $('#name').prop('disabled', true);
        $('#comment').prop('disabled', true);
        $('#nodeTree').prop('disabled', true);
        $('#button-cancel').prop('disabled', false);
        $('#barcode').focus();
        state = "INSERT";
    }
};

// --- EDIT STATE

function update_node() {
    if(selectedNode && state=="READY"  && selectedNode.data.id!="ROOT") {
        enable(false);
        $('#type').focus();
        $('#type').val('');
        $('#button-cancel').prop('disabled', false);
        $('#button-save').prop('disabled', false);
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
                target = url('node', 'update', '');
                
                 
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
                target = url('node', 'insert', '');
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


