
<%@ page import="inventario.CancelacionAlmacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cancelacionAlmacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cancelacionAlmacen" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'cancelacionAlmacen.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="comentarios" title="${message(code: 'cancelacionAlmacen.comentarios.label', default: 'Comentarios')}" />
					
						<th><g:message code="cancelacionAlmacen.entrada.label" default="Entrada" /></th>
					
						<th><g:message code="cancelacionAlmacen.salida.label" default="Salida" /></th>
					
						<g:sortableColumn property="creador" title="${message(code: 'cancelacionAlmacen.creador.label', default: 'Creador')}" />
					
						<th><g:message code="cancelacionAlmacen.almacen.label" default="Almacen" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cancelacionAlmacenes}" status="i" var="cancelacionAlmacen">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${cancelacionAlmacen.id}">${fieldValue(bean: cancelacionAlmacen, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: cancelacionAlmacen, field: "comentarios")}</td>
					
						<td>${fieldValue(bean: cancelacionAlmacen, field: "entrada")}</td>
					
						<td>${fieldValue(bean: cancelacionAlmacen, field: "salida")}</td>
					
						<td>${fieldValue(bean: cancelacionAlmacen, field: "creador")}</td>
					
						<td>${fieldValue(bean: cancelacionAlmacen, field: "almacen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeCancelacionAlmacen}" />
			</div>
		</div>
	</body>
</html>
