
<%@ page import="general.TipoCliente" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoCliente.label', default: 'TipoCliente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoCliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoCliente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoCliente.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoCliente.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="margenUtilidad" title="${message(code: 'tipoCliente.margenUtilidad.label', default: 'Margen Utilidad')}" />
					
						<g:sortableColumn property="base" title="${message(code: 'tipoCliente.base.label', default: 'Base')}" />
					
						<th><g:message code="tipoCliente.empresa.label" default="Empresa" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoClienteInstanceList}" status="i" var="tipoClienteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoClienteInstance.id}">${fieldValue(bean: tipoClienteInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: tipoClienteInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: tipoClienteInstance, field: "margenUtilidad")}</td>
					
						<td><g:formatBoolean boolean="${tipoClienteInstance.base}" /></td>
					
						<td>${fieldValue(bean: tipoClienteInstance, field: "empresa")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoClienteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
