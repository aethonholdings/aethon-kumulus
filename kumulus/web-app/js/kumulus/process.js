var pageNo;
var currentRowObj;
$(document).ready(function()
{
    
    onLoadPreview()
    $("#company").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {
        }
    });

    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function(e) {
        imagePreview($(this))
        e.preventDefault();
    });

    $(document).on('blur', '#pageNo', function() {

        if ($("#pageNo").val() > pageNo) {
            alert("Invalid page number. You have entered a higher number than the number of pages in the document")
            $("#pageNo").focus()
        }
    });

    $(document).on('click', '.add', function() {

        // FIRST VALIDATE THE ENTRIES WITH THE VALIDATION PLUGIN
        // 
        // 
        // should just copy the footer here

        var tdObjectPageNo = $(currentRowObj).find("td #pageNo");
        var tdObjectFocus = $(currentRowObj).find("td #focus");
        var tdObjectTest = $(currentRowObj).find("td #amount");


        if (tdObjectFocus.val().trim().length === 0 && tdObjectTest.val().trim().length === 0 && tdObjectPageNo.val().trim().length === 0) {
            $(tdObjectFocus).addClass("error")
            $(tdObjectTest).addClass("error")
            $(tdObjectPageNo).addClass("error")
        }
        else if (tdObjectPageNo.val().trim().length === 0) {
            $(tdObjectPageNo).addClass("error")
        }
        else if (tdObjectFocus.val().trim().length === 0) {
            $(tdObjectFocus).addClass("error")
        }
        else if (tdObjectTest.val().trim().length === 0) {
            $(tdObjectTest).addClass("error")
        }
        else {
            $('#lineItems tbody').append(
                    '<tr class="new" onclick="send($(this))">' +
                    $('tr.new').html() +
                    '</tr>'
                    );

            $('.new.kumulus-column-page').val(pageNo);
            cloneRow();
        }

        // clear the new row

    });

    $(document).on('click', '.remove', function() {
        $(this).closest("tr").remove();
    });

    $(document).on('blur', '.kumulus-column-price', function() {
        var tdObjectPrice = $(currentRowObj).find("td .kumulus-column-price");
        var tdObjectQuantity = $(currentRowObj).find("td .kumulus-column-quantity");
        var tdObjectAmount = $(currentRowObj).find("td .kumulus-column-amount");

        var total = tdObjectPrice.val() * tdObjectQuantity.val();
        tdObjectAmount.val(total)
    });

    $('#save').click(function() {

        var formObj = $("#structure")
        var json = ConvertFormToJSON(formObj)
        validate();
//       var data = { node: nodeId,selectDate:selectDate}
        $.ajax({
            url: url('structure', 'save', ''),
            type: 'POST',
            data: JSON.stringify(json),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

                $.each(data, function(i) {

                    $("#nodeTable").append('<tr><td>' + data[i].name + '</td><td>' + data[i].barcode + '</td><td><input type="checkbox" class="checkbox" name="node_checkbox" id="' + data[i].id + '"></td></tr>');
                })

            }
        });

    })

});

function cloneRow() {

    $('#lineItems tr td a').not(':last').html('Remove');
    $('#lineItems tr td:last a').html('Add');
    $('#lineItems tr td:last a').removeClass('remove');
    $('#lineItems tr td:last a').addClass('add');
    $('#lineItems tr td a').not(':last').removeClass('add');
    $('#lineItems tr td a').not(':last').addClass('remove');

}


function send(obj) {

    currentRowObj = obj;
}

function CheckNumeric(e) {


    if (window.event) // IE 
    {
        if ((e.keyCode < 48 || e.keyCode > 57) & e.keyCode != 8) {
            event.returnValue = false;
            return false;

        }
    }
    else { // Fire Fox
        if ((e.which < 48 || e.which > 57) & e.which != 8) {
            e.preventDefault();
            return false;

        }
    }
}

function onLoadPreview() {
    var obj = $("ul#pages li:first img")
    imagePreview(obj);
}


function imagePreview(obj) {

    preview($('#preview-img'), obj.attr('viewId'));
    pageNo = obj.attr('pageNumber')

    if ($("#pageNo").val().trim().length == 0)
    {
        $('.new.kumulus-column-page').val(obj.attr('pageNumber'));
    }
    $('#lineItems tr:last td #pageNo').val(obj.attr('pageNumber'));
    $('#lineItems tr:last td #pageId').val(obj.attr('pageId'));
    $('.edit.kumulus-column-page').val(obj.attr('pageNumber'));
    $('#documentType').focus();

}
function calculateTotalAmount(price, q){
    console.log($(currentRowObj).text());
    console.log($(currentRowObj).find("#quantity").val());
    var total=(price*parseInt($(currentRowObj).find("td #quantity").val()));
    $(currentRowObj).find("#totalAmount").text(total);
    console.log(total);
    calculategrandTotalAmount()
}

function calculategrandTotalAmount(){
  
    var gtotal=0;
    $("#table tbody tr").each(function(j) {
        $(this).find("#totalAmount").each(function(index) {
            gtotal = parseInt(gtotal)  + parseInt($(this).html());
        });
        $('#grandtotal').text(gtotal);
    });
}

function ConvertFormToJSON(form) {
    var array = jQuery(form).serializeArray();
    var json = {};
    var itemList = new Array();
    var i = 0;

    
    jQuery.each(array, function() {

        if (i < 5) {
            json[this.name] = this.value || '';
        }
        else {

        }
        i++;
    });

    var subMap = {}
    $("#lineItems tbody tr").each(function(j) {
        $(this).find(":input").each(function(index) {
            subMap[$(this).attr("name")] = $(this).val() || '';
        });
        console.log(subMap)
        itemList.push(subMap);
    })
     
    json["lineItems"] = itemList;
   


    return json;
}


