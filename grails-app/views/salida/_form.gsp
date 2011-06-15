<%@ page import="inventario.Salida" %>



<div class="fieldcontain ${hasErrors(bean: salida, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="salida.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="folio" maxlength="64" required="" value="${salida?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'reporte', 'error')} ">
	<label for="reporte">
		<g:message code="salida.reporte.label" default="Reporte" />
		
	</label>
	<g:textField name="reporte" maxlength="64" value="${salida?.reporte}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="salida.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: salida, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="salida.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" required="" value="${fieldValue(bean: salida, field: 'total')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'empleado', 'error')} ">
	<label for="empleado">
		<g:message code="salida.empleado.label" default="Empleado" />
		
	</label>
	<g:textField name="empleado" maxlength="64" value="${salida?.empleado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'departamento', 'error')} ">
	<label for="departamento">
		<g:message code="salida.departamento.label" default="Departamento" />
		
	</label>
	<g:textField name="departamento" maxlength="64" value="${salida?.departamento}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="salida.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textArea name="comentarios" cols="40" rows="5" maxlength="254" value="${salida?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'estatus', 'error')} ">
	<label for="estatus">
		<g:message code="salida.estatus.label" default="Estatus" />
		
	</label>
	<g:select name="estatus" from="${salida.constraints.estatus.inList}" value="${salida?.estatus}" valueMessagePrefix="salida.estatus" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'facturaAlmacen', 'error')} ">
	<label for="facturaAlmacen">
		<g:message code="salida.facturaAlmacen.label" default="Factura Almacen" />
		
	</label>
	<g:select id="facturaAlmacen" name="facturaAlmacen.id" from="${inventario.FacturaAlmacen.list()}" optionKey="id" value="${salida?.facturaAlmacen?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="salida.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${salida?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="salida.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${general.Cliente.list()}" optionKey="id" required="" value="${salida?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salida, field: 'lotes', 'error')} ">
	<label for="lotes">
		<g:message code="salida.lotes.label" default="Lotes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${salida?.lotes?}" var="l">
    <li><g:link controller="loteSalida" action="ver" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="loteSalida" action="nueva" params="['salida.id': salida?.id]">${message(code: 'default.add.label', args: [message(code: 'loteSalida.label', default: 'LoteSalida')])}</g:link>
</li>
</ul>

</div>

