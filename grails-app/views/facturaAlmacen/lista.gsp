
<%@ page import="inventario.FacturaAlmacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-facturaAlmacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-facturaAlmacen" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'facturaAlmacen.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="comentarios" title="${message(code: 'facturaAlmacen.comentarios.label', default: 'Comentarios')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'facturaAlmacen.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="total" title="${message(code: 'facturaAlmacen.total.label', default: 'Total')}" />
					
						<g:sortableColumn property="estatus" title="${message(code: 'facturaAlmacen.estatus.label', default: 'Estatus')}" />
					
						<th><g:message code="facturaAlmacen.almacen.label" default="Almacen" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${facturaAlmacenes}" status="i" var="facturaAlmacen">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${facturaAlmacen.id}">${fieldValue(bean: facturaAlmacen, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: facturaAlmacen, field: "comentarios")}</td>
					
						<td>${fieldValue(bean: facturaAlmacen, field: "iva")}</td>
					
						<td>${fieldValue(bean: facturaAlmacen, field: "total")}</td>
					
						<td>${fieldValue(bean: facturaAlmacen, field: "estatus")}</td>
					
						<td>${fieldValue(bean: facturaAlmacen, field: "almacen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeFacturaAlmacenes}" />
			</div>
		</div>
	</body>
</html>
