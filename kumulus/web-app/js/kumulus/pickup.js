/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selectDate = "", deliveryId, shipmentId;

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


//  getNodeList();  

    $("#sendPickupNode").click(function() {

        if ($("input:checkbox:checked").length != 0) {
            $("input:checkbox:checked").each(function(i) {
                if ($(this).attr("checked", true)) {
                    nodeId[i] = $(this).attr("id")
                    $("#nodeId").val(nodeId)
                }

            });

            saveNode();
        }
        else {
            alert("Please select a node to send")
        }

    })


    $("#addNode, #addNode1").click(function() {
        deliveryId = $(this).attr('deliveryId');
        shipmentId = $("#shipmentId").val()
        getNodeList($(this).attr('deliveryId'), $("#shipmentId").val());

    });

    $("#nodeDialog").dialog({
        autoOpen: false,
        height: 300,
        width: 400,
        modal: true,
        buttons: {
            "Add Nodes": function() {
                addNodes();
                $(this).dialog("close")
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

    $("#removeNode, #removeNode1").click(function() {
        deliveryId = $(this).attr('deliveryId');
        removeNode($(this).attr('deliveryId'));
        window.location.reload(true);

    });


});



function getNodeList(deliveryId, shipmentId) {

    var data = {deliveryId: deliveryId, shipmentId: shipmentId, }
    $.ajax({
        url: url('node', 'list', ''),
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {
            $("#nodeDialog").dialog("open");
            $("#nodeTableDialog tbody tr").remove()
            $.each(data, function(i) {

                $("#nodeTableDialog").append('<tr><td><input type="checkbox" class="checkbox" name="node_checkbox" id="' + data[i].id + '"></td><td>' + data[i].name + '</td></tr>');
            })

        }
    });

}


function saveNode() {

    if (selectDate != "") {
        var data = {node: nodeId, selectDate: selectDate}
        $.ajax({
            url: url('node', 'test', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

                $.each(data, function(i) {

                    $("#nodeTable").append('<tr><td>' + data[i].name + '</td><td>' + data[i].barcode + '</td><td><input type="checkbox" class="checkbox" name="node_checkbox" id="' + data[i].id + '"></td></tr>');
                })

            }
        });
    }
    else {
        alert("Please select date")
    }

}

function addNodes() {
    var nodeId = [];
    if ($("input[name=node_checkbox]:checked").length != 0) {
        $("input[name=node_checkbox]:checked").each(function(i) {

            if ($(this).attr("checked", true)) {
                nodeId[i] = $(this).attr("id");
            }
        })
        saveShipmentNode(nodeId, deliveryId);
    }
    else {
        alert("Select the node to be add.");
        return false;
    }

}

function removeNode() {
    var nodeId = [];
    if ($("input[name=shipItem]:checked").length != 0) {
        $("input[name=shipItem]:checked").each(function(i) {

            if ($(this).attr("checked", true)) {
                nodeId[i] = $(this).attr("id");
            }
        })
        removeShipmentNode(nodeId, deliveryId);
    }
    else {
        alert("Select the node to be remove.");
        return false;
    }

}

function saveShipmentNode(nodeId, deliveryId) {

    var data = {nodeList: nodeId, deliveryId: deliveryId, shipmentId: shipmentId};
    $.ajax({
        url: url('shipment', 'saveItems', ''),
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {

        }
    });

}

function  removeShipmentNode(nodeId, deliveryId) {
    var data = {nodeList: nodeId, deliveryId: deliveryId};
    $.ajax({
        url: url('shipment', 'removeItems', ''),
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data) {

        }
    });

}








