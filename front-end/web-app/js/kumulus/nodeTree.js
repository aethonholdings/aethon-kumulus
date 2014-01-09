$(document).ready(function(){
    $.getJSON("/front-end/collect/refreshTree/1", function(result){
        // create the nodetree
        $('#nodeTree').on('changed.jstree', function (e, data) {
            var node=data.instance.get_node(data.selected[0]);
            $('#barcode').val(node.original.barcode);
            $('#comment').val(node.original.comment);
            $('#type').val(node.original.type);
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

function findNode(id) {
    var i;
    for(i=0;i<nodes.length;i++){
        if(nodes[i].id==id) return(nodes[i]);
    }
}



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