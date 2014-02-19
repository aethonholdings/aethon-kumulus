<%@ page import="com.kumulus.domain.Barcode" %>



<div class="fieldcontain ${hasErrors(bean: barcodeInstance, field: 'printed', 'error')} ">
	<label for="printed">
		<g:message code="barcode.printed.label" default="Printed" />
		
	</label>
	<g:checkBox name="printed" value="${barcodeInstance?.printed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: barcodeInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="barcode.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${barcodeInstance?.text}"/>
</div>

