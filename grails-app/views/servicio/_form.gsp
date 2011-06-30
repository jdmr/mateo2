<%@ page import="contabilidad.Servicio" %>

<div class="fieldcontain">
    <h3>
        <g:message code="servicio.tags.label" default="tags" />
    </h3>
    <ul name="tags">
      <g:each in="${servicio?.tags?.tokenize(',')}">
        <li>${it}</li>
      </g:each>
    </ul>
</div>

<div class="fieldcontain ${hasErrors(bean: servicio, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="servicio.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${servicio?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicio, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="servicio.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" maxlength="200" required="" value="${servicio?.descripcion}"/>
</div>

<r:script>
    $(document).ready(function() {
        $("ul[name='tags']").tagit({select:true, tagSource: "${g.createLink(action: 'tags')}"});
        $('#nombre').focus();
    });
</r:script>
