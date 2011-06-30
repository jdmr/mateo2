
<%@ page import="inventario.LoteEntrada" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'loteEntrada.label', default: 'LoteEntrada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-loteEntrada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-loteEntrada" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cantidad" title="${message(code: 'loteEntrada.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="precioUnitario" title="${message(code: 'loteEntrada.precioUnitario.label', default: 'Precio Unitario')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'loteEntrada.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'loteEntrada.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="loteEntrada.entrada.label" default="Entrada" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'loteEntrada.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${loteEntradas}" status="i" var="loteEntrada">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${loteEntrada.id}">${fieldValue(bean: loteEntrada, field: "cantidad")}</g:link></td>
					
						<td>${fieldValue(bean: loteEntrada, field: "precioUnitario")}</td>
					
						<td>${fieldValue(bean: loteEntrada, field: "iva")}</td>
					
						<td><g:formatDate date="${loteEntrada.dateCreated}" /></td>
					
						<td>${fieldValue(bean: loteEntrada, field: "entrada")}</td>
					
						<td><g:formatDate date="${loteEntrada.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeLoteEntradas}" />
			</div>
		</div>
	</body>
</html>
