$(document).ready(function(){
    
    

});

function selectPage(pageImage){
    // perform rescaling here   
    var viewImageId = pageImage.getAttribute('viewId');
    $('#preview').css('background-image', 'url(http://localhost:8080/front-end/image/get/'+viewImageId+')')
}   

        
    