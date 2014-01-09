var state;

$(document).ready(function(){
    $.getJSON("/front-end/collect/refreshTree/1", function(result){
        // create the node tree
        $('#button-edit').prop('disabled', true);
        $('#nodeTree').on('changed.jstree', function (e, data) {
            var node=data.instance.get_node(data.selected[0]);
            refreshContainerInformation(node);
        });
        $('#nodeTree').jstree({
            'core': {
                'data' : result,
                'multiple' : false,
                'check_callback' : true,
                'plugins' : ['dnd']
            }
        });
        state = "READY";
    });
});

function findNode(id) {
    var i;
    for(i=0;i<nodes.length;i++){
        if(nodes[i].id==id) return(nodes[i]);
    }
}

function add_node() {
    var tree = $('#nodeTree').jstree(true);
    var parent = tree.get_selected();
    if(!parent.length) { return false; }
    parent = parent[0];
    tree.open_node(parent);
    var node = tree.create_node(parent, {'type':'C'});
    if(node) {
        $('#barcode').val('');
        $('#comment').val('');
        $('#type').val('Container');
        tree.edit(node);
        state = "CREATE NEW";
    }
};

function delete_node() {
    var ref = $('#nodeTree').jstree(true),
        sel = ref.get_selected();
    if(!sel.length) { return false; }
    ref.delete_node(sel);
};

function edit_node() {
    if($('#button-edit').prop('disabled')==false) toggle_input_disabled(false);
};

function toggle_input_disabled(bool) {
    $('#type').prop('disabled', bool);
    $('#comment').prop('disabled', bool); 
}

function cancel() {
    var tree = $('#nodeTree').jstree(true);
    var nodes = tree.get_selected();
    if(nodes.length) {
        var node = tree.get_node(nodes[0]);
        refreshContainerInformation(node);
    }
}

function refreshContainerInformation(node) {
    if(node.original.type=='ROOT') $('#button-edit').prop('disabled', true); else $('#button-edit').prop('disabled', false);
    $('#barcode').val(node.original.barcode);
    $('#comment').val(node.original.comment);
    $('#type').val(node.original.type);
    toggle_input_disabled(true);
}
