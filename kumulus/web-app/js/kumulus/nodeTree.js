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
                 nodeDetailInfo(node);
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
    
    $('#barcode').change(function(){
        console.log("helllo");
        var data = {barcode: $('#barcode').val()}   
        $.ajax({
            url: url('node', 'checkBarcode', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

             if(data.status=='true'){
                alert("Invalid Barcode")
                enable(true)
                $('#barcode').val('')
                $('#barcode').prop('disabled', false);
//                $('#button-save').prop('disabled', true);
                $('#barcode').focus();
                return false;
                }
                else{
                     enable(false)
                    $('#button-save').prop('disabled', false);
                     $('#type').focus();
                }   
            }   
            
        });
    });
    
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
   $('#button-add').prop('disabled', true);
   $('#button-edit').prop('disabled', true);
   $('#button-delete').prop('disabled', true);
   $('#button-search').prop('disabled', true);
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
                    var parentnode=selectedNode.getParent()
                    selectedNode.remove();
                    ready();
                    if (selectedNode.getParent().data.key != '#') {
                        selectedNode.getParent().reloadChildren(function(selectedNode, isOk) {
                            tree.activateKey(selectedNode.data.key);
                        });
                    }
                    else {
                       tree.activateKey(selectedNode.getParent().data.key);
                    }
                    
                }
            });
        }
            $('#button-add').prop('disabled', false);
            $('#button-edit').prop('disabled', false);
            $('#button-delete').prop('disabled', false);
            $('#button-search').prop('disabled', false);
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
        $('#button-add').prop('disabled', true);
        $('#button-edit').prop('disabled', true);
        $('#button-delete').prop('disabled', true);
        $('#button-search').prop('disabled', true);
        $('#barcode').focus();
        state = "INSERT";
    }
};

// --- EDIT STATE

function update_node() {
    if(selectedNode && state=="READY"  && selectedNode.data.id!="ROOT") {
        enable(false);
        $('#type').focus();
        $('#button-cancel').prop('disabled', false);
        $('#button-save').prop('disabled', false);
        $('#button-add').prop('disabled', true);
        $('#button-edit').prop('disabled', true);
        $('#button-delete').prop('disabled', true);
        $('#button-search').prop('disabled', true);
        state = "UPDATE";
    }
};

function search_node() {
   $('#button-add').prop('disabled', true);
   $('#button-edit').prop('disabled', true);
   $('#button-delete').prop('disabled', true);
   $('#button-search').prop('disabled', true);
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
                tree.loadKeyPath(keypath, function(node, status){
                    if(status == "loaded") {
                        // 'node' is a parent that was just traversed.
                        // If we call expand() here, then all nodes will be expanded
                        // as we go
                        node.expand();
                    }else if(status == "ok") {
                        // 'node' is the end node of our path.
                        // If we call activate() or makeVisible() here, then the
                        // whole branch will be exoanded now
                        node.activate();
                    }else if(status == "notfound") {
                        
                    }
                });
            } else {
                alert("Barcode not found in this project archive");
            }
        }
    });
     $('#button-add').prop('disabled', false);
     $('#button-edit').prop('disabled', false);
     $('#button-delete').prop('disabled', false);
     $('#button-search').prop('disabled', false);
}

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
            $('#button-cancel').prop('disabled', true);
            $('#button-save').prop('disabled', true);
            $('#button-add').prop('disabled', false);
            $('#button-edit').prop('disabled', false);
            $('#button-delete').prop('disabled', false);
            $('#button-search').prop('disabled', false);
            ready();
            break;
    } 
}

function save() {

    if (!$("#barcode").val() || !$("#type").val() || !$("#name").val()) {
        alert("Please complete all required input fields")
        return(false);
    } else {
        var target, data;
        // need a case selection here
        switch (state) {
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
        var news = selectedNode;
        $.ajax({
            url: target,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                ready();
                if (selectedNode.data.key != '#') {
                    selectedNode.reloadChildren(function(selectedNode, isOk) {
                    });
                }
                else {
                    tree.reload();
                }
                $('#button-cancel').prop('disabled', true);
                $('#button-save').prop('disabled', true);
                $('#button-add').prop('disabled', false);
                $('#button-edit').prop('disabled', false);
                $('#button-delete').prop('disabled', false);
                $('#button-search').prop('disabled', false);
            }
        });
        tree.selectKey(news.data.key, true);
    }
}
function containerToTransport(){
    var data = { 
        id: selectedNode.data.id
    }
    var newNodeKey=selectedNode.data.key;
    if(data.id!='ROOT'){
        $.ajax({
            url: url('node', 'containerToTransport', ''),
            type: 'post', 
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                var pnode = selectedNode.getParent()
                if (selectedNode.getParent().data.key != '#') {
                    selectedNode.getParent().reloadChildren(function(pnode, isOk) { 
                        tree.activateKey(newNodeKey);
                    });                   
                }
                else {
                    var path = "#/" + newNodeKey;
                    location.reload(true)
                    tree.loadKeyPath(path, function(node, status){
                        if(status == "loaded") {
                            node.expand();
                        } else if(status == "ok") {
                            node.activate();
                        }
                    });
                }
            }
        });    
    }
}
function nodeDetailInfo(node){
    
    var data = { node:node.data.id }
    if(data.node!='ROOT'){
        $("#nodeBarcode").val(node.data.barcode);
        $("#nodeType").val(node.data.type);
        $("#nodeLocation").val(node.data.location);
        $("#nodeStatus").val(node.data.status);
        $.ajax({
            url: url('node', 'getDocuments', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                $("#pageInfo tbody tr").remove();
                $.each(data, function(i) {
                    var imgUrl = url('image','get', data[i].thumbnailImageId);
                    var status = data[i].status;
                    $("#pageInfo tbody").append('<tr><td><img class="kumulus-thumbnail kumulus-element-border" height="140" width="100"  src='+ imgUrl +' /></td><td>'+status+'</td></tr>');
                });
            }
        });
         if(node.data.status=='Open'){
            $('#button-readyfortransfer').prop('disabled', false);
        }
        else{
            $('#button-readyfortransfer').prop('disabled', true);
        }
    }
  }  
  function fetchFromStorage(){
     var data ={  id: selectedNode.data.id }
          if(data.node!='ROOT'){
           $.ajax({
                url: url('node', 'fetchFromStorage', ''),
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(data) {
                    if(data.done == true){
                    alert("your Request has been placed");
                           }
                       }
            });
          }         
    }

