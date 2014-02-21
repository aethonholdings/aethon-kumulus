/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selectDate="";
var nodeId=[];
$(document).ready(function(){

  $("#divCalendar").datepicker({
       minDate: new Date(),
                onSelect: function (selectedDate) { 
                  
                   selectDate=selectedDate.toString(); 
            
                } 
            });
            
  getNodeList();  
  
  $("#sendPickupNode").click(function(){
//  alert($("input:checkbox:checked").length);
      if($("input:checkbox:checked").length!=0){
          $("input:checkbox:checked").each(function(i){
              if($(this).attr("checked",true)){
                  
  
                  nodeId[i]=$(this).attr("id")
                  $("#nodeId").val(nodeId)
              }
              
          });
          
          saveNode();
      }
      else{
          alert("Please select a node to send")
      }
   
  })
});



function getNodeList(){

                 
        $.ajax({
            url: url('node', 'list', ''),
            type: 'POST',
//            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
//                if(response.done) $('#documents').empty();
//                $('#preview-img').hide();
//                alert("<<<<"+ data[0].size)
                $.each(data,function(i){
          
                     $("#nodeTable").append('<tr><td>'+data[i].name+'</td><td>'+data[i].barcode+'</td><td><input type="checkbox" class="checkbox" name="node_checkbox" id="'+data[i].id+'"></td></tr>');  
                })
             
            }
        });
           
}


function saveNode(){

      if(selectDate!=""){
           var data = { node: nodeId,selectDate:selectDate}
       $.ajax({
            url: url('node', 'test', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {

                $.each(data,function(i){
          
                     $("#nodeTable").append('<tr><td>'+data[i].name+'</td><td>'+data[i].barcode+'</td><td><input type="checkbox" class="checkbox" name="node_checkbox" id="'+data[i].id+'"></td></tr>');  
                })
             
            }
        });
    }
    else{
        alert("Please select date")
    }
    
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


