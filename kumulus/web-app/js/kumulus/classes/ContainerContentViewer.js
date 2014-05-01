function ContainerContentViewer(elementId) {
    var instance = this;
    instance.element = $(elementId);
    instance.node = null;
    
    instance.update = function(node) {
        instance.node = node;
        instance.element.find("tbody tr").remove();
        if(node!=null) {
            request(url('node', 'getDocuments', ''), {node: node.data.id}, function(data) {
                $.each(data, function(i) {
                    var imgUrl = url('image','get', data[i].thumbnailImageId);
                    var status = data[i].status;
                    instance.element.find("tbody").append(
                        '<tr><td><a href="#"><img class="kumulus-thumbnail" height="140" width="100"  src='+ imgUrl +' onClick="new DocumentViewer(' + data[i].id + ')"/></a></td><td>'+status+'</td></tr>'
                    );
                });
            });
        }
    }
}

