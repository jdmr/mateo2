
<%@ page import="general.Empresa" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empresa.label', default: 'Empresa')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empresa" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'empresa.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="nombreCompleto" title="${message(code: 'empresa.nombreCompleto.label', default: 'Nombre Completo')}" />
					
						<th><g:message code="empresa.organizacion.label" default="Organizacion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${empresas}" status="i" var="empresa">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${empresa.id}">${fieldValue(bean: empresa, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: empresa, field: "nombreCompleto")}</td>
					
						<td>${fieldValue(bean: empresa, field: "organizacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeEmpresas}" />
			</div>
		</div>
	</body>
</html>
