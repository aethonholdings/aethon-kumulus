
$(document).ready(function(){
    
    var count = 0;
    var pageCount = $("#pageCount").val();
    $("#template").hide();
    
    // bind autocomplete to Company name 
    $("#company").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {}
    });
    
    // initialisation of validation for document inputs
    $("#documentForm").validate();
    $("#documentForm input").each(function() {
        $(this).rules("add", {required: true});
    });
    
    // initialisation of validation and even handlers for all input table rows
    $("#lineItems tr:not(:has(th))").each(function() {
        initialiseRow($(this), pageCount, "updateTag");
    });
    
    // bind add new row
    $("#add").click(function(){
        if($("#lineItemForm").valid()) {
            count++;
            var regExp = new RegExp("!0", "g");
            $("#lineItems tbody").append($("#template tbody").clone(true, true).html().replace(regExp,"!"+count));                                               // add the row
            initialiseRow($("#lineItems tbody tr:last"), pageCount, "createTag");
        }
    });
    
    // bind save event
    $("#save").click(function(){ 
        if(validateAll()) {
            if(!save()) alert("Server error, could not save.");
        }
    });
    
    // bind save and next event
    $("#saveAndNext").click(function(){ 
        if(validateAll()) {
            if(!save()) alert("Server error, could not save."); 
            else {
                request(url("task", "close", ""), {taskId: $("#taskId").val()}, function(){
                    request(url("task", "getNext", ""), "", function(response){
                        alert(JSON.stringify(response))
                        window.open(url("backOffice", "process", response.data.taskId));
                    });
                });
            }
        } 
    });
    
    // perform full validation 
    function validateAll() {  
        if($("#documentForm").valid() && $("#lineItemForm").valid()) return true;
        return false;
    }
        
    function initialiseRow(element, pageCount, action) {
        if(!element.attr("initialised")) {        
            element.find(".remove").click(function(){ 
                $(this).parent().parent().attr("action","deleteTag");
                $(this).parent().parent().hide(); 
            });
            // validation setup
            $("#lineItemForm").validate({ errorPlacement: $.noop });
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
            element.attr("action", action);
            element.attr("initialised", true);
        }
    }
    
    function save() {
        var success;
        var taskId = $("#taskId").val();
        
        // package document data
        var document = {
            taskId: taskId,
            typeId: $("#documentType").val(),
            company: $("#company").val(),
            date: $("#date").val(),
            identifier: $("#identifier").val()
        }
        
        // package line items
        var lineItems = [];
        $("#lineItems tr:not(:has(th))").each(function() {
            var lineItem = {
                action: $(this).attr("action"),
                taskId: parseInt(taskId),
                lineItemId: parseInt($(this).attr("lineItemId")),
                page: parseInt($(this).find(".kumulus-column-page").val()),
                description: $(this).find(".kumulus-column-description").val(),
                date: $(this).find(".kumulus-column-date").val(),
                quantity: parseFloat($(this).find(".kumulus-column-quantity").val()),
                currencyId: parseInt($(this).find(".kumulus-column-currency").val()),
                price: parseFloat($(this).find(".kumulus-column-price").val()),
                amount: parseFloat($(this).find(".kumulus-column-amount").val())
            }
            lineItems.push(lineItem);
        });
        
        // update document
        request(url("document", "update", ""), document, function(response){ success = response.done; }); 
        // update line items
        if(success) {                                                          
            for(var i=0; i<lineItems.length && success; i++) {
                request(url("document", lineItems[i].action, ""), lineItems[i], function(response){ success = response.done });
            }
        }
        return success;
    }
    
})



