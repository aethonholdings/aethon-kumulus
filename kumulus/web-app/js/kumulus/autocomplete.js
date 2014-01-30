$(document).ready(function() {
    $("#company").autocomplete({
        source: url("company", "search", ""),
        minLength: 2,
        select: function(event, ui) {  
        }
    });
});