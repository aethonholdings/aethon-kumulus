/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selectedDate,nodeId;
$(document).ready(function(){

  $("#divCalendar").datepicker({
       minDate: new Date(),
                onSelect: function (selectedDate) { 
                   selectedDate=selectedDate.toString(); 
                } 
            }); 
  getNodeList();  
  
  $("#sendPickupNode").click(function(){
      if($("input [name=node_checkbox]:checked").length!=0){
          $("input [name=node_checkbox]:checked").each(function(i){
              if($(this).attr("checked",true)){
                  nodeId[i]=$(this).attr("id")
                  $("#nodeId").val(nodeId)
              }
              
          });
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
                $.each(data,function(){
                    alert("KKK")
                     $("#nodeTable").append('<tr><td>'+data[0].name+'</td><td>'+data[0].barcode+'</td><td><input type="checkbox" name="node_checkbox" id="'+data[0].id+'"></td></tr>');  
                })
             
            }
        });
           
}


