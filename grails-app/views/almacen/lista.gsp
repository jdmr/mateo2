
<%@ page import="inventario.Almacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'almacen.label', default: 'Almacen')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-almacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-almacen" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'almacen.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'almacen.nombre.label', default: 'Nombre')}" />
					
<!--						<th><g:message code="almacen.empresa.label" default="Empresa" /></th>-->

                                                <g:sortableColumn property="nombreCompleto" title="${message(code: 'almacen.nombreCompleto.label', default: 'Nombre Completo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${almacenes}" status="i" var="almacen">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${almacen.id}">${fieldValue(bean: almacen, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: almacen, field: "nombre")}</td>
					
<!--						<td>${fieldValue(bean: almacen, field: "empresa")}</td>-->

                                                <td>${fieldValue(bean: almacen, field: "nombreCompleto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeAlmacenes}" />
			</div>
		</div>
	</body>
</html>
