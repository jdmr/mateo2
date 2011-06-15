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

