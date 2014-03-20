function preview(element, imageId) {
    element.load(function () {
        scaleImage(element, false);
        element.attr('alt', imageId);
        element.show();        
    });
    element.hide();
    element.attr('src', url('image', 'get', imageId));
    element.click(function () {        
        var zoomWindow = window.open('','New Window','width=800,height=600,toolbar=0,menubar=0,location=0, addressbar=0, status=1,scrollbars=1,resizable=1,left=5,top=5');
        zoomWindow.document.write('<html>');
        zoomWindow.document.write('<head><title>Popup</title></head>');
        zoomWindow.document.write('<body>');
        zoomWindow.document.write('<img src="' + url('image', 'get', imageId) + '">');
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

