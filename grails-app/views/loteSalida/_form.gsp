<%@ page import="inventario.LoteSalida" %>



<div class="fieldcontain ${hasErrors(bean: loteSalidaInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="loteSalida.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cantidad" required="" value="${fieldValue(bean: loteSalidaInstance, field: 'cantidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteSalidaInstance, field: 'precioUnitario', 'error')} required">
	<label for="precioUnitario">
		<g:message code="loteSalida.precioUnitario.label" default="Precio Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="precioUnitario" required="" value="${fieldValue(bean: loteSalidaInstance, field: 'precioUnitario')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteSalidaInstance, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="loteSalida.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: loteSalidaInstance, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteSalidaInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="loteSalida.producto.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="producto.id" from="${inventario.Producto.list()}" optionKey="id" required="" value="${loteSalidaInstance?.producto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteSalidaInstance, field: 'salida', 'error')} required">
	<label for="salida">
		<g:message code="loteSalida.salida.label" default="Salida" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="salida" name="salida.id" from="${inventario.Salida.list()}" optionKey="id" required="" value="${loteSalidaInstance?.salida?.id}" class="many-to-one"/>
</div>

