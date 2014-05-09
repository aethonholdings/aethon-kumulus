<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/base.js'/>
    <g:javascript src='kumulus/upload.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <g:javascript src='kumulus/containerViewer.js'/>
    <g:javascript src='kumulus/uploader.js'/>
    <g:javascript src='kumulus/documentViewer.js'/>
    <title>Upload scans | Kumulus</title>
  </head>
  <body>
    <span id="project" projectID="${project.id}"></span>  
    <div class="pure-g">
      <div class="pure-u-1-3">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget-3-5">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Archive contents</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-node-tree kumulus-scrollable-y kumulus-scrollable-x">
              <div id="nodeTree" class="jstree-draggable"></div>
            </div>            
          </div>
          <div class="kumulus-widget-base kumulus-widget-2-5">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Container details</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="pure-form pure-form-aligned">
              <div class="pure-control-group">
                <label for="barcode">Barcode:</label>
                <input name="barcode" type="text" id="nodeBarcode" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="type">Type:</label>
                <input name="type" type="text" id="nodeType" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="location">Location:</label>
                <input name="location" type="text" id="nodeLocation" class="pure-u-1-2" readonly=""/>
              </div>
              <div class="pure-control-group">
                <label for="status">Status:</label>
                <input name="status" type="text" id="nodeStatus" class="pure-u-1-2" readonly=""/>
              </div>
            </div>
            <div id ="nodeActions" class="kumulus-button-bank"></div>
          </div> 
        </div>
      </div>
      <div class="pure-u-1-3">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget kumulus-scrollable-y">
            <div class="kumulus-container-for-collect">
              <div class="kumulus-widget-header">
                <span class="kumulus-widget-header-title">Container contents</span>
              </div>
              <div class="kumulus-scrollable-y">
                <table id="pageInfo" class="pure-table pure-table-horizontal kumulus-table-no-border">
                  <tbody> 
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div> 
      <div class="pure-u-1-3">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Select upload page</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-data-entry">
              <form action="/kumulus/fileUploader/process/-1" method="post" enctype="multipart/form-data" class="kumulus-uploader-form">
                <input type='hidden' name='upload' value='image' />
                <input type='hidden' name='errorAction' value='error' />
                <input type='hidden' name='errorController' value='test' />
                <input type='hidden' name='successAction' value='upload' />
                <input type='hidden' name='successController' value='image' />
                <input type='file' name='file' class='kumulus-uploader pure-button-disabled' disabled/>
                <input type='submit' name='submit' value='Submit' class='kumulus-uploader pure-button-disabled' disabled/>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
