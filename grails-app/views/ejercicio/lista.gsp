
<%@ page import="contabilidad.Ejercicio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ejercicio.label', default: 'Ejercicio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ejercicio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ejercicio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'ejercicio.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="estatus" title="${message(code: 'ejercicio.estatus.label', default: 'Estatus')}" />
					
						<th><g:message code="ejercicio.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'ejercicio.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'ejercicio.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ejercicios}" status="i" var="ejercicio">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${ejercicio.id}">${fieldValue(bean: ejercicio, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: ejercicio, field: "estatus")}</td>
					
						<td>${fieldValue(bean: ejercicio, field: "empresa")}</td>
					
						<td><g:formatDate date="${ejercicio.dateCreated}" /></td>
					
						<td><g:formatDate date="${ejercicio.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeEjercicios}" />
			</div>
		</div>
	</body>
</html>
