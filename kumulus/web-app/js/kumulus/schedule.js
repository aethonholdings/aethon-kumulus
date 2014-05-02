$(document).ready(function(){ 
    
    $("#logisticsScheduler ul").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    
    $("#save").click(function() {
        var data = {
            
        }
        request(url("logistics", "save", ""), data, function() {});
    }); 

});