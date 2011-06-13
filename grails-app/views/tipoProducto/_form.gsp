<%@ page import="inventario.TipoProducto" %>



<div class="fieldcontain ${hasErrors(bean: tipoProductoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoProducto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${tipoProductoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoProductoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoProducto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="128" required="" value="${tipoProductoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoProductoInstance, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="tipoProducto.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${tipoProductoInstance?.almacen?.id}" class="many-to-one"/>
</div>

