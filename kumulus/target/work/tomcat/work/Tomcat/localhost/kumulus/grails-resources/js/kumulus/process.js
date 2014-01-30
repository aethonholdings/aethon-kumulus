$(document).ready(function(){
        
    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function() {
        preview($('#preview-img'), $(this).attr('viewId'));
    });
   
});

function addLineItem() {
    
    $("#lineItems > tbody:last").append("<tr><td>test<td></tr>");
}

function save() {
    
}

