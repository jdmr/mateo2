
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
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-almacen" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list almacen">
			
				<g:if test="${almacenInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="almacen.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${almacenInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="almacen.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${almacenInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="almacen.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${almacenInstance?.empresa?.id}">${almacenInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.entradas}">
				<li class="fieldcontain">
					<span id="entradas-label" class="property-label"><g:message code="almacen.entradas.label" default="Entradas" /></span>
					
						<g:each in="${almacenInstance.entradas}" var="e">
						<span class="property-value" aria-labelledby="entradas-label"><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.facturas}">
				<li class="fieldcontain">
					<span id="facturas-label" class="property-label"><g:message code="almacen.facturas.label" default="Facturas" /></span>
					
						<g:each in="${almacenInstance.facturas}" var="f">
						<span class="property-value" aria-labelledby="facturas-label"><g:link controller="facturaAlmacen" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.folios}">
				<li class="fieldcontain">
					<span id="folios-label" class="property-label"><g:message code="almacen.folios.label" default="Folios" /></span>
					
						<g:each in="${almacenInstance.folios}" var="f">
						<span class="property-value" aria-labelledby="folios-label"><g:link controller="folioInventario" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.productos}">
				<li class="fieldcontain">
					<span id="productos-label" class="property-label"><g:message code="almacen.productos.label" default="Productos" /></span>
					
						<g:each in="${almacenInstance.productos}" var="p">
						<span class="property-value" aria-labelledby="productos-label"><g:link controller="producto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.salidas}">
				<li class="fieldcontain">
					<span id="salidas-label" class="property-label"><g:message code="almacen.salidas.label" default="Salidas" /></span>
					
						<g:each in="${almacenInstance.salidas}" var="s">
						<span class="property-value" aria-labelledby="salidas-label"><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.tiposProducto}">
				<li class="fieldcontain">
					<span id="tiposProducto-label" class="property-label"><g:message code="almacen.tiposProducto.label" default="Tipos Producto" /></span>
					
						<g:each in="${almacenInstance.tiposProducto}" var="t">
						<span class="property-value" aria-labelledby="tiposProducto-label"><g:link controller="tipoProducto" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${almacenInstance?.usuarios}">
				<li class="fieldcontain">
					<span id="usuarios-label" class="property-label"><g:message code="almacen.usuarios.label" default="Usuarios" /></span>
					
						<g:each in="${almacenInstance.usuarios}" var="u">
						<span class="property-value" aria-labelledby="usuarios-label"><g:link controller="usuario" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${almacenInstance?.id}" />
					<g:link class="edit" action="edit" id="${almacenInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
