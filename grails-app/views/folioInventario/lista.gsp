
<%@ page import="inventario.FolioInventario" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'folioInventario.label', default: 'FolioInventario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-folioInventario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-folioInventario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'folioInventario.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="valor" title="${message(code: 'folioInventario.valor.label', default: 'Valor')}" />
					
						<th><g:message code="folioInventario.almacen.label" default="Almacen" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${folioInventarios}" status="i" var="folioInventario">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${folioInventario.id}">${fieldValue(bean: folioInventario, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: folioInventario, field: "valor")}</td>
					
						<td>${fieldValue(bean: folioInventario, field: "almacen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeFolioInventarios}" />
			</div>
		</div>
	</body>
</html>
