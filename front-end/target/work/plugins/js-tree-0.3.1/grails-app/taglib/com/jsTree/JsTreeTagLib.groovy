package com.jsTree

class JsTreeTagLib {

   static namespace = 'jsTree'

   def resources = { attrs ->
      out << """
    	<script type="text/javascript" src="${g.resource(plugin: "jsTree", dir:'js/jstree_pre1.0_fix', file: "jquery.jstree.js").encodeAsHTML()}"></script>
    	<script type="text/javascript" src="${g.resource(plugin: "jsTree", dir:'js/jstree_pre1.0_fix//_lib', file: "jquery.cookie.js").encodeAsHTML()}"></script>
    	<script type="text/javascript" src="${g.resource(plugin: "jsTree", dir:'js/jstree_pre1.0_fix//_lib', file: "jquery.hotkeys.js").encodeAsHTML()}"></script>
    	"""
   }

}



