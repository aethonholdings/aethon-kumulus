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
       }
           else {  
               $('#name').prop('disabled', false);
               $('#barcode').prop('disabled', true);
                 $('#type').prop('disabled', false);
               $('#type').focus();
          }
  }); 
  $('#type').change(function(){
         if ($(this).val() == '') {      
           $('#type').prop('disabled', true);
           $('#button-save').prop('disabled', true);
       }
           else {
               $('#comment').prop('disabled', false);
               $('#barcode').prop('disabled', true);
               $('#name').prop('disabled', false);           
              $('#name').focus();
              $('#button-save').prop('disabled', false);
          }  
    });
    
         $('#name').change(function(){
         if ($(this).val() == '') {
           $('#name').prop('disabled', true);
           
       }
           else {             
               $('#type').prop('disabled', false);
               $('#barcode').prop('disabled', true); 
               $('#name').prop('disabled', false);
               $('#comment').focus();
               
              
          }  
    });
    
    
  
    
    
//    $('#comment').change(function(){
//         if ($(this).val() == '') {
//             $('#comment').prop('disabled', true);
//       }
//           else {
//               $('#comment').prop('disabled', false);
//               $('#barcode').prop('disabled', true);
//               $('#type').prop('disabled', true);
//       }  
//    });
    
     
});
