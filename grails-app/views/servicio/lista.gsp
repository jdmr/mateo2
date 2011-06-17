
<%@ page import="contabilidad.Servicio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'servicio.label', default: 'Servicio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-servicio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-servicio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'servicio.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'servicio.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="tags" title="${message(code: 'servicio.tags.label', default: 'Tags')}" />
					
						<th><g:message code="servicio.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'servicio.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'servicio.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${servicios}" status="i" var="servicio">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${servicio.id}">${fieldValue(bean: servicio, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: servicio, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: servicio, field: "tags")}</td>
					
						<td>${fieldValue(bean: servicio, field: "empresa")}</td>
					
						<td><g:formatDate date="${servicio.dateCreated}" /></td>
					
						<td><g:formatDate date="${servicio.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeServicios}" />
			</div>
		</div>
	</body>
</html>
