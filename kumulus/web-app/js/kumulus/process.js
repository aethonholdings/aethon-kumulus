
$(document).ready(function(){
    
    var count = 0;
    var pageCount = $("#pageCount").val();
    $("#template").hide();
    
    $("#documentForm").validate();
    $("#documentForm input").each(function() {
        $(this).rules("add", {required: true});
    });
    
    $("#lineItems tr:not(:has(th))").each(function() {
        initialiseRow($(this), pageCount);
    });
    
    $("#add").click(function(){
        if($("#lineItemForm").valid()) {
            count++;
            var regExp = new RegExp("!0", "g");
            $("#lineItems tbody").append($("#template tbody").clone(true, true).html().replace(regExp,"!"+count));                                               // add the row
            initialiseRow($("#lineItems tbody tr:last"), pageCount);
        }
    });
    
    $("#save").click(function(){
        if(validateAll()) alert("pass");
    });
    
    function validateAll() {
        if($("#documentForm").valid() && $("#lineItemForm").valid()) return true;
        return false;
    }
        
    function initialiseRow(element, pageCount) {
        
        if(!element.attr("initialised")) {        
            
            element.find(".remove").click(function(){ $(this).parent().parent().remove(); });
            
            // validation setup
            $("#lineItemForm").validate({
                errorPlacement: $.noop
            });
            
            element.find(".kumulus-column-page").rules("add", {
                required: true, 
                digits: true,
                min: 1,
                max: pageCount
            });
            element.find(".kumulus-column-description").rules("add", {
                required: true, 
            });
            element.find(".kumulus-column-amount").rules("add", {
                required: true, 
                number: true
            });
            element.attr("initialised", true);
        }
    }
    
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

