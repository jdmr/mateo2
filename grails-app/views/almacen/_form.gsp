<%@ page import="inventario.Almacen" %>



<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="almacen.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="18" required="" value="${almacenInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="almacen.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${almacenInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="almacen.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${almacenInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'entradas', 'error')} ">
	<label for="entradas">
		<g:message code="almacen.entradas.label" default="Entradas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.entradas?}" var="e">
    <li><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="entrada" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'entrada.label', default: 'Entrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'facturas', 'error')} ">
	<label for="facturas">
		<g:message code="almacen.facturas.label" default="Facturas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.facturas?}" var="f">
    <li><g:link controller="facturaAlmacen" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="facturaAlmacen" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'folios', 'error')} ">
	<label for="folios">
		<g:message code="almacen.folios.label" default="Folios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.folios?}" var="f">
    <li><g:link controller="folioInventario" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="folioInventario" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'folioInventario.label', default: 'FolioInventario')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'productos', 'error')} ">
	<label for="productos">
		<g:message code="almacen.productos.label" default="Productos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.productos?}" var="p">
    <li><g:link controller="producto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="producto" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'producto.label', default: 'Producto')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'salidas', 'error')} ">
	<label for="salidas">
		<g:message code="almacen.salidas.label" default="Salidas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.salidas?}" var="s">
    <li><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="salida" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'salida.label', default: 'Salida')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'tiposProducto', 'error')} ">
	<label for="tiposProducto">
		<g:message code="almacen.tiposProducto.label" default="Tipos Producto" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacenInstance?.tiposProducto?}" var="t">
    <li><g:link controller="tipoProducto" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tipoProducto" action="create" params="['almacen.id': almacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tipoProducto.label', default: 'TipoProducto')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacenInstance, field: 'usuarios', 'error')} ">
	<label for="usuarios">
		<g:message code="almacen.usuarios.label" default="Usuarios" />
		
	</label>
	<g:select name="usuarios" from="${general.Usuario.list()}" multiple="multiple" optionKey="id" size="5" value="${almacenInstance?.usuarios*.id}" class="many-to-many"/>
</div>

