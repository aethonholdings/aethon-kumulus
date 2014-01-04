// Resource declarations for Resources plugin

modules = {

   'jsTree' {
      dependsOn 'jquery'

      resource url: [plugin: 'jsTree', dir:'js/jstree_pre1.0_fix', file:"jquery.jstree.js"], disposition: 'head'
      resource url: [plugin: 'jsTree', dir:'js/jstree_pre1.0_fix/_lib', file:"jquery.cookie.js"], disposition: 'head'
      resource url: [plugin: 'jsTree', dir:'js/jstree_pre1.0_fix/_lib', file:"jquery.hotkeys.js"], disposition: 'head'

      resource url: [plugin: 'jsTree', dir:'js/jstree_pre1.0_fix/themes/default', file:"style.css"], disposition: 'head'
   }

}
