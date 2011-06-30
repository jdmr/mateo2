
<%@ page import="general.Proveedor" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proveedor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-proveedor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'proveedor.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="nombreCompleto" title="${message(code: 'proveedor.nombreCompleto.label', default: 'Nombre Completo')}" />
					
						<g:sortableColumn property="rfc" title="${message(code: 'proveedor.rfc.label', default: 'Rfc')}" />
					
						<g:sortableColumn property="curp" title="${message(code: 'proveedor.curp.label', default: 'Curp')}" />
					
						<g:sortableColumn property="direccion" title="${message(code: 'proveedor.direccion.label', default: 'Direccion')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'proveedor.telefono.label', default: 'Telefono')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proveedorInstanceList}" status="i" var="proveedorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${proveedorInstance.id}">${fieldValue(bean: proveedorInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: proveedorInstance, field: "nombreCompleto")}</td>
					
						<td>${fieldValue(bean: proveedorInstance, field: "rfc")}</td>
					
						<td>${fieldValue(bean: proveedorInstance, field: "curp")}</td>
					
						<td>${fieldValue(bean: proveedorInstance, field: "direccion")}</td>
					
						<td>${fieldValue(bean: proveedorInstance, field: "telefono")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proveedorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
