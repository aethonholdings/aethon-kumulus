/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//workflow UI
$(document).ready(function(){
    
    $('#barcode').change(function(){
        if ($(this).val() == '') {
            $('#barcode').prop('disabled', true);
            $('#button-save').prop('disabled', true);
        }
            else {  
                $('#name').prop('disabled', false);
                $('#barcode').prop('disabled', true);
                $('#type').prop('disabled', false);
                $('#button-save').prop('disabled', false);
                $('#comment').prop('disabled', false);
                $('#type').focus();
          }
    }); 
    $('#type').live('change', function () {
    // Get the from and to values.
    var from = $(this).data("from");
    var to = $(this).val();

    // Remember the from value for next time
    $(this).data("from", to);

    // Change the text for each field that corresponds with this component.
    var textBoxes = $(this).closest('div.data-group').find('input:text');
    textBoxes.each(function () {
        var curValue = $(this).val();
        $(this).val($(this).unitConvert({
            value: curValue,
            from: from,
            to: to
        }));
    });
 }); 
         $('#name').change(function(){
             alert(hello);
         if ($(this).val() == '') {
           $('#name').prop('disabled', true);
            $('#button-save').prop('disabled', true);
       }
           else {             
               $('#type').prop('disabled', false);
               $('#barcode').prop('disabled', true); 
               $('#name').prop('disabled', false);
               $('#comment').focus();
       }  
    });  
});
