$(document).ready(function(){    
    var containerViewer = new ContainerViewer("#containerViewer");
    var containerContentViewer = new ContainerContentViewer("#containerContentViewer");
    var uploader = new Uploader($(".kumulus-uploader-form"));
    var tree = new NodeTree("#nodeTree", $('#project').attr('projectID'), function(node){
        containerViewer.update(node);
        containerContentViewer.update(node);
        if(node && node.data.key!="#") uploader.enable(node); else uploader.disable();
    });
});