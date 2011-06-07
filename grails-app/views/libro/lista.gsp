
<%@ page import="contabilidad.Libro" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'libro.label', default: 'Libro')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-libro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-libro" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'libro.nombre.label', default: 'Nombre')}" />
					
						<th><g:message code="libro.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'libro.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'libro.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${libros}" status="i" var="libro">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${libro.id}">${fieldValue(bean: libro, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: libro, field: "empresa")}</td>
					
						<td><g:formatDate date="${libro.dateCreated}" /></td>
					
						<td><g:formatDate date="${libro.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeLibros}" />
			</div>
		</div>
	</body>
</html>
