<%@ page import="general.Empresa" %>


<g:hiddenField name="organizacion.id" value="${empresa?.organizacion?.id}" />
<div class="fieldcontain ${hasErrors(bean: empresa, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="empresa.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="32" required="" value="${empresa?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresa, field: 'nombreCompleto', 'error')} required">
	<label for="nombreCompleto">
		<g:message code="empresa.nombreCompleto.label" default="Nombre Completo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreCompleto" maxlength="128" required="" value="${empresa?.nombreCompleto}"/>
</div>

