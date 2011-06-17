
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'servicio.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="servicio.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'servicio.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${servicioInstanceList}" status="i" var="servicioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${servicioInstance.id}">${fieldValue(bean: servicioInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: servicioInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: servicioInstance, field: "tags")}</td>
					
						<td><g:formatDate date="${servicioInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: servicioInstance, field: "empresa")}</td>
					
						<td><g:formatDate date="${servicioInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${servicioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
