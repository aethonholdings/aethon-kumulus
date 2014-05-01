function ContainerViewer(elementId) {
    var instance = this;
    instance.element = $(elementId);
    instance.node = null;
    
    instance.update = function update(node) {
        instance.node = node;
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
        }
    }
}

