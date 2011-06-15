<%@ page import="inventario.Almacen" %>



<div class="fieldcontain ${hasErrors(bean: almacen, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="almacen.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="18" required="" value="${almacen?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="almacen.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${almacen?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="almacen.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${almacen?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'entradas', 'error')} ">
	<label for="entradas">
		<g:message code="almacen.entradas.label" default="Entradas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.entradas?}" var="e">
    <li><g:link controller="entrada" action="ver" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="entrada" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'entrada.label', default: 'Entrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'facturas', 'error')} ">
	<label for="facturas">
		<g:message code="almacen.facturas.label" default="Facturas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.facturas?}" var="f">
    <li><g:link controller="facturaAlmacen" action="ver" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="facturaAlmacen" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'folios', 'error')} ">
	<label for="folios">
		<g:message code="almacen.folios.label" default="Folios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.folios?}" var="f">
    <li><g:link controller="folioInventario" action="ver" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="folioInventario" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'folioInventario.label', default: 'FolioInventario')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'productos', 'error')} ">
	<label for="productos">
		<g:message code="almacen.productos.label" default="Productos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.productos?}" var="p">
    <li><g:link controller="producto" action="ver" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="producto" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'producto.label', default: 'Producto')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'salidas', 'error')} ">
	<label for="salidas">
		<g:message code="almacen.salidas.label" default="Salidas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.salidas?}" var="s">
    <li><g:link controller="salida" action="ver" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="salida" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'salida.label', default: 'Salida')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'tiposProducto', 'error')} ">
	<label for="tiposProducto">
		<g:message code="almacen.tiposProducto.label" default="Tipos Producto" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${almacen?.tiposProducto?}" var="t">
    <li><g:link controller="tipoProducto" action="ver" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tipoProducto" action="nuevo" params="['almacen.id': almacen?.id]">${message(code: 'default.add.label', args: [message(code: 'tipoProducto.label', default: 'TipoProducto')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: almacen, field: 'usuarios', 'error')} ">
	<label for="usuarios">
		<g:message code="almacen.usuarios.label" default="Usuarios" />
		
	</label>
	<g:select name="usuarios" from="${general.Usuario.list()}" multiple="multiple" optionKey="id" size="5" value="${almacen?.usuarios*.id}" class="many-to-many"/>
</div>

