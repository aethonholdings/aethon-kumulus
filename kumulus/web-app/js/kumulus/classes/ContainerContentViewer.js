function ContainerContentViewer(elementId) {
    var instance = this;
    instance.element = $(elementId);
    
    instance.update = function(node) {
        $.ajax({
            url: url('node', 'getDocuments', ''),
            type: 'POST',
            data: JSON.stringify({ node: node.data.id }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(data) {
                $("#pageInfo tbody tr").remove();
                $.each(data, function(i) {
                    var imgUrl = url('image','get', data[i].thumbnailImageId);
                    var status = data[i].status;
                    $("#pageInfo tbody").append(
                        '<tr><td><a href="#"><img class="kumulus-thumbnail" height="140" width="100"  src='+ imgUrl +' onClick="new DocumentViewer(' + data[i].id + ')"/></a></td><td>'+status+'</td></tr>'
                    );
                });
            }
        });
    }
    
}

