$(document).ready(function(){ 
    
    $("#logisticsScheduler ul").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    
    $("#save").click(function() {
        var data = {
            unscheduled: [],
            scheduled: {}
        }
        $("#unscheduled input").each(function() {
            data.unscheduled.push(this.value);
        });
        
        for(var i=0; i<5; i++) {
            var element = $("#scheduleDate" + i);
            var key = element.attr("date");
            data.scheduled[key] = [];
            element.find("input").each(function() {
                data.scheduled[key].push(this.value);
            });
        }
        
        alert(JSON.stringify(data));
        // request(url("logistics", "save", ""), data, function() {});
    }); 

});