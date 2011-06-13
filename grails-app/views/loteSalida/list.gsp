
<%@ page import="inventario.LoteSalida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'loteSalida.label', default: 'LoteSalida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-loteSalida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-loteSalida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cantidad" title="${message(code: 'loteSalida.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="precioUnitario" title="${message(code: 'loteSalida.precioUnitario.label', default: 'Precio Unitario')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'loteSalida.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'loteSalida.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'loteSalida.lastUpdated.label', default: 'Last Updated')}" />
					
						<th><g:message code="loteSalida.producto.label" default="Producto" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${loteSalidaInstanceList}" status="i" var="loteSalidaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${loteSalidaInstance.id}">${fieldValue(bean: loteSalidaInstance, field: "cantidad")}</g:link></td>
					
						<td>${fieldValue(bean: loteSalidaInstance, field: "precioUnitario")}</td>
					
						<td>${fieldValue(bean: loteSalidaInstance, field: "iva")}</td>
					
						<td><g:formatDate date="${loteSalidaInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${loteSalidaInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: loteSalidaInstance, field: "producto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${loteSalidaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
