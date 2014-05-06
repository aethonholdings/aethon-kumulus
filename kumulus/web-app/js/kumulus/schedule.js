$(document).ready(function(){ 
    
    $("#logisticsScheduler ul").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    
    $("#save").click(function() {
        var updates = [];
        
        // package all shipments into a data structure
        $(".kumulus-shipment-data").each(function() {
            var data = {
                companyId: $(this).attr("companyId"),
                id: $(this).attr("shipmentId"),
                scheduledDate: $(this).parent().parent().attr("date")
            }
            updates.push(data);
        });
        for(var i=0; i < updates.length; i++) {
            var action;
            var data = updates[i];
            if(data.id) {
                if(data.scheduledDate) action = "update"; else action = "remove";
            } else {
                if(data.scheduledDate) action = "create";
            }
            if(action) request(url("shipment", action, ""), data, function(){});
        }
        location.reload(true);
    });
});