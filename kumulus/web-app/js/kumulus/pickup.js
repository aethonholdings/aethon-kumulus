$(document).ready(function() {
    var containerViewer = new ContainerViewer("#containerViewer");
    $(document).on("keypress", "#barcodeScanner", function(e) {
        if (e.keyCode == 13) {
            alert("Enter pressed");
            return false; // prevent the button click from happening
        }
    });
    $("#barcodeScanner").focus();
    
})