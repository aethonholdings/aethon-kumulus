$(document).ready(function(){
    
    

});

function selectPage(pageImage){

    var viewImageId = pageImage.getAttribute('viewId');
    var previewImage = $('#preview-img');
    previewImage.hide();
    previewImage.attr('src', '/front-end/image/get/'+viewImageId);
    // need to bind to DOM object in order to ensure load event binding works
    previewImage.load(function(){
        scaleImage(previewImage);
        previewImage.show();
    })  
}   

// rescales an image to fit parent container, maintaining aspect ratio
function scaleImage(image) {
    
    var height;
    var width;
    
    // determine the target dimensions
    var containerWidth = image.parent().width();
    var containerHeight = image.parent().height();
    var containerAspectRatio = containerHeight / containerWidth;
    
    // determine the source dimensions
    var sourceWidth = image.width();
    var sourceHeight = image.height();
    var sourceAspectRatio = sourceHeight / sourceWidth;
    
    if(containerAspectRatio > sourceAspectRatio) {
        width = containerWidth;
        height = sourceAspectRatio * width;

    } else {
        height = containerHeight;
        width = height / sourceAspectRatio;

    }
    
    image.height(height);
    image.width(width);
}

        
    