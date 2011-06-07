<%@ page import="general.Empresa" %>


<div class="fieldcontain ${hasErrors(bean: empresa, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="empresa.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="6" required="" value="${empresa?.codigo}"/>
</div>

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

