$(document).ready(function() {
    var containerViewer = new ContainerViewer("#containerViewer");
    $(document).on("keypress", "#barcodeScanner", function(e) {
        if (e.keyCode == 13) {
            containerViewer.reset();
            $("#company").val("");
            $("#date").val("");
            var data = {
                barcode: $("#barcodeScanner").val()
            }
            request(url("node", "getByBarcode", ""), data, function(nodeResponse){
                if(nodeResponse.success) {
                    request(url("shipment", "getByNodeId", ""), {nodeId: nodeResponse.data.key}, function(shipmentResponse) {
                        if(shipmentResponse.success) {
                            var date = new Date(Date.parse(shipmentResponse.data.scheduled));
                            $("#company").val(shipmentResponse.data.company);
                            $("#date").val(date.toISOString().substring(0, 10));
                            containerViewer.update(nodeResponse);
                        }
                    });
                }
            });
            return false; // prevent the button click from happening
        }
    });
    $("#barcodeScanner").focus();
    
})