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
                $('#preview-img').hide();
            }
        });
    }
}
