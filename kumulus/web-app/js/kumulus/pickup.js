$(document).ready(function() {
    var containerViewer = new ContainerViewer("#containerViewer");
    $(document).on("keypress", "#barcodeScanner", function(e) {
        if (e.keyCode == 13) {
            containerViewer.reset();
            $("#company").val("");
            $("#date").val("");

            request(url("shipment", "getByBarcode", ""), {barcode: $("#barcodeScanner").val()}, function(shipmentData) {
                if(shipmentData.success) {
                    var date = new Date(Date.parse(shipmentData.data.scheduled));
                    $("#company").val(shipmentData.data.company);
                    $("#date").val(date.toISOString().substring(0, 10));
                    request(url("shipment", "getNodes", ""), {shipmentId: shipmentData.data.id}, function(nodes){
                        
                    });
                    // containerViewer.update(nodeResponse);
                }
            });
            return false; // prevent the button click from happening
        }
    });
    $("#barcodeScanner").focus();
    
})