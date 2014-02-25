
<%@ page import="com.kumulus.domain.Shipment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shipment.label', default: 'Shipment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-shipment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-shipment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list shipment">
			
				<g:if test="${shipmentInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="shipment.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${shipmentInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.started}">
				<li class="fieldcontain">
					<span id="started-label" class="property-label"><g:message code="shipment.started.label" default="Started" /></span>
					
						<span class="property-value" aria-labelledby="started-label"><g:formatDate date="${shipmentInstance?.started}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.finished}">
				<li class="fieldcontain">
					<span id="finished-label" class="property-label"><g:message code="shipment.finished.label" default="Finished" /></span>
					
						<span class="property-value" aria-labelledby="finished-label"><g:formatDate date="${shipmentInstance?.finished}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.barcodes}">
				<li class="fieldcontain">
					<span id="barcodes-label" class="property-label"><g:message code="shipment.barcodes.label" default="Barcodes" /></span>
					
						<g:each in="${shipmentInstance.barcodes}" var="b">
						<span class="property-value" aria-labelledby="barcodes-label"><g:link controller="barcode" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.fromCompany}">
				<li class="fieldcontain">
					<span id="fromCompany-label" class="property-label"><g:message code="shipment.fromCompany.label" default="From Company" /></span>
					
						<span class="property-value" aria-labelledby="fromCompany-label"><g:fieldValue bean="${shipmentInstance}" field="fromCompany"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.nodes}">
				<li class="fieldcontain">
					<span id="nodes-label" class="property-label"><g:message code="shipment.nodes.label" default="Nodes" /></span>
					
						<g:each in="${shipmentInstance.nodes}" var="n">
						<span class="property-value" aria-labelledby="nodes-label"><g:link controller="node" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.scheduled}">
				<li class="fieldcontain">
					<span id="scheduled-label" class="property-label"><g:message code="shipment.scheduled.label" default="Scheduled" /></span>
					
						<span class="property-value" aria-labelledby="scheduled-label"><g:formatDate date="${shipmentInstance?.scheduled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${shipmentInstance?.toCompany}">
				<li class="fieldcontain">
					<span id="toCompany-label" class="property-label"><g:message code="shipment.toCompany.label" default="To Company" /></span>
					
						<span class="property-value" aria-labelledby="toCompany-label"><g:fieldValue bean="${shipmentInstance}" field="toCompany"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:shipmentInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${shipmentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
