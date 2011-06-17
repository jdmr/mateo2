<%@ page import="contabilidad.Servicio" %>



<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="servicio.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${servicioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="servicio.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="200" required="" value="${servicioInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="servicio.tags.label" default="Tags" />
		
	</label>
	<g:textField name="tags" maxlength="200" value="${servicioInstance?.tags}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'destinos', 'error')} ">
	<label for="destinos">
		<g:message code="servicio.destinos.label" default="Destinos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${servicioInstance?.destinos?}" var="d">
    <li><g:link controller="componente" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="componente" action="create" params="['servicio.id': servicioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'componente.label', default: 'Componente')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="servicio.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${servicioInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'origenes', 'error')} ">
	<label for="origenes">
		<g:message code="servicio.origenes.label" default="Origenes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${servicioInstance?.origenes?}" var="o">
    <li><g:link controller="componente" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="componente" action="create" params="['servicio.id': servicioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'componente.label', default: 'Componente')])}</g:link>
</li>
</ul>

</div>

