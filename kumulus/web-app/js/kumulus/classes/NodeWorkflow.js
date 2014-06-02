function NodeWorkflow(elementId) {
    var instance = this;    // we have to do this to get around the fact that "this" context doesn't persist to event hander level
    instance.element = $(elementId);
    instance.parent = null;
    instance.node = null;
    instance.saveCallbackFunction;
    
    // initialise the dialog box
    instance.element.dialog({
        height: 580,
        width: 800,
        modal: true,
        draggable: false
    });
    instance.element.dialog("close")
            
    instance.add = function(parent, saveCallbackFunction) {
        if(parent) {
            // we are editing a new node
            initialise();
            instance.parent = parent;
            $('#barcode').val('');
            $('#name').val('');
            $('#type').val('');
            $('#comment').val('');
            $('#barcode').prop('disabled', false);
            $('#barcode').focus();
            instance.saveCallbackFunction = saveCallbackFunction;
            instance.element.dialog("open");
        }
    }
    
    instance.update = function(node, saveCallbackFunction) {
        if(node && node.data.key!="#") {
            // we are editing an existing node
            initialise();
            instance.node = node;
            $('#barcode').val(node.data.barcode);
            $('#name').val(node.data.title);
            $('#type').val(node.data.type);
            $('#comment').val(node.data.comment);
            $('#barcode').prop('disabled', true);            
            $('#type').focus();
            instance.saveCallbackFunction = saveCallbackFunction;
            instance.element.dialog("open");
        }
    }
    
    instance.delete = function(node) {
        if(node && node.data.id!="ROOT") {
            if(confirm("Please confirm that you would like to delete this item")) {
                var data = { id: node.data.id }
                request(url('node', 'delete', ''), data, function() {})
            }
        }
    }
    
    function initialise() {
        instance.element.find("input").prop('disabled', false);
        instance.element.find("select").prop('disabled', false);
        instance.element.find("button").prop('disabled', false);
        instance.element.find("button").prop('disabled', false);
        instance.element.find("textarea").prop('disabled', false);
        instance.node = null;
        instance.parent = null;
    } 
    
    
    // bind function to save nodes
    $('#button-save').click(function() {
        if (!$("#barcode").val() || !$("#type").val() || !$("#name").val()) {
            alert("Please complete all required input fields")
            return(false);
        } else {   
            var target, refreshNode;
            var data = {
                id: null,
                project: null,
                parentId: null, 
                barcode: $("#barcode").val(),
                type: $("#type").val(),
                name: $("#name").val(),
                comment: $("#comment").val()
            };
            if(instance.node) {
                // we are editing an existing node
                data.id = instance.node.data.id;
                data.project = instance.node.data.project;
                data.parentId = instance.node.data.parentId;
                target = url('node', 'update', '');
            } else if(instance.parent) {
                // we are creating a new node
                data.id = null;
                data.project = instance.parent.data.project;
                data.parentId = instance.parent.data.id;
                target = url('node', 'insert', '');
            }
            request(target, data, function(){
                instance.element.dialog("close");
                instance.saveCallbackFunction();
            });
        }
    });
     
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
                    $('#barcode').focus();
                }
            }   
        });
    }); 
    
    
}