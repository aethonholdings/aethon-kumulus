function LogisticsController(elementId, callbackFunction) {
    var instance = this;
    instance.element = $(elementId);
    instance.node = null;
    
    instance.update = function(node) {
        var button = false;
        var buttonValue = null;
        var buttonBinding;
        instance.node = node;
        instance.element.empty();
                
        // map node state to buttons
        switch(node.data.stateId) {
            case 1:                                                         // STATE_CLIENT_OPEN
                button = true;
                buttonValue = "Pick up";
                buttonBinding = function() {
                    alert("Container flagged for pickup.");
                }
                break;
            case 2:                                                         // STATE_FLAGGED_TO_SHIP
                button = true;
                buttonValue = "Cancel pickup"
                break;
            case 3:                                                         // STATE_IN_TRANSIT
                break;  
            case 4:                                                         // STATE_IN_STORAGE
                break;
            case 5:                                                         // STATE_IN_DIGITISATION_LINE
                break;
            case 6:                                                         // STATE_FLAGGED_TO_FETCH
                break;                                                      
            case 7:                                                         // STATE_RETURN_IN_PROGRESS
                break;
            case 8:                                                         // STATE_CLIENT_RETURNED
                break;
        }   
        if(button) {
            instance.element.append('<input class="pure-button kumulus-margin-top" type="button"/>');
            var buttonTag = instance.element.find("input");
            buttonTag.val(buttonValue);
            buttonTag.click(function(obj) { 
                buttonBinding()
            });
        } 
    }    
}
