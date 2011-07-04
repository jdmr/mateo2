
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
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="inventario"><g:message code="inventario.label" default="Inventario" />&nbsp;&gt;</g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
				<g:each in="${tipoProductos}" status="i" var="tipoProducto">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${tipoProducto.id}">${fieldValue(bean: tipoProducto, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoProducto, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: tipoProducto, field: "almacen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeTipoProductos}" />
			</div>
		</div>
	</body>
</html>
