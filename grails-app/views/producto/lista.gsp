
<%@ page import="inventario.Producto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="inventario"><g:message code="inventario.label" default="Inventario" />&nbsp;&gt;</g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-producto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'producto.codigo.label', default: 'Codigo')}" />

                        <g:sortableColumn property="sku" title="${message(code: 'producto.sku.label', default: 'Sku')}" />

						<g:sortableColumn property="nombre" title="${message(code: 'producto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'producto.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="marca" title="${message(code: 'producto.marca.label', default: 'Marca')}" />
					
						<g:sortableColumn property="modelo" title="${message(code: 'producto.modelo.label', default: 'Modelo')}" />
					
						<g:sortableColumn property="unidadMedida" title="${message(code: 'producto.unidadMedida.label', default: 'Unidad de Medida')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${productos}" status="i" var="producto">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${producto.id}">${fieldValue(bean: producto, field: "codigo")}</g:link></td>
					
                                                <td>${fieldValue(bean: producto, field: "sku")}</td>

                                                <td>${fieldValue(bean: producto, field: "nombre")}</td>
					
						<td>${fieldValue(bean: producto, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: producto, field: "marca")}</td>
					
						<td>${fieldValue(bean: producto, field: "modelo")}</td>
					
						<td>${fieldValue(bean: producto, field: "unidadMedida")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeProductos}" />
			</div>
		</div>
	</body>
</html>
