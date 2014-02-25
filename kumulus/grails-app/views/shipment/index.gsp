
<%@ page import="com.kumulus.domain.Shipment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shipment.label', default: 'Shipment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-shipment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-shipment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="notes" title="${message(code: 'shipment.notes.label', default: 'Notes')}" />
					
						<g:sortableColumn property="started" title="${message(code: 'shipment.started.label', default: 'Started')}" />
					
						<g:sortableColumn property="finished" title="${message(code: 'shipment.finished.label', default: 'Finished')}" />
					
						<g:sortableColumn property="fromCompany" title="${message(code: 'shipment.fromCompany.label', default: 'From Company')}" />
					
						<g:sortableColumn property="scheduled" title="${message(code: 'shipment.scheduled.label', default: 'Scheduled')}" />
					
						<g:sortableColumn property="toCompany" title="${message(code: 'shipment.toCompany.label', default: 'To Company')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${shipmentInstanceList}" status="i" var="shipmentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${shipmentInstance.id}">${fieldValue(bean: shipmentInstance, field: "notes")}</g:link></td>
					
						<td><g:formatDate date="${shipmentInstance.started}" /></td>
					
						<td><g:formatDate date="${shipmentInstance.finished}" /></td>
					
						<td>${fieldValue(bean: shipmentInstance, field: "fromCompany")}</td>
					
						<td><g:formatDate date="${shipmentInstance.scheduled}" /></td>
					
						<td>${fieldValue(bean: shipmentInstance, field: "toCompany")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${shipmentInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
