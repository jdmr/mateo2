
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
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cancelacionAlmacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cancelacionAlmacen">
			
				<g:if test="${cancelacionAlmacen?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="cancelacionAlmacen.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${cancelacionAlmacen}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="cancelacionAlmacen.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${cancelacionAlmacen}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.entrada}">
				<li class="fieldcontain">
					<span id="entrada-label" class="property-label"><g:message code="cancelacionAlmacen.entrada.label" default="Entrada" /></span>
					
						<span class="property-value" aria-labelledby="entrada-label"><g:link controller="entrada" action="show" id="${cancelacionAlmacen?.entrada?.id}">${cancelacionAlmacen?.entrada?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.salida}">
				<li class="fieldcontain">
					<span id="salida-label" class="property-label"><g:message code="cancelacionAlmacen.salida.label" default="Salida" /></span>
					
						<span class="property-value" aria-labelledby="salida-label"><g:link controller="salida" action="show" id="${cancelacionAlmacen?.salida?.id}">${cancelacionAlmacen?.salida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.creador}">
				<li class="fieldcontain">
					<span id="creador-label" class="property-label"><g:message code="cancelacionAlmacen.creador.label" default="Creador" /></span>
					
						<span class="property-value" aria-labelledby="creador-label"><g:fieldValue bean="${cancelacionAlmacen}" field="creador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.almacen}">
				<li class="fieldcontain">
					<span id="almacen-label" class="property-label"><g:message code="cancelacionAlmacen.almacen.label" default="Almacen" /></span>
					
						<span class="property-value" aria-labelledby="almacen-label"><g:link controller="almacen" action="show" id="${cancelacionAlmacen?.almacen?.id}">${cancelacionAlmacen?.almacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="cancelacionAlmacen.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${cancelacionAlmacen?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.entradas}">
				<li class="fieldcontain">
					<span id="entradas-label" class="property-label"><g:message code="cancelacionAlmacen.entradas.label" default="Entradas" /></span>
					
						<g:each in="${cancelacionAlmacen.entradas}" var="e">
						<span class="property-value" aria-labelledby="entradas-label"><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="cancelacionAlmacen.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${cancelacionAlmacen?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cancelacionAlmacen?.salidas}">
				<li class="fieldcontain">
					<span id="salidas-label" class="property-label"><g:message code="cancelacionAlmacen.salidas.label" default="Salidas" /></span>
					
						<g:each in="${cancelacionAlmacen.salidas}" var="s">
						<span class="property-value" aria-labelledby="salidas-label"><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cancelacionAlmacen?.id}" />
					<g:link class="edit" action="edita" id="${cancelacionAlmacen?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
