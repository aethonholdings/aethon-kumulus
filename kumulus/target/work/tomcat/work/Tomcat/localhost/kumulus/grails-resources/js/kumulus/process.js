$(document).ready(function(){
    
    $("#company").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {  
        }
    });
    
    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function() {
        preview($('#preview-img'), $(this).attr('viewId'));
        $('.new.kumulus-column-page').val($(this).attr('pageNumber'));
        $('.edit.kumulus-column-page').val($(this).attr('pageNumber'));
        $('.kumulus-column-description.new').focus();
    });
    
    $('.add').bind('click', function() {
        
        // FIRST VALIDATE THE ENTRIES WITH THE VALIDATION PLUGIN
        // 
        // 
        // should just copy the footer here

        $('#lineItems tbody').append(
            '<tr>' + 
                $('tr.new').html() +
            '</tr>'
        );
            
        // clear the new row
    });
    
    $('#documentType').focus()
   
});

function save() {
    
    
    
}

