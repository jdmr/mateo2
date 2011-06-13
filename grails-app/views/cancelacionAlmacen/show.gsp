
<%@ page import="inventario.CancelacionAlmacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cancelacionAlmacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cancelacionAlmacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cancelacionAlmacen">
			
				<g:if test="${cancelacionAlmacenInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="cancelacionAlmacen.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${cancelacionAlmacenInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="cancelacionAlmacen.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${cancelacionAlmacenInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.entrada}">
				<li class="fieldcontain">
					<span id="entrada-label" class="property-label"><g:message code="cancelacionAlmacen.entrada.label" default="Entrada" /></span>
					
						<span class="property-value" aria-labelledby="entrada-label"><g:link controller="entrada" action="show" id="${cancelacionAlmacenInstance?.entrada?.id}">${cancelacionAlmacenInstance?.entrada?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.salida}">
				<li class="fieldcontain">
					<span id="salida-label" class="property-label"><g:message code="cancelacionAlmacen.salida.label" default="Salida" /></span>
					
						<span class="property-value" aria-labelledby="salida-label"><g:link controller="salida" action="show" id="${cancelacionAlmacenInstance?.salida?.id}">${cancelacionAlmacenInstance?.salida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.creador}">
				<li class="fieldcontain">
					<span id="creador-label" class="property-label"><g:message code="cancelacionAlmacen.creador.label" default="Creador" /></span>
					
						<span class="property-value" aria-labelledby="creador-label"><g:fieldValue bean="${cancelacionAlmacenInstance}" field="creador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.almacen}">
				<li class="fieldcontain">
					<span id="almacen-label" class="property-label"><g:message code="cancelacionAlmacen.almacen.label" default="Almacen" /></span>
					
						<span class="property-value" aria-labelledby="almacen-label"><g:link controller="almacen" action="show" id="${cancelacionAlmacenInstance?.almacen?.id}">${cancelacionAlmacenInstance?.almacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="cancelacionAlmacen.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${cancelacionAlmacenInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.entradas}">
				<li class="fieldcontain">
					<span id="entradas-label" class="property-label"><g:message code="cancelacionAlmacen.entradas.label" default="Entradas" /></span>
					
						<g:each in="${cancelacionAlmacenInstance.entradas}" var="e">
						<span class="property-value" aria-labelledby="entradas-label"><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="cancelacionAlmacen.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${cancelacionAlmacenInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacenInstance?.salidas}">
				<li class="fieldcontain">
					<span id="salidas-label" class="property-label"><g:message code="cancelacionAlmacen.salidas.label" default="Salidas" /></span>
					
						<g:each in="${cancelacionAlmacenInstance.salidas}" var="s">
						<span class="property-value" aria-labelledby="salidas-label"><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cancelacionAlmacenInstance?.id}" />
					<g:link class="edit" action="edit" id="${cancelacionAlmacenInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
