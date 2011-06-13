<%@ page import="inventario.FacturaAlmacen" %>



<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="facturaAlmacen.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" maxlength="64" value="${facturaAlmacenInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="facturaAlmacen.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" maxlength="128" value="${facturaAlmacenInstance?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="facturaAlmacen.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: facturaAlmacenInstance, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="facturaAlmacen.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" required="" value="${fieldValue(bean: facturaAlmacenInstance, field: 'total')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'estatus', 'error')} ">
	<label for="estatus">
		<g:message code="facturaAlmacen.estatus.label" default="Estatus" />
		
	</label>
	<g:select name="estatus" from="${facturaAlmacenInstance.constraints.estatus.inList}" value="${facturaAlmacenInstance?.estatus}" valueMessagePrefix="facturaAlmacen.estatus" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="facturaAlmacen.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${facturaAlmacenInstance?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="facturaAlmacen.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${general.Cliente.list()}" optionKey="id" required="" value="${facturaAlmacenInstance?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'entradas', 'error')} ">
	<label for="entradas">
		<g:message code="facturaAlmacen.entradas.label" default="Entradas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${facturaAlmacenInstance?.entradas?}" var="e">
    <li><g:link controller="entrada" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="entrada" action="create" params="['facturaAlmacen.id': facturaAlmacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'entrada.label', default: 'Entrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="facturaAlmacen.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day" value="${facturaAlmacenInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: facturaAlmacenInstance, field: 'salidas', 'error')} ">
	<label for="salidas">
		<g:message code="facturaAlmacen.salidas.label" default="Salidas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${facturaAlmacenInstance?.salidas?}" var="s">
    <li><g:link controller="salida" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="salida" action="create" params="['facturaAlmacen.id': facturaAlmacenInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'salida.label', default: 'Salida')])}</g:link>
</li>
</ul>

</div>

