<%@ page import="inventario.Entrada" %>



<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="entrada.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" maxlength="64" value="${entradaInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'factura', 'error')} required">
	<label for="factura">
		<g:message code="entrada.factura.label" default="Factura" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="factura" maxlength="64" required="" value="${entradaInstance?.factura}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'fechaFactura', 'error')} required">
	<label for="fechaFactura">
		<g:message code="entrada.fechaFactura.label" default="Fecha Factura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFactura" precision="day" value="${entradaInstance?.fechaFactura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="entrada.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" min="0" required="" value="${fieldValue(bean: entradaInstance, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="entrada.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" min="0" required="" value="${fieldValue(bean: entradaInstance, field: 'total')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'tipoCambio', 'error')} ">
	<label for="tipoCambio">
		<g:message code="entrada.tipoCambio.label" default="Tipo Cambio" />
		
	</label>
	<g:field type="number" name="tipoCambio" value="${fieldValue(bean: entradaInstance, field: 'tipoCambio')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'estatus', 'error')} ">
	<label for="estatus">
		<g:message code="entrada.estatus.label" default="Estatus" />
		
	</label>
	<g:select name="estatus" from="${entradaInstance.constraints.estatus.inList}" value="${entradaInstance?.estatus}" valueMessagePrefix="entrada.estatus" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="entrada.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" maxlength="128" value="${entradaInstance?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'facturaAlmacen', 'error')} ">
	<label for="facturaAlmacen">
		<g:message code="entrada.facturaAlmacen.label" default="Factura Almacen" />
		
	</label>
	<g:select id="facturaAlmacen" name="facturaAlmacen.id" from="${inventario.FacturaAlmacen.list()}" optionKey="id" value="${entradaInstance?.facturaAlmacen?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="entrada.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${entradaInstance?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'devolucion', 'error')} ">
	<label for="devolucion">
		<g:message code="entrada.devolucion.label" default="Devolucion" />
		
	</label>
	<g:checkBox name="devolucion" value="${entradaInstance?.devolucion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'lotes', 'error')} ">
	<label for="lotes">
		<g:message code="entrada.lotes.label" default="Lotes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${entradaInstance?.lotes?}" var="l">
    <li><g:link controller="loteEntrada" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="loteEntrada" action="create" params="['entrada.id': entradaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'proveedor', 'error')} required">
	<label for="proveedor">
		<g:message code="entrada.proveedor.label" default="Proveedor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proveedor" name="proveedor.id" from="${general.Proveedor.list()}" optionKey="id" required="" value="${entradaInstance?.proveedor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entradaInstance, field: 'totalFactura', 'error')} required">
	<label for="totalFactura">
		<g:message code="entrada.totalFactura.label" default="Total Factura" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="totalFactura" required="" value="${fieldValue(bean: entradaInstance, field: 'totalFactura')}"/>
</div>

