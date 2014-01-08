$(document).ready(function(){
  $.getJSON("/front-end/collect/refreshTree/1", function(result){
    $('#nodeTree').on('changed.jstree', function (e, data) {
      var i, j, r = [];
      for(i = 0, j = data.selected.length; i < j; i++) {
        r.push(data.instance.get_node(data.selected[i]).text);
      }
      $('#workArea').html('Selected: ' + r.join(', '));
    });
    $('#nodeTree').jstree({'core': {'data': result}});});
  }
);

