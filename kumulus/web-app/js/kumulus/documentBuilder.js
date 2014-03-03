$(document).ready(function(){ 

    $("#pages").sortable({
        connectWith: "div",
        dropOnEmpty: true
    });

    $("#document-strip").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });

    $('#pages').droppable({
        over: function(event, ul) {
            var count = $("#document-strip li").length; 
            if(count==0){
                alert("empty");
            }
        }
    });
    $("#document-strip li").hover(
        function () {
            $("#document-strip").addClass("connectedSortable");
        });
    $("#document-strip span").hover(
        function () {
            $("#document-strip").removeClass("connectedSortable");
        });

  
    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function() {
        preview($('#preview-img'), $(this).attr('viewId'));
        $("#barcode").val($(this).attr('barcode'))
        $("#containername").val($(this).attr('containerName'))
        $("#containertype").val($(this).attr('containerType'))
        $("#comment").val($(this).attr('containerComment'))
    });
   
    $("#pages, #documents").disableSelection();
});
function save() {
    var documents = $('#document-strip li');
    var taskIds = [];
    documents.each(function(i, li){
        taskIds.push($(li).attr('taskId'));
    });

    if(documents.length>0) {
        var data = {tasks: taskIds};
        $.ajax({
            url: url('document', 'merge', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(response) {
                if(response.done) documents.empty();
                $('#preview-img').hide();
            }
        });
    }
}