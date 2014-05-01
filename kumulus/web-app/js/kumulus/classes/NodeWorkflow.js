function NodeWorkflow(elementId, callbackFunction) {
    var instance = this;    // we have to do this to get around the fact that "this" context doesn't persist to event hander level
    instance.node = null;
    instance.element = $(elementId);
    instance.callbackFunction = callbackFunction;
    
    $('#barcode').change(function(){
        var data = {barcode: $('#barcode').val()}   
        $.ajax({
            url: url('node', 'checkBarcode', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                if(data.status=='true'){
                    alert("Invalid Barcode");
                    $('#barcode').val('');
                    $('#barcode').prop('disabled', false);
                    $('#barcode').focus();
                    return false;
                } else {
                    $('#button-save').prop('disabled', false);
                    $('#type').focus();
                }   
            }   
        });
    }); 
    
    // initialise the dialog box
    instance.element.dialog({
        height: 580,
        width: 800,
        modal: true,
        draggable: false
    });
    instance.element.dialog("close")
    
    // function to start the workflow
    instance.show = function (node) {
        if(node!="ROOT") {
            instance.node = node;
            if(node){
                // we are editing an existing node
                $('#barcode').val(node.data.barcode);
                $('#name').val(node.data.title);
                $('#type').val(node.data.type);
                $('#comment').val(node.data.comment);
                $('#barcode').prop('disabled', true);            
                $('#type').focus();
            } else {
                // we are editing a new node
                $('#barcode').val('');
                $('#name').val('');
                $('#type').val('');
                $('#comment').val('');
                $('#barcode').prop('disabled', false);
                $('#barcode').focus();
            }
            $('#type').prop('disabled', false);
            $('#name').prop('disabled', false); 
            $('#comment').prop('disabled', false); 
            $('#button-save').prop('disabled', false);
            instance.element.dialog("open");
        }
    }
    
    // bind function to return the form inputs
    $('#button-save').click(function() {
        if (!$("#barcode").val() || !$("#type").val() || !$("#name").val()) {
            alert("Please complete all required input fields")
            return(false);
        } else {
            var data = {
                node: null,
                barcode: $("#barcode").val(),
                type: $("#type").val(),
                name: $("#name").val(),
                comment: $("#comment").val()
            }
            if(instance.node) data.node = instance.node;
            callbackFunction(data);
            instance.node = null;
            instance.element.dialog("close");
        }
    });
    
    instance.add = function() {
        
    }
    
    instance.update = function(node) {
        
    }
    
    instance.delete = function(node) {
        
    }
    
    
}