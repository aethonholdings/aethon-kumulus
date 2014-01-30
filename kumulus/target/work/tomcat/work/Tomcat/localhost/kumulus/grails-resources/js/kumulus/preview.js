function preview(element, imageId) {
    element.load(function () {
        scaleImage(element, false);
        element.attr('alt', imageId);
        element.show();        
    });
    element.hide();
    element.attr('src', url('image', 'get', imageId));
    element.click(function () {        
        var zoomWindow = window.open('', 'newwindow');
        zoomWindow.document.write('<html>');
        zoomWindow.document.write('<body>');
        zoomWindow.document.write('<img src="' + url('image', 'get', imageId) + '" width="100%" height="100%">');
        zoomWindow.document.write('</body>');
        zoomWindow.document.write('</html>');        
    });
}

// rescales an image to fit parent container, maintaining aspect ratio
function scaleImage(image, fit) {
    
    var height;
    var width;
    
    // determine the target dimensions
    var containerWidth = image.parent().width();
    var containerHeight = image.parent().height();
    var containerAspectRatio = containerHeight / containerWidth;
    
    if(fit) {
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
    } else {
        if(containerHeight>containerWidth) {
            height = containerHeight;
            width = height / sourceAspectRatio;
        } else {
            width = containerWidth;
            height = sourceAspectRatio * width;
        }
    }
    
    image.height(height);
    image.width(width);
}

