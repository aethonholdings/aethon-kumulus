<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div id="searchPop" style="display: none">
  <!--<g:form name="myform" action="getNodeFromTree"  controller="node" >-->
                    <p><bold>Search for Barcode</bold></p>
                    <div class="input" id="searchInput">
                        <g:textField name="enterBarcode" value="" id="barcodeSearch" placeholder="Enter the Barcode"/>
                    </div>
                       
              <input type="SUBMIT"  name="search" value="search" onclick="search()" />
              
              <a href="#" class="closePop" id="closePopup" onclick="hidePopup();" >close </a>

              <!--</g:form>-->
    </div>
