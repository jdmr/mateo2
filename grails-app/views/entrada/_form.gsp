<%@ page import="inventario.Entrada" %>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="entrada.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" min="0" required="" value="${fieldValue(bean: entrada, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'tipoCambio', 'error')} ">
	<label for="tipoCambio">
		<g:message code="entrada.tipoCambio.label" default="Tipo Cambio" />
		
	</label>
	<g:field type="number" name="tipoCambio" value="${fieldValue(bean: entrada, field: 'tipoCambio')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'estatus', 'error')} ">
	<label for="estatus">
		<g:message code="entrada.estatus.label" default="Estatus" />
		
	</label>
	<g:select name="estatus" from="${entrada.constraints.estatus.inList}" value="${entrada?.estatus}" valueMessagePrefix="entrada.estatus" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="entrada.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" maxlength="128" value="${entrada?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'facturaAlmacen', 'error')} ">
	<label for="facturaAlmacen">
		<g:message code="entrada.facturaAlmacen.label" default="Factura Almacen" />
		
	</label>
	<g:select id="facturaAlmacen" name="facturaAlmacen.id" from="${inventario.FacturaAlmacen.list()}" optionKey="id" value="${entrada?.facturaAlmacen?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="entrada.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${entrada?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'devolucion', 'error')} ">
	<label for="devolucion">
		<g:message code="entrada.devolucion.label" default="Devolucion" />
		
	</label>
	<g:checkBox name="devolucion" value="${entrada?.devolucion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'lotes', 'error')} ">
	<label for="lotes">
		<g:message code="entrada.lotes.label" default="Lotes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${entrada?.lotes?}" var="l">
    <li><g:link controller="loteEntrada" action="ver" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="loteEntrada" action="nueva" params="['entrada.id': entrada?.id]">${message(code: 'default.add.label', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: entrada, field: 'proveedor', 'error')} required">
	<label for="proveedor">
		<g:message code="entrada.proveedor.label" default="Proveedor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proveedor" name="proveedor.id" from="${general.Proveedor.list()}" optionKey="id" required="" value="${entrada?.proveedor?.id}" class="many-to-one"/>
</div>