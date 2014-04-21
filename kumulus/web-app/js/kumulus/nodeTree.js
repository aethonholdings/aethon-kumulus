function NodeTree(elementId, projectId, refreshCallbackFunction) {
    
    // create the node tree
    this.dynatree = $(elementId).dynatree({
        
        initAjax: {
            title: 'Archive structure',
            url: url('node', 'getRoot', projectId),
            minExpandLevel: 0,
            autoFocus: true,
            persist: true,
            clickFolderMode: 1, 
            selectMode: 1, 
            rootVisible: true,
            keyboard: true
        },
        
        onActivate: function(node) {
            refreshCallbackFunction(node);
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
                return true;
            },
              
            onDragStop: function(node) {
            },
            autoExpandMS: 1000,
            preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
    
            onDragEnter: function(node, sourceNode) {
                return true;
            },
                    
            onDragOver: function(node, sourceNode, hitMode) {
                if(node.isDescendantOf(sourceNode)){
                    return false;
                }
                if( !node.data.isFolder && hitMode === "over" ){
                    return "after";
                }
            },
                    
            onDrop: function(node, sourceNode, hitMode, ui, draggable) {
                sourceNode.expand(true);
                var data = { 
                    id: sourceNode.data.id,
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
                    }
                });
            },
            
            onDragLeave: function(node, sourceNode) {
            }
        }
    });
    
    this.getTree = function() {
        return $(elementId).dynatree("getTree");
    }
    
    this.getSelectedNode = function() {
        return this.getTree().getActiveNode();
    }
    
    this.expandKeypath = function(keypath) { 
        this.getTree().loadKeyPath(keypath, function(node, status){
            if(status == "loaded") {
                // 'node' is a parent that was just traversed.
                // If we call expand() here, then all nodes will be expanded
                // as we go
                node.expand();
                return true;
            }else if(status == "ok") {
                // 'node' is the end node of our path.
                // If we call activate() or makeVisible() here, then the
                // whole branch will be exoanded now
                node.activate();
                return true;
            }else if(status == "notfound") {            
                return false;
            }
        }); 
    }
    
    this.removeNode = function(node) {
        node.remove();
        if (node.getParent().data.key != '#') {
            node.getParent().reloadChildren(function(selectedNode, isOk) {
                this.getTree().activateKey(selectedNode.data.key);
            });
        } else {
            this.getTree().activateKey(selectedNode.getParent().data.key);
        }
    }
    
    this.selectKey = function(key) {
        this.getTree().selectKey(key, true);
    }
    
    this.reload = function() {
        this.getTree().reload();
    }
}



