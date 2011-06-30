
<%@ page import="general.Imagen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imagen.label', default: 'Imagen')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-imagen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-imagen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list imagen">
			
				<g:if test="${imagenInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="imagen.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${imagenInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${imagenInstance?.tipoContenido}">
				<li class="fieldcontain">
					<span id="tipoContenido-label" class="property-label"><g:message code="imagen.tipoContenido.label" default="Tipo Contenido" /></span>
					
						<span class="property-value" aria-labelledby="tipoContenido-label"><g:fieldValue bean="${imagenInstance}" field="tipoContenido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${imagenInstance?.tamano}">
				<li class="fieldcontain">
					<span id="tamano-label" class="property-label"><g:message code="imagen.tamano.label" default="Tamano" /></span>
					
						<span class="property-value" aria-labelledby="tamano-label"><g:fieldValue bean="${imagenInstance}" field="tamano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${imagenInstance?.archivo}">
				<li class="fieldcontain">
					<span id="archivo-label" class="property-label"><g:message code="imagen.archivo.label" default="Archivo" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${imagenInstance?.id}" />
					<g:link class="edit" action="edit" id="${imagenInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
