<%@ page import="contabilidad.Cuenta" %>



<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="cuenta.numero.label" default="numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="32" required="" value="${cuenta?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cuenta.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="128" required="" value="${cuenta?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'padre', 'error')} ">
	<label for="padre">
		<g:message code="cuenta.padre.label" default="Padre" />
		
	</label>
	<g:select id="padre" name="padre.id" from="${contabilidad.Cuenta.list()}" optionKey="id" value="${cuenta?.padre?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'auxiliares', 'error')} ">
	<label for="auxiliares">
		<g:message code="cuenta.auxiliares.label" default="Auxiliares" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuenta?.auxiliares?}" var="a">
    <li><g:link controller="auxiliar" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="auxiliar" action="create" params="['cuenta.id': cuenta?.id]">${message(code: 'default.add.label', args: [message(code: 'auxiliar.label', default: 'Auxiliar')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="cuenta.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${cuenta?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'hijos', 'error')} ">
	<label for="hijos">
		<g:message code="cuenta.hijos.label" default="Hijos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuenta?.hijos?}" var="h">
    <li><g:link controller="cuenta" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="cuenta" action="create" params="['cuenta.id': cuenta?.id]">${message(code: 'default.add.label', args: [message(code: 'cuenta.label', default: 'Cuenta')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'tieneAuxiliares', 'error')} ">
	<label for="tieneAuxiliares">
		<g:message code="cuenta.tieneAuxiliares.label" default="Tiene Auxiliares" />
		
	</label>
	<g:checkBox name="tieneAuxiliares" value="${cuenta?.tieneAuxiliares}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cuenta, field: 'tieneMovimientos', 'error')} ">
	<label for="tieneMovimientos">
		<g:message code="cuenta.tieneMovimientos.label" default="Tiene Movimientos" />
		
	</label>
	<g:checkBox name="tieneMovimientos" value="${cuenta?.tieneMovimientos}" />
</div>

