<%@ page import="inventario.TipoProducto" %>



<div class="fieldcontain ${hasErrors(bean: tipoProducto, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoProducto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${tipoProducto?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoProducto, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoProducto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="128" required="" value="${tipoProducto?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoProducto, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="tipoProducto.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${tipoProducto?.almacen?.id}" class="many-to-one"/>
</div>

