var pageNo;
var currentRowObj;
$(document).ready(function(){
    validate();
    $("#company").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {  
        }
    });
    
    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function(e) {
      
        preview($('#preview-img'), $(this).attr('viewId'));
        pageNo=$(this).attr('pageNumber')
      
       if($("#pageNo").val().trim().length==0){
   
        $('.new.kumulus-column-page').val($(this).attr('pageNumber'));
        }
        $('#lineItems tr:last td #pageNo').val($(this).attr('pageNumber'));
        $('.edit.kumulus-column-page').val($(this).attr('pageNumber'));
//        $('.kumulus-column-description.new').focus();
        $('#documentType').focus();
        e.preventDefault();
             
              
    });
    
 $(document).on('click', '.add', function(){
        
        // FIRST VALIDATE THE ENTRIES WITH THE VALIDATION PLUGIN
        // 
        // 
        // should just copy the footer here

        var tdObjectFocus= $(currentRowObj).find("td #focus");
        var tdObjectTest= $(currentRowObj).find("td #test");

            if(tdObjectFocus.val().trim().length===0){
                alert("Please fill mandatory fields before adding new row")
            }
            else if(tdObjectTest.val().trim().length===0){
                alert("Please fill mandatory fields before adding new row")
            }
            else{
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
   
//    $('#documentType').focus();

 $(document).on('click', '.remove', function(){
 $(this).closest("tr").remove();
 });
 
 $(document).on('blur','.kumulus-column-price',function(){
 var tdObjectPrice= $(currentRowObj).find("td .kumulus-column-price");
 var tdObjectQuantity= $(currentRowObj).find("td .kumulus-column-quantity");
 var  tdObjectAmount= $(currentRowObj).find("td .kumulus-column-amount");

   var total=  tdObjectPrice.val()* tdObjectQuantity.val();
   tdObjectAmount.val(total) 
 });
   
});

function cloneRow(){

 $('#lineItems tr td a').not(':last').html('Remove');
 $('#lineItems tr td:last a').html('Add');
 $('#lineItems tr td:last a').removeClass('remove');
 $('#lineItems tr td:last a').addClass('add');
 $('#lineItems tr td a').not(':last').removeClass('add');
 $('#lineItems tr td a').not(':last').addClass('remove');

}


function send(obj){

    currentRowObj=obj;
}

function save() {
    
    
    
}



