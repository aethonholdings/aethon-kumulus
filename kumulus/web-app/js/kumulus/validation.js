/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
//    validate();
    $("#ClientName").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {  
        }
    });
    
      $("#editProject").click(function(){
 
        $("#edit div input,textarea").removeAttr("disabled");   
        $(this).attr('disabled', 'disabled');
    });

});


function validate(){

    	$("#structure ,#project, .add, #edit").validate({
             
		rules: {
                   
			company: "required",
			date: "required",
                        identifier:"required",
                        description:"required",
                        amount:"required",
                        projectName:"required",
                        ClientName:"required",
                        pageNo:"required"
                                            
	
		},
//		messages: {
//			company: "Please enter company name",
//			date: "Please select the date",
//                        identifier: "Please enter identifier",
//                        description: "Please enter description",
//                        tamount: "Please enter amount",
//                        projectName:"Please"
//	
//		}

}).form();
}


