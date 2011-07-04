
<%@ page import="inventario.Entrada" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entrada.label', default: 'Entrada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-entrada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="inventario"><g:message code="inventario.label" default="Inventario" />&nbsp;&gt;</g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-entrada" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'entrada.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="factura" title="${message(code: 'entrada.factura.label', default: 'Factura')}" />
					
						<g:sortableColumn property="fechaFactura" title="${message(code: 'entrada.fechaFactura.label', default: 'Fecha Factura')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'entrada.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="total" title="${message(code: 'entrada.total.label', default: 'Total')}" />
					
						<g:sortableColumn property="tipoCambio" title="${message(code: 'entrada.tipoCambio.label', default: 'Tipo Cambio')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${entradas}" status="i" var="entrada">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${entrada.id}">${fieldValue(bean: entrada, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: entrada, field: "factura")}</td>
					
						<td><g:formatDate date="${entrada.fechaFactura}" /></td>
					
						<td>${fieldValue(bean: entrada, field: "iva")}</td>
					
						<td>${fieldValue(bean: entrada, field: "total")}</td>
					
						<td>${fieldValue(bean: entrada, field: "tipoCambio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeEntradas}" />
			</div>
		</div>
	</body>
</html>
