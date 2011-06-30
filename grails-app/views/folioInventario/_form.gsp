<%@ page import="inventario.FolioInventario" %>



<div class="fieldcontain ${hasErrors(bean: folioInventario, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="folioInventario.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="32" value="${folioInventario?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: folioInventario, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="folioInventario.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="valor" required="" value="${fieldValue(bean: folioInventario, field: 'valor')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: folioInventario, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="folioInventario.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${folioInventario?.almacen?.id}" class="many-to-one"/>
</div>

