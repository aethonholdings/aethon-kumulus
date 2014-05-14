


$(document).ready(function(){
    var count = 0;
    $("#template").hide();    
    
    $(".remove").click(function(){ $(this).parent().parent().remove(); });
    
    $("#add").click(function(){
        $("#lineItemForm").validate();
        if($("#lineItemForm").valid()) {
            var newRow = $("#template tbody").clone(true, true);
            $("#lineItems tbody").append(newRow.html());                            // add the row
            $(".remove").click(function(){ $(this).parent().parent().remove(); });  // bind events
            // bind validation            
        }
    });
})

function packageLineItems() {
    
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
        lineItems.push(lineItem);
    });
    return lineItems;
    
}

