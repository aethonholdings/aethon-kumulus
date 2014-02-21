
<%@ page import="com.kumulus.domain.Barcode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'barcode.label', default: 'Barcode')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-barcode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-barcode" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list barcode">
			
				<g:if test="${barcodeInstance?.printed}">
				<li class="fieldcontain">
					<span id="printed-label" class="property-label"><g:message code="barcode.printed.label" default="Printed" /></span>
					
						<span class="property-value" aria-labelledby="printed-label"><g:formatBoolean boolean="${barcodeInstance?.printed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${barcodeInstance?.text}">
				<li class="fieldcontain">
					<span id="text-label" class="property-label"><g:message code="barcode.text.label" default="Text" /></span>
					
						<span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${barcodeInstance}" field="text"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:barcodeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${barcodeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
