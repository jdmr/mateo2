<%@ page import="inventario.FacturaAlmacen" %>


<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="facturaAlmacen.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" maxlength="128" value="${facturaAlmacen?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="facturaAlmacen.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: facturaAlmacen, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'estatus', 'error')} ">
	<label for="estatus">
		<g:message code="facturaAlmacen.estatus.label" default="Estatus" />
		
	</label>
	<g:select name="estatus" from="${facturaAlmacen.constraints.estatus.inList}" value="${facturaAlmacen?.estatus}" valueMessagePrefix="facturaAlmacen.estatus" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="facturaAlmacen.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${facturaAlmacen?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="facturaAlmacen.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${general.Cliente.list()}" optionKey="id" required="" value="${facturaAlmacen?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'entradas', 'error')} ">
	<label for="entradas">
		<g:message code="facturaAlmacen.entradas.label" default="Entradas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${facturaAlmacen?.entradas?}" var="e">
    <li><g:link controller="entrada" action="ver" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="entrada" action="nueva" params="['facturaAlmacen.id': facturaAlmacen?.id]">${message(code: 'default.add.label', args: [message(code: 'entrada.label', default: 'Entrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacen, field: 'salidas', 'error')} ">
	<label for="salidas">
		<g:message code="facturaAlmacen.salidas.label" default="Salidas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${facturaAlmacen?.salidas?}" var="s">
    <li><g:link controller="salida" action="ver" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="salida" action="nueva" params="['facturaAlmacen.id': facturaAlmacen?.id]">${message(code: 'default.add.label', args: [message(code: 'salida.label', default: 'Salida')])}</g:link>
</li>
</ul>

</div>

