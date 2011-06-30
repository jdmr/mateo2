
<%@ page import="inventario.Almacen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'almacen.label', default: 'Almacen')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-almacen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-almacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list almacen">
			
				<g:if test="${almacen?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="almacen.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${almacen}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="almacen.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${almacen}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="almacen.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${almacen?.empresa?.id}">${almacen?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.entradas}">
				<li class="fieldcontain">
					<span id="entradas-label" class="property-label"><g:message code="almacen.entradas.label" default="Entradas" /></span>
					
						<g:each in="${almacen.entradas}" var="e">
						<span class="property-value" aria-labelledby="entradas-label"><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.facturas}">
				<li class="fieldcontain">
					<span id="facturas-label" class="property-label"><g:message code="almacen.facturas.label" default="Facturas" /></span>
					
						<g:each in="${almacen.facturas}" var="f">
						<span class="property-value" aria-labelledby="facturas-label"><g:link controller="facturaAlmacen" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.folios}">
				<li class="fieldcontain">
					<span id="folios-label" class="property-label"><g:message code="almacen.folios.label" default="Folios" /></span>
					
						<g:each in="${almacen.folios}" var="f">
						<span class="property-value" aria-labelledby="folios-label"><g:link controller="folioInventario" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.productos}">
				<li class="fieldcontain">
					<span id="productos-label" class="property-label"><g:message code="almacen.productos.label" default="Productos" /></span>
					
						<g:each in="${almacen.productos}" var="p">
						<span class="property-value" aria-labelledby="productos-label"><g:link controller="producto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.salidas}">
				<li class="fieldcontain">
					<span id="salidas-label" class="property-label"><g:message code="almacen.salidas.label" default="Salidas" /></span>
					
						<g:each in="${almacen.salidas}" var="s">
						<span class="property-value" aria-labelledby="salidas-label"><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacen?.tiposProducto}">
				<li class="fieldcontain">
					<span id="tiposProducto-label" class="property-label"><g:message code="almacen.tiposProducto.label" default="Tipos Producto" /></span>
					
						<g:each in="${almacen.tiposProducto}" var="t">
						<span class="property-value" aria-labelledby="tiposProducto-label"><g:link controller="tipoProducto" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${almacen?.id}" />
					<g:link class="edit" action="edita" id="${almacen?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
