$(document).ready(function() {
    $("#thumbnail-sortable").sortable();
    $("#thumbnail-sortable").disableSelection();
});

function selectPage(page) {
    $("#page").attr('src', '/front-end/download/root/20140120200910ABCDE/pages/thumbs/201401120200912WOHO.jpg')
}

