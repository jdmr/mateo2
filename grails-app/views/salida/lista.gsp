
<%@ page import="inventario.Salida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salida.label', default: 'Salida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-salida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="inventario"><g:message code="inventario.label" default="Inventario" />&nbsp;&gt;</g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-salida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'salida.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="reporte" title="${message(code: 'salida.reporte.label', default: 'Reporte')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'salida.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="total" title="${message(code: 'salida.total.label', default: 'Total')}" />
					
						<g:sortableColumn property="empleado" title="${message(code: 'salida.empleado.label', default: 'Empleado')}" />
					
						<g:sortableColumn property="departamento" title="${message(code: 'salida.departamento.label', default: 'Departamento')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${salidas}" status="i" var="salida">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${salida.id}">${fieldValue(bean: salida, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: salida, field: "reporte")}</td>
					
						<td>${fieldValue(bean: salida, field: "iva")}</td>
					
						<td>${fieldValue(bean: salida, field: "total")}</td>
					
						<td>${fieldValue(bean: salida, field: "empleado")}</td>
					
						<td>${fieldValue(bean: salida, field: "departamento")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeSalidas}" />
			</div>
		</div>
	</body>
</html>
