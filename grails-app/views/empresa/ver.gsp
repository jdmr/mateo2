
<%@ page import="general.Empresa" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empresa.label', default: 'Empresa')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-empresa" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list empresa">
			
				<g:if test="${empresa?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="empresa.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${empresa}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresa?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="empresa.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${empresa}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresa?.nombreCompleto}">
				<li class="fieldcontain">
					<span id="nombreCompleto-label" class="property-label"><g:message code="empresa.nombreCompleto.label" default="Nombre Completo" /></span>
					
						<span class="property-value" aria-labelledby="nombreCompleto-label"><g:fieldValue bean="${empresa}" field="nombreCompleto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresa?.organizacion}">
				<li class="fieldcontain">
					<span id="organizacion-label" class="property-label"><g:message code="empresa.organizacion.label" default="Organizacion" /></span>
					
						<span class="property-value" aria-labelledby="organizacion-label"><g:link controller="organizacion" action="ver" id="${empresa?.organizacion?.id}">${empresa?.organizacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${empresa?.id}" />
					<g:link class="edit" action="edita" id="${empresa?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
