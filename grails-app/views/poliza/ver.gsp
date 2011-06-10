
<%@ page import="contabilidad.Poliza" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-poliza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevaIngreso"><g:message code="poliza.nueva.ingreso.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-poliza" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list poliza">
			
				<g:if test="${poliza?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="poliza.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${poliza}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="poliza.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${poliza}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="poliza.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${poliza}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="poliza.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${poliza}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.libro}">
				<li class="fieldcontain">
					<span id="libro-label" class="property-label"><g:message code="poliza.libro.label" default="Libro" /></span>
					
						<span class="property-value" aria-labelledby="libro-label"><g:link controller="libro" action="show" id="${poliza?.libro?.id}">${poliza?.libro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.ejercicio}">
				<li class="fieldcontain">
					<span id="ejercicio-label" class="property-label"><g:message code="poliza.ejercicio.label" default="Ejercicio" /></span>
					
						<span class="property-value" aria-labelledby="ejercicio-label"><g:link controller="ejercicio" action="show" id="${poliza?.ejercicio?.id}">${poliza?.ejercicio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="poliza.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${poliza?.empresa?.id}">${poliza?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="poliza.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${poliza?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="poliza.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${poliza?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${poliza?.id}" />
					<g:link class="edit" action="edita" id="${poliza?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
