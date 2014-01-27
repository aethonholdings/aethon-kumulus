$(document).ready(function(){
    $("#pages").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    $("#documents").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    
    $("#pages, #documents").disableSelection();
});

function selectPage(pageImage){

    var viewImageId = pageImage.getAttribute('viewId');
    var previewImage = $('#preview-img');
    previewImage.hide();
    previewImage.attr('src', url('image', 'get', viewImageId));
    // need to bind to DOM object in order to ensure load event binding works
    previewImage.load(function(){
        scaleImage(previewImage);
        previewImage.attr('alt', viewImageId);
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

function zoom() {
    var image = $('#preview-img');
    var zoomWindow = window.open('', 'newwindow');
    zoomWindow.document.write('<img src="' + url('image', 'get', image.attr('alt')) + '">')
}

function save() {
    var documents = $('#documents li');
    var documentIds = [];
    documents.each(function(i, li){
        documentIds.push($(li).attr('documentId'));
    });
    if(documents.length>0) {
        var data = {documents: documentIds};
        $.ajax({
            url: url('document', 'merge', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(response) {
                if(response.done) $('#documents').empty();
            }
        });
    }
}

function url(controller, action, parameterString){
    var urlString = '/kumulus/' + controller + '/' + action + '/' + parameterString;
    return(urlString);
}
    