$(document).ready(function(){    
    containerViewer = new ContainerViewer("view");
    uploader = new Uploader($(".kumulus-uploader-form"));
    tree = new NodeTree("#nodeTree", $('#project').attr('projectID'), function(node){
        containerViewer.update(node);
        if(node && node.data.key!="#") uploader.enable(node); else uploader.disable();
    });
});