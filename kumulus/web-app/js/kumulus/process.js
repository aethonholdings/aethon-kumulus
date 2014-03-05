var pageNo,pageId,status=false,rowCounter;
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
    
    
    currentRowObj= $("#lineItems tbody tr");
 
    datePick($("#lineItems tbody tr").length,$("#lineItemCount").val());

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

    $('#add').click(function() {

        // FIRST VALIDATE THE ENTRIES WITH THE VALIDATION PLUGIN
        // 
        // 
        // should just copy the footer here
        var rowCount = $("#lineItems tbody tr").length;
        var status = "";
        
        if (rowCount == 0) {
            addRow();
            $('.new.kumulus-column-page').val(pageNo);
        }
        else {
            status = validateLastRow();
        }
        if (status)
        {
            addRow();
        }

        // clear the new row

    });

    $(document).on('click', '.remove', function() {
        $(this).closest("tr").remove();
        status=true;
        rowCounter= $("#lineItems tbody tr").length;
    });

    $(document).on('blur', '.kumulus-column-price', function() {
       
        var tdObjectPrice = $(currentRowObj).find("td .kumulus-column-price");
        var tdObjectQuantity = $(currentRowObj).find("td .kumulus-column-quantity");
        var tdObjectAmount = $(currentRowObj).find("td #amount");
        var total = tdObjectPrice.val() * tdObjectQuantity.val();
        tdObjectAmount.val(total)
    });

    $('#save').click(function() {
        save(false);
    });
    
    $('#saveAndNext').click(function() {
        save(true);
    });

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
    var keyCode = e.which;
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && keyCode != 37 && keyCode != 38 && keyCode != 39 && keyCode != 40 && keyCode != 46 && keyCode != 110 && keyCode != 190) {
        if (keyCode < 48) {
            e.preventDefault();
        } else if (keyCode > 57 && keyCode < 96) {
            e.preventDefault();
        } else if (keyCode > 105) {
            e.preventDefault();
        }
    }
}

function CheckNumericWithoutDec(e) {

    var keyCode = e.which;
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && keyCode != 37 && keyCode != 38 && keyCode != 39 && keyCode != 40 && keyCode != 46) {
        if (keyCode < 48) {
            e.preventDefault();
        } else if (keyCode > 57 && keyCode < 96) {
            e.preventDefault();
        } else if (keyCode > 105) {
            e.preventDefault();
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
    pageId=obj.attr('pageId')
    
    if($('#lineItems tbody tr').length>0)
    {
        if ($("#pageNo").val().trim().length == 0)
        {
        $('.new.kumulus-column-page').val(obj.attr('pageNumber'));
        }
    }
    $('#lineItems tr:last td #pageNo').val(obj.attr('pageNumber'));
    $('#lineItems tr:last td #pageId').val(obj.attr('pageId'));
    $('.edit.kumulus-column-page').val(obj.attr('pageNumber'));
    $('#documentType').focus();

}

function calculateTotalAmount(price){
    var total=(parseFloat(price)*parseFloat($(currentRowObj).find("td #quantity").val()));
    $(currentRowObj).find("#totalAmount").text(total.toFixed(2));

    calculategrandTotalAmount()
}

function calculategrandTotalAmount() {

    var gtotal = 0;
    $("#table tbody tr").each(function(j) {
        $(this).find("#totalAmount").each(function(index) {
            gtotal = parseFloat(gtotal) + parseFloat($(this).html());
        });
        $('#grandtotal').text(gtotal.toFixed(2));
    });
}

function ConvertFormToJSON(form) {
    
    var array = jQuery(form).serializeArray();
    var json = {};
    var itemList = new Array();
    var i = 0;


    jQuery.each(array, function() {

        if (i < 7) {
            json[this.name] = this.value || '';
        }
        else {

        }
        i++;
    });

    $("#lineItems tbody tr").each(function(j) {
        var subMap = {}
        $(this).find(":input").each(function(index) {
            subMap[$(this).attr("name")] = $(this).val() || '';
        });
        itemList.push(subMap);
    })
    json["lineItems"] = itemList;
    return json;
}

function validateLastRow() {


    var tdObjectPageNo = $("#lineItems tbody tr:last").find("td #pageNo");
    var tdObjectFocus = $("#lineItems tbody tr:last").find("td #focus");
    var tdObjectTest = $("#lineItems tbody tr:last").find("td #amount");


    if (tdObjectFocus.val().trim().length === 0 && tdObjectTest.val().trim().length === 0 && tdObjectPageNo.val().trim().length === 0) {
        $(tdObjectFocus).addClass("error")
        $(tdObjectTest).addClass("error")
        $(tdObjectPageNo).addClass("error")
        return false;
    }
    else if (tdObjectPageNo.val().trim().length === 0 && tdObjectTest.val().trim().length === 0) {
        $(tdObjectPageNo).addClass("error")
        $(tdObjectTest).addClass("error")
        return false;
    }
    else if (tdObjectFocus.val().trim().length === 0 && tdObjectTest.val().trim().length === 0) {
        $(tdObjectFocus).addClass("error")
        $(tdObjectTest).addClass("error")

        return false;
    }
    else if (tdObjectFocus.val().trim().length === 0) {
        $(tdObjectFocus).addClass("error")
        return false;
    }
    else if (tdObjectTest.val().trim().length === 0) {
        $(tdObjectTest).addClass("error")
        return false;
    }

    return true;
}

function addRow() {
       
     var mainCount=$("#lineItemCount").val();   
     var count=$("#lineItems tbody tr").length
   
    if(status){
        if(rowCounter==count){
       count=count+count+3
       }
       else{
        count=count+count   
       }
      }
   
    $("#lineItems tbody").append('<tr onkeypress="send(this)" class="new">' +
            '<td><input id="lineItemId" name="lineItemId" size="4" type="text" value="" class="kumulus-column-id new" readonly></input></td>' +
            '<td><input id="pageNo" name="pageNo" size="2" type="text" value="" class="kumulus-column-page new" onkeypress="CheckNumeric(event)" ></input>' +
            '<input id="pageId" name="pageId" type="hidden" value=""></input></td>' +
            '<td><input id="focus" name="description" size="25" type=text value="" class="kumulus-column-description new" ></input></td>' +
            '<td><input id="lineItemDate'+mainCount+count+'" name ="lineItemDate" placeholder="Select a date" type="text"  class="pure-input-1"></td>' +
            '<td><input id="quantity" name="quantity" type=text  size="6" value="" class="kumulus-column-quantity new" onkeydown="CheckNumeric(event)"></input></td>' +
            '<td><input id="price" name="price" size="6" type="text" value="" class="kumulus-column-price new" onkeydown="CheckNumeric(event)" onchange="total(this)"></input></td>' +
            '<td><input id="amount" name ="amount" size="6" type="text"  value="" class="kumulus-column-amount new" onkeydown="CheckNumeric(event)" id="test"></input></td>' +
            '<td><a class="remove" href="#" >Remove</a></td>' +
            '</tr>')
    datePick(count,mainCount);
      $('#lineItems tr:last td #pageNo').val(pageNo);
      $('#lineItems tr:last td #pageId').val(pageId);
}


function datePick(counter,mainCounter){

for(var j=0;j<=mainCounter;j++)
{
    for ( var i = 0; i <=counter; i++ ) 
        {
            $("#lineItemDate"+j+i).datepicker({
           
              onClose: function () {
                this.focus();
                }
            });
            
        }
    }
    
     $("#datePickerDate").datepicker({
           
            onClose: function () {
                this.focus();
                }
             
     });

}

function save(next) {

    var formObj = $("#structure");
    validate();
    var status = $("#structure").valid();
    var rowStatus = validateLastRow();

    if (status && rowStatus) {
        var json = ConvertFormToJSON(formObj);
        var submit = {
            completeTask: next,
            form: json
        };
        
        $.ajax({
            url: url('structure', 'save', ''),
            type: 'POST',
            data: JSON.stringify(submit),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                if(data.done) { 
                    if(next) {
                        location.href = url("structure", "getNextTask", "");
                    } else {
                        location.reload();
                    } 
                }
                else {
                    alert("Server error, unable to save data, please retry");
                }
            }
        });
    }
}

