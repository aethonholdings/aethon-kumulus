<%@ page import="com.kumulus.domain.Shipment" %>



<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="shipment.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="500" value="${shipmentInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'started', 'error')} ">
	<label for="started">
		<g:message code="shipment.started.label" default="Started" />
		
	</label>
	<g:datePicker name="started" precision="day"  value="${shipmentInstance?.started}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'finished', 'error')} ">
	<label for="finished">
		<g:message code="shipment.finished.label" default="Finished" />
		
	</label>
	<g:datePicker name="finished" precision="day"  value="${shipmentInstance?.finished}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'barcodes', 'error')} ">
	<label for="barcodes">
		<g:message code="shipment.barcodes.label" default="Barcodes" />
		
	</label>
	<g:select name="barcodes" from="${com.kumulus.domain.Barcode.list()}" multiple="multiple" optionKey="id" size="5" value="${shipmentInstance?.barcodes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'fromCompany', 'error')} ">
	<label for="fromCompany">
		<g:message code="shipment.fromCompany.label" default="From Company" />
		
	</label>
	<g:textField name="fromCompany" value="${shipmentInstance?.fromCompany}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'nodes', 'error')} ">
	<label for="nodes">
		<g:message code="shipment.nodes.label" default="Nodes" />
		
	</label>
	<g:select name="nodes" from="${com.kumulus.domain.Node.list()}" multiple="multiple" optionKey="id" size="5" value="${shipmentInstance?.nodes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'scheduled', 'error')} required">
	<label for="scheduled">
		<g:message code="shipment.scheduled.label" default="Scheduled" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="scheduled" precision="day"  value="${shipmentInstance?.scheduled}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: shipmentInstance, field: 'toCompany', 'error')} ">
	<label for="toCompany">
		<g:message code="shipment.toCompany.label" default="To Company" />
		
	</label>
	<g:textField name="toCompany" value="${shipmentInstance?.toCompany}"/>
</div>

