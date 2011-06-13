
<%@ page import="inventario.FacturaAlmacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-facturaAlmacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-facturaAlmacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list facturaAlmacen">
			
				<g:if test="${facturaAlmacenInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="facturaAlmacen.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${facturaAlmacenInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="facturaAlmacen.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${facturaAlmacenInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.iva}">
				<li class="fieldcontain">
					<span id="iva-label" class="property-label"><g:message code="facturaAlmacen.iva.label" default="Iva" /></span>
					
						<span class="property-value" aria-labelledby="iva-label"><g:fieldValue bean="${facturaAlmacenInstance}" field="iva"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="facturaAlmacen.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${facturaAlmacenInstance}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="facturaAlmacen.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${facturaAlmacenInstance}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.almacen}">
				<li class="fieldcontain">
					<span id="almacen-label" class="property-label"><g:message code="facturaAlmacen.almacen.label" default="Almacen" /></span>
					
						<span class="property-value" aria-labelledby="almacen-label"><g:link controller="almacen" action="show" id="${facturaAlmacenInstance?.almacen?.id}">${facturaAlmacenInstance?.almacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="facturaAlmacen.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="cliente" action="show" id="${facturaAlmacenInstance?.cliente?.id}">${facturaAlmacenInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="facturaAlmacen.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${facturaAlmacenInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.entradas}">
				<li class="fieldcontain">
					<span id="entradas-label" class="property-label"><g:message code="facturaAlmacen.entradas.label" default="Entradas" /></span>
					
						<g:each in="${facturaAlmacenInstance.entradas}" var="e">
						<span class="property-value" aria-labelledby="entradas-label"><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="facturaAlmacen.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${facturaAlmacenInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="facturaAlmacen.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${facturaAlmacenInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${facturaAlmacenInstance?.salidas}">
				<li class="fieldcontain">
					<span id="salidas-label" class="property-label"><g:message code="facturaAlmacen.salidas.label" default="Salidas" /></span>
					
						<g:each in="${facturaAlmacenInstance.salidas}" var="s">
						<span class="property-value" aria-labelledby="salidas-label"><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${facturaAlmacenInstance?.id}" />
					<g:link class="edit" action="edit" id="${facturaAlmacenInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
