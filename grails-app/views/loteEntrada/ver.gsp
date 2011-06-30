
<%@ page import="inventario.LoteEntrada" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'loteEntrada.label', default: 'LoteEntrada')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-loteEntrada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-loteEntrada" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list loteEntrada">
			
				<g:if test="${loteEntrada?.cantidad}">
				<li class="fieldcontain">
					<span id="cantidad-label" class="property-label"><g:message code="loteEntrada.cantidad.label" default="Cantidad" /></span>
					
						<span class="property-value" aria-labelledby="cantidad-label"><g:fieldValue bean="${loteEntrada}" field="cantidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.precioUnitario}">
				<li class="fieldcontain">
					<span id="precioUnitario-label" class="property-label"><g:message code="loteEntrada.precioUnitario.label" default="Precio Unitario" /></span>
					
						<span class="property-value" aria-labelledby="precioUnitario-label"><g:fieldValue bean="${loteEntrada}" field="precioUnitario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.iva}">
				<li class="fieldcontain">
					<span id="iva-label" class="property-label"><g:message code="loteEntrada.iva.label" default="Iva" /></span>
					
						<span class="property-value" aria-labelledby="iva-label"><g:fieldValue bean="${loteEntrada}" field="iva"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="loteEntrada.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${loteEntrada?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.entrada}">
				<li class="fieldcontain">
					<span id="entrada-label" class="property-label"><g:message code="loteEntrada.entrada.label" default="Entrada" /></span>
					
						<span class="property-value" aria-labelledby="entrada-label"><g:link controller="entrada" action="show" id="${loteEntrada?.entrada?.id}">${loteEntrada?.entrada?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="loteEntrada.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${loteEntrada?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${loteEntrada?.producto}">
				<li class="fieldcontain">
					<span id="producto-label" class="property-label"><g:message code="loteEntrada.producto.label" default="Producto" /></span>
					
						<span class="property-value" aria-labelledby="producto-label"><g:link controller="producto" action="show" id="${loteEntrada?.producto?.id}">${loteEntrada?.producto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${loteEntrada?.id}" />
					<g:link class="edit" action="edita" id="${loteEntrada?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
