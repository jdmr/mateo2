<%@ page import="inventario.LoteEntrada" %>



<div class="fieldcontain ${hasErrors(bean: loteEntrada, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="loteEntrada.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cantidad" required="" value="${fieldValue(bean: loteEntrada, field: 'cantidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteEntrada, field: 'precioUnitario', 'error')} required">
	<label for="precioUnitario">
		<g:message code="loteEntrada.precioUnitario.label" default="Precio Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="precioUnitario" min="0" required="" value="${fieldValue(bean: loteEntrada, field: 'precioUnitario')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteEntrada, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="loteEntrada.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: loteEntrada, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteEntrada, field: 'entrada', 'error')} required">
	<label for="entrada">
		<g:message code="loteEntrada.entrada.label" default="Entrada" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="entrada" name="entrada.id" from="${inventario.Entrada.list()}" optionKey="id" required="" value="${loteEntrada?.entrada?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loteEntrada, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="loteEntrada.producto.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="producto.id" from="${inventario.Producto.list()}" optionKey="id" required="" value="${loteEntrada?.producto?.id}" class="many-to-one"/>
</div>

