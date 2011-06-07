<%@ page import="contabilidad.Ejercicio" %>



<div class="fieldcontain ${hasErrors(bean: ejercicio, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="ejercicio.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${ejercicio?.nombre}"/>
</div>

