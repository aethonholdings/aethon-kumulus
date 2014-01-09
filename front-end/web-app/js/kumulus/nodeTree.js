$(document).ready(function(){
    $.getJSON("/front-end/collect/refreshTree/1", function(result){
        $('#nodeTree').on('changed.jstree', function (e, data) {
            $('#barcode').val(data.instance.get_node(data.selected[0]).text);
            $('#parent').val("test");
            $('#comment').val("test");
            $('#type').val("test");
        });
        $('#nodeTree').jstree({
            'core': {
                'data' : result,
                'multiple' : false,
                'check_callback' : true,
                'plugins' : ['dnd']
            }
        });
        
    });
});

function add_node() {
    var ref = $('#nodeTree').jstree(true);
    var sel = ref.get_selected();
    if(!sel.length) { return false; }
    sel = sel[0];
    sel = ref.create_node(sel, {'type':'C'});
    if(sel) {
        ref.edit(sel);
    }
};

function delete_node() {
    var ref = $('#nodeTree').jstree(true),
        sel = ref.get_selected();
    if(!sel.length) { return false; }
    ref.delete_node(sel);
};