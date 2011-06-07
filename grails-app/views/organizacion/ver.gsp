
<%@ page import="general.Organizacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'organizacion.label', default: 'Organizacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-organizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-organizacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list organizacion">
			
				<g:if test="${organizacion?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="organizacion.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${organizacion}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizacion?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="organizacion.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${organizacion}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${organizacion?.nombreCompleto}">
				<li class="fieldcontain">
					<span id="nombreCompleto-label" class="property-label"><g:message code="organizacion.nombreCompleto.label" default="Nombre Completo" /></span>
					
						<span class="property-value" aria-labelledby="nombreCompleto-label"><g:fieldValue bean="${organizacion}" field="nombreCompleto"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${organizacion?.id}" />
					<g:link class="edit" action="edita" id="${organizacion?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
