<%@ page import="inventario.Producto" %>



<div class="fieldcontain ${hasErrors(bean: producto, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="producto.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="6" required="" value="${producto?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'sku', 'error')} required">
	<label for="sku">
		<g:message code="producto.sku.label" default="Sku" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sku" maxlength="6" required="" value="${producto?.sku}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="producto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="128" required="" value="${producto?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="254" value="${producto?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="producto.marca.label" default="Marca" />
		
	</label>
	<g:textField name="marca" maxlength="32" value="${producto?.marca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'modelo', 'error')} ">
	<label for="modelo">
		<g:message code="producto.modelo.label" default="Modelo" />
		
	</label>
	<g:textField name="modelo" maxlength="32" value="${producto?.modelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'unidadMedida', 'error')} ">
	<label for="unidadMedida">
		<g:message code="producto.unidadMedida.label" default="Unidad Medida" />
		
	</label>
	<g:textField name="unidadMedida" maxlength="16" value="${producto?.unidadMedida}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'fraccion', 'error')} ">
	<label for="fraccion">
		<g:message code="producto.fraccion.label" default="Fraccion" />

	</label>
	<g:checkBox name="fraccion" value="${producto?.fraccion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'ubicacion', 'error')} ">
	<label for="ubicacion">
		<g:message code="producto.ubicacion.label" default="Ubicacion" />

	</label>
	<g:textField name="ubicacion" maxlength="32" value="${producto?.ubicacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producto, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="producto.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: producto, field: 'iva')}"/>
</div>
