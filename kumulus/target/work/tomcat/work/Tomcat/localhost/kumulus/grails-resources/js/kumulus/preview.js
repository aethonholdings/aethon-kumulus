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

function zoom() {
    var image = $('#preview-img');
    var zoomWindow = window.open('', 'newwindow');
    zoomWindow.document.write('<img src="' + url('image', 'get', image.attr('alt')) + '">')
}


