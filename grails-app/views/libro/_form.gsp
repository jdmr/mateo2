<%@ page import="contabilidad.Libro" %>



<div class="fieldcontain ${hasErrors(bean: libro, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="libro.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${libro?.nombre}"/>
</div>

