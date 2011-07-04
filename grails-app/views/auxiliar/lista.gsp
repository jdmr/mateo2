<%@ page import="contabilidad.Auxiliar" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auxiliar.label', default: 'Auxiliar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-auxiliar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="contabilidad"><g:message code="contabilidad.label" default="Contabilidad" />&nbsp;&gt;</g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-auxiliar" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numero" title="${message(code: 'auxiliar.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'auxiliar.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="auxiliar.organizacion.label" default="Organizacion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${auxiliares}" status="i" var="auxiliar">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${auxiliar.id}">${fieldValue(bean: auxiliar, field: "numero")}</g:link></td>
					
						<td>${fieldValue(bean: auxiliar, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: auxiliar, field: "organizacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeAuxiliares}" />
			</div>
		</div>
	</body>
</html>
