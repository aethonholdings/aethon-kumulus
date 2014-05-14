


$(document).ready(function(){

    $("#template").hide();    
    
    $(".remove").click(function(){ $(this).parent().parent().remove(); });
    
    $("#add").click(function(){
        if(validateLineItems()) {
            var newRow = $("#template tbody").clone(true, true);
            $("#lineItems tbody").append(newRow.html());
            $(".remove").click(function(){ $(this).parent().parent().remove(); });
        }
    });
    
})

function validateLineItems() {
    var success = true;
    var maxPages = $("#pageCount").val();
    var lineItems = [];
    
    $("#lineItems tr:not(:has(th))").each(function() {
        // check the page number
        var lineItem = {
            page: $(this).find(".kumulus-column-page").val(),
            description: $(this).find(".kumulus-column-description").val(),
            date: $(this).find(".kumulus-column-date").val(),
            quantity: $(this).find(".kumulus-column-quantity").val(),
            currency: $(this).find(".kumulus-column-currency").val(),
            price: $(this).find(".kumulus-column-price").val(),
            amount: $(this).find(".kumulus-column-amount").val()
        }
        
        if(!lineItem.page || isNaN(lineItem.page) || lineItem.page > maxPages) {
            $(this).find(".kumulus-column-page").addClass("kumulus-entry-error")
            success = false;
        } else {
            $(this).find(".kumulus-column-page").removeClass("kumulus-entry-error")
        } 
        
    });
    return success;
}

