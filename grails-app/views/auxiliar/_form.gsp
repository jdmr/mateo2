<%@ page import="contabilidad.Auxiliar" %>



<div class="fieldcontain ${hasErrors(bean: auxiliar, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="auxiliar.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="32" required="" value="${auxiliar?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auxiliar, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="auxiliar.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="128" required="" value="${auxiliar?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auxiliar, field: 'tags', 'error')} required">
	<h3>
		<g:message code="auxiliar.tags.label" default="tags" />
	</h3>
    <ul name="tags">
      <g:each in="${auxiliar?.cuentas}">
        <li>${it.descripcion}</li>
      </g:each>
    </ul>
</div>

<r:script>
    $(document).ready(function() {
        $("ul[name='tags']").tagit({select:true, tagSource: "${g.createLink(action: 'tags')}"});
        $('#numero').focus();
    });
</r:script>
