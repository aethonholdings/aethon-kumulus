function ContainerViewer() {
    
    this.node = null;
    this.update = update;
    this.fetch = fetch;
    this.seal = seal;
    this.submit = submit;
    
    function update(node) {
        
        var buttonTag;
        this.node = node;
        
        $("#nodeActions").empty();
        $("#nodeBarcode").val(null);
        $("#nodeType").val(null);
        $("#nodeLocation").val(null);
        $("#nodeStatus").val(null);

        // update node detail info panel
        if(node.data.id!='ROOT'){
            $("#nodeBarcode").val(node.data.barcode);
            $("#nodeType").val(node.data.type);
            $("#nodeLocation").val(node.data.location);
            $("#nodeStatus").val(node.data.state);
            $.ajax({
                url: url('node', 'getDocuments', ''),
                type: 'POST',
                data: JSON.stringify({ node: node.data.id }),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(data) {
                    $("#pageInfo tbody tr").remove();
                    $.each(data, function(i) {
                        var imgUrl = url('image','get', data[i].thumbnailImageId);
                        var status = data[i].status;
                        $("#pageInfo tbody").append(
                            '<tr><td><a href="' + url("document", "get", data[i].id) + '"><img class="kumulus-thumbnail" height="140" width="100"  src='+ imgUrl +' /></a></td><td>'+status+'</td></tr>'
                        );
                    });
                }
            });
        
            // map node state to buttons
            switch(node.data.stateId) {
                case 1:                                                         // container open and still being processed by customer
                    if(node.data.storeable) buttonTag = '<input id="button-readyfortransfer" type="button"  value="Seal" class="pure-button kumulus-margin-top" onclick="containerViewer.seal();" />';
                    break;

                case 2:                                                         // container sealed and ready for shipping
                    
                    break;

                case 3:                                                         // container flagged for shipping
                    
                    break;

                case 4:                                                         // container in storage
                    buttonTag = '<input id="button-fetch" type="button"  value="Fetch from storage" class="pure-button kumulus-margin-top" onclick="containerViewer.fetch();" />';
                    break;  
                    
                case 5:                                                         // container in digitisation production line for scanning
                    break;
                
                case 6:                                                         // container flagged for fetching from storage
                    break;
            }       
            $("#nodeActions").append(buttonTag);   
        }
    }
    
    function seal(){
        if(this.node.id!='ROOT'){
            this.submit(url('node', 'seal', ''));
        }
    }
    
    function ship(){
        if(this.node.id!='ROOT'){
            this.submit(url('node', 'ship', ''));
        }
    }
    
    function fetch(){
        var data ={  id: this.node.data.id }
        if(data.node!='ROOT'){
            $.ajax({
                url: url('node', 'fetch', ''),
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(data) {
                    if(data.done == true){
                        alert("Your request has been placed");
                    }
                }
            });
        }         
    }
    
    function submit(url) {
        var pnode = this.node.getParent();
        var newNodeKey=this.node.data.key;
        $.ajax({
            url: url,
            type: 'post', 
            data: JSON.stringify({ id: this.node.data.id }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                if (pnode.data.key != '#') {
                    pnode.reloadChildren(function(pnode, isOk) { 
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

