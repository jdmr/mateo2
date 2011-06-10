
<%@ page import="contabilidad.Poliza" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-poliza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevaIngreso"><g:message code="poliza.nueva.ingreso.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-poliza" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'poliza.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'poliza.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="estatus" title="${message(code: 'poliza.estatus.label', default: 'Estatus')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'poliza.tipo.label', default: 'Tipo')}" />
					
						<th><g:message code="poliza.libro.label" default="Libro" /></th>
					
						<th><g:message code="poliza.ejercicio.label" default="Ejercicio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${polizas}" status="i" var="poliza">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${poliza.id}">${fieldValue(bean: poliza, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: poliza, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: poliza, field: "estatus")}</td>
					
						<td>${fieldValue(bean: poliza, field: "tipo")}</td>
					
						<td>${fieldValue(bean: poliza, field: "libro")}</td>

						<td>${fieldValue(bean: poliza, field: "ejercicio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDePolizas}" />
			</div>
		</div>
	</body>
</html>
