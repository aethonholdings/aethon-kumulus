
var selectDate = "";

$(document).ready(function() {

    $("#divCalendar").datepicker({
        minDate: new Date(),
        onSelect: function(selectedDate) {
            selectDate = selectedDate.toString();
        }
    });

    $("#scheduleDated").datepicker({
        minDate: new Date(),
        beforeShow:function(){
            $('#scheduleDated').val($('#scheduleDated').val());
        }
    });
    
    $("#datePickerDate").datepicker({
        minDate: new Date(),
        onClose: function() {
            this.focus();
        }
    });

    $("#addNode").click(function() {
        getNodeList();
    });

    $("#removeNode, #removeNode1").click(function() {
        removeNodesFromShipment();
        window.location.reload(true);
    });

    $("#nodeDialog").dialog({
        autoOpen: false,
        height: 300,
        width: 700,
        modal: true,
        buttons: {
            "Add to shipment": function() {
                addNodesToShipment();
                $(this).dialog("close");
                window.location.reload(true);
            },
            Cancel: function() {
                $(this).dialog("close");
            }
        },
        close: function() {
            $(this).dialog("close");
        }
    });

});

function getNodeList() {
    
    $.ajax({
        url: url('node', 'listShippable', ''),
        type: 'POST',
        data: null,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {
            $("#nodeDialog").dialog("open");
            $("#nodeTableDialog tbody tr").remove();
            $.each(data, function(i) {
                $("#nodeTableDialog").append('<tr><td><input type="checkbox" class="inputCheckbox" name="node_checkbox" id="' + data[i].key + '"></td><td>' + data[i].text + '</td><td>' + data[i].barcode + '</td></tr>');
            });
        }
    });

}

function addNodesToShipment() {
    
    var shipmentId = $("#shipmentId").attr("value")
    var nodeIds = [];
    
    $(".inputCheckbox").each(function(i) {
        if ($(this).attr("checked", true)) {
            nodeIds[i] = $(this).attr("id");
        }
    });
    if(nodeIds.length>0) {
        $.ajax({
            url: url('shipment', 'addNodes', ''),
            type: 'POST',
            data: JSON.stringify({nodeIds: nodeIds, shipmentId: shipmentId}),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

            }
        });
    }
}

function removeNodesFromShipment() {
    
    var shipmentItemIds = [];
    
    if ($("input[name=shipItem]:checked").length != 0) {
        $("input[name=shipItem]:checked").each(function(i) {

            if ($(this).attr("checked", true)) {
                shipmentItemIds[i] = $(this).attr("id");
            }
        })
    
        $.ajax({
            url: url('shipment', 'removeNodes', ''),
            type: 'POST',
            data: JSON.stringify({shipmentItemIds: shipmentItemIds}),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

            }
        });
    }
}




