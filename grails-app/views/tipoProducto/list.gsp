
<%@ page import="inventario.TipoProducto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoProducto.label', default: 'TipoProducto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoProducto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoProducto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoProducto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoProducto.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="tipoProducto.almacen.label" default="Almacen" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoProductoInstanceList}" status="i" var="tipoProductoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoProductoInstance.id}">${fieldValue(bean: tipoProductoInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoProductoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: tipoProductoInstance, field: "almacen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoProductoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
