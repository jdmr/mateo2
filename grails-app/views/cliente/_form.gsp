<%@ page import="general.Cliente" %>



<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="cliente.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${clienteInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'nombreCompleto', 'error')} required">
	<label for="nombreCompleto">
		<g:message code="cliente.nombreCompleto.label" default="Nombre Completo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreCompleto" maxlength="128" required="" value="${clienteInstance?.nombreCompleto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="cliente.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="13" required="" value="${clienteInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'curp', 'error')} ">
	<label for="curp">
		<g:message code="cliente.curp.label" default="Curp" />
		
	</label>
	<g:textField name="curp" value="${clienteInstance?.curp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion', 'error')} ">
	<label for="direccion">
		<g:message code="cliente.direccion.label" default="Direccion" />
		
	</label>
	<g:textArea name="direccion" cols="40" rows="5" maxlength="500" value="${clienteInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="cliente.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" maxlength="25" value="${clienteInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'fax', 'error')} ">
	<label for="fax">
		<g:message code="cliente.fax.label" default="Fax" />
		
	</label>
	<g:textField name="fax" maxlength="25" value="${clienteInstance?.fax}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'contacto', 'error')} ">
	<label for="contacto">
		<g:message code="cliente.contacto.label" default="Contacto" />
		
	</label>
	<g:textField name="contacto" maxlength="64" value="${clienteInstance?.contacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'correo', 'error')} ">
	<label for="correo">
		<g:message code="cliente.correo.label" default="Correo" />
		
	</label>
	<g:field type="email" name="correo" maxlength="128" value="${clienteInstance?.correo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'base', 'error')} ">
	<label for="base">
		<g:message code="cliente.base.label" default="Base" />
		
	</label>
	<g:checkBox name="base" value="${clienteInstance?.base}" />
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="cliente.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${clienteInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'tipoCliente', 'error')} required">
	<label for="tipoCliente">
		<g:message code="cliente.tipoCliente.label" default="Tipo Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoCliente" name="tipoCliente.id" from="${general.TipoCliente.list()}" optionKey="id" required="" value="${clienteInstance?.tipoCliente?.id}" class="many-to-one"/>
</div>

