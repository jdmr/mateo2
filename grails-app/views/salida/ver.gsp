
<%@ page import="inventario.Salida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salida.label', default: 'Salida')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-salida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-salida" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list salida">
			
				<g:if test="${salida?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="salida.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${salida}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.reporte}">
				<li class="fieldcontain">
					<span id="reporte-label" class="property-label"><g:message code="salida.reporte.label" default="Reporte" /></span>
					
						<span class="property-value" aria-labelledby="reporte-label"><g:fieldValue bean="${salida}" field="reporte"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.iva}">
				<li class="fieldcontain">
					<span id="iva-label" class="property-label"><g:message code="salida.iva.label" default="Iva" /></span>
					
						<span class="property-value" aria-labelledby="iva-label"><g:fieldValue bean="${salida}" field="iva"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="salida.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${salida}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="salida.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:fieldValue bean="${salida}" field="empleado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.departamento}">
				<li class="fieldcontain">
					<span id="departamento-label" class="property-label"><g:message code="salida.departamento.label" default="Departamento" /></span>
					
						<span class="property-value" aria-labelledby="departamento-label"><g:fieldValue bean="${salida}" field="departamento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="salida.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${salida}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="salida.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${salida}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.facturaAlmacen}">
				<li class="fieldcontain">
					<span id="facturaAlmacen-label" class="property-label"><g:message code="salida.facturaAlmacen.label" default="Factura Almacen" /></span>
					
						<span class="property-value" aria-labelledby="facturaAlmacen-label"><g:link controller="facturaAlmacen" action="show" id="${salida?.facturaAlmacen?.id}">${salida?.facturaAlmacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.almacen}">
				<li class="fieldcontain">
					<span id="almacen-label" class="property-label"><g:message code="salida.almacen.label" default="Almacen" /></span>
					
						<span class="property-value" aria-labelledby="almacen-label"><g:link controller="almacen" action="show" id="${salida?.almacen?.id}">${salida?.almacen?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="salida.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="cliente" action="show" id="${salida?.cliente?.id}">${salida?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="salida.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${salida?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="salida.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${salida?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${salida?.lotes}">
				<li class="fieldcontain">
					<span id="lotes-label" class="property-label"><g:message code="salida.lotes.label" default="Lotes" /></span>
					
						<g:each in="${salida.lotes}" var="l">
						<span class="property-value" aria-labelledby="lotes-label"><g:link controller="loteSalida" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${salida?.id}" />
					<g:link class="edit" action="edita" id="${salida?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
