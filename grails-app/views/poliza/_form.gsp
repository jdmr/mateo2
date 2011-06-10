<%@ page import="contabilidad.Poliza" %>

<div class="fieldcontain ${hasErrors(bean: poliza, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="poliza.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" maxlength="200" required="" value="${poliza?.descripcion}"/>
</div>

<g:if test="${!poliza.tipo}">
    <div class="fieldcontain ${hasErrors(bean: poliza, field: 'tipo', 'error')} ">
        <label for="tipo">
            <g:message code="poliza.tipo.label" default="Tipo" />
            
        </label>
        <g:select name="tipo" from="${poliza.constraints.tipo.inList}" value="${poliza?.tipo}" valueMessagePrefix="poliza.tipo" noSelection="['': '']"/>
    </div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: poliza, field: 'libro', 'error')} required">
	<label for="libro">
		<g:message code="poliza.libro.label" default="Libro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="libro" name="libro.id" from="${contabilidad.Libro.list()}" optionKey="id" required="" value="${poliza?.libro?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: poliza, field: 'ejercicio', 'error')} required">
	<label for="ejercicio">
		<g:message code="poliza.ejercicio.label" default="Ejercicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ejercicio" name="ejercicio.id" from="${contabilidad.Ejercicio.list()}" optionKey="id" required="" value="${poliza?.ejercicio?.id}" class="many-to-one"/>
</div>

