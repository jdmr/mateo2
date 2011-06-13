
<%@ page import="inventario.Entrada" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entrada.label', default: 'Entrada')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-entrada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-entrada" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list entrada">
			
				<g:if test="${entradaInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="entrada.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${entradaInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.factura}">
				<li class="fieldcontain">
					<span id="factura-label" class="property-label"><g:message code="entrada.factura.label" default="Factura" /></span>
					
						<span class="property-value" aria-labelledby="factura-label"><g:fieldValue bean="${entradaInstance}" field="factura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.fechaFactura}">
				<li class="fieldcontain">
					<span id="fechaFactura-label" class="property-label"><g:message code="entrada.fechaFactura.label" default="Fecha Factura" /></span>
					
						<span class="property-value" aria-labelledby="fechaFactura-label"><g:formatDate date="${entradaInstance?.fechaFactura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.iva}">
				<li class="fieldcontain">
					<span id="iva-label" class="property-label"><g:message code="entrada.iva.label" default="Iva" /></span>
					
						<span class="property-value" aria-labelledby="iva-label"><g:fieldValue bean="${entradaInstance}" field="iva"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="entrada.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${entradaInstance}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.tipoCambio}">
				<li class="fieldcontain">
					<span id="tipoCambio-label" class="property-label"><g:message code="entrada.tipoCambio.label" default="Tipo Cambio" /></span>
					
						<span class="property-value" aria-labelledby="tipoCambio-label"><g:fieldValue bean="${entradaInstance}" field="tipoCambio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="entrada.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${entradaInstance}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="entrada.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${entradaInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.facturaAlmacen}">
				<li class="fieldcontain">
					<span id="facturaAlmacen-label" class="property-label"><g:message code="entrada.facturaAlmacen.label" default="Factura Almacen" /></span>
					
						<span class="property-value" aria-labelledby="facturaAlmacen-label"><g:link controller="facturaAlmacen" action="show" id="${entradaInstance?.facturaAlmacen?.id}">${entradaInstance?.facturaAlmacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.almacen}">
				<li class="fieldcontain">
					<span id="almacen-label" class="property-label"><g:message code="entrada.almacen.label" default="Almacen" /></span>
					
						<span class="property-value" aria-labelledby="almacen-label"><g:link controller="almacen" action="show" id="${entradaInstance?.almacen?.id}">${entradaInstance?.almacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="entrada.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${entradaInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.devolucion}">
				<li class="fieldcontain">
					<span id="devolucion-label" class="property-label"><g:message code="entrada.devolucion.label" default="Devolucion" /></span>
					
						<span class="property-value" aria-labelledby="devolucion-label"><g:formatBoolean boolean="${entradaInstance?.devolucion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="entrada.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${entradaInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.lotes}">
				<li class="fieldcontain">
					<span id="lotes-label" class="property-label"><g:message code="entrada.lotes.label" default="Lotes" /></span>
					
						<g:each in="${entradaInstance.lotes}" var="l">
						<span class="property-value" aria-labelledby="lotes-label"><g:link controller="loteEntrada" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.proveedor}">
				<li class="fieldcontain">
					<span id="proveedor-label" class="property-label"><g:message code="entrada.proveedor.label" default="Proveedor" /></span>
					
						<span class="property-value" aria-labelledby="proveedor-label"><g:link controller="proveedor" action="show" id="${entradaInstance?.proveedor?.id}">${entradaInstance?.proveedor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${entradaInstance?.totalFactura}">
				<li class="fieldcontain">
					<span id="totalFactura-label" class="property-label"><g:message code="entrada.totalFactura.label" default="Total Factura" /></span>
					
						<span class="property-value" aria-labelledby="totalFactura-label"><g:fieldValue bean="${entradaInstance}" field="totalFactura"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${entradaInstance?.id}" />
					<g:link class="edit" action="edit" id="${entradaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
