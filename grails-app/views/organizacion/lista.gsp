
<%@ page import="general.Organizacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'organizacion.label', default: 'Organizacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-organizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-organizacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'organizacion.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="nombreCompleto" title="${message(code: 'organizacion.nombreCompleto.label', default: 'Nombre Completo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${organizaciones}" status="i" var="organizacion">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${organizacion.id}">${fieldValue(bean: organizacion, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: organizacion, field: "nombreCompleto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeOrganizaciones}" />
			</div>
		</div>
	</body>
</html>
