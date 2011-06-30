<%@ page import="inventario.CancelacionAlmacen" %>


<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="cancelacionAlmacen.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" maxlength="200" value="${cancelacionAlmacen?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'entrada', 'error')} ">
	<label for="entrada">
		<g:message code="cancelacionAlmacen.entrada.label" default="Entrada" />
		
	</label>
	<g:select id="entrada" name="entrada.id" from="${inventario.Entrada.list()}" optionKey="id" value="${cancelacionAlmacen?.entrada?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'salida', 'error')} ">
	<label for="salida">
		<g:message code="cancelacionAlmacen.salida.label" default="Salida" />
		
	</label>
	<g:select id="salida" name="salida.id" from="${inventario.Salida.list()}" optionKey="id" value="${cancelacionAlmacen?.salida?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="cancelacionAlmacen.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${cancelacionAlmacen?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'entradas', 'error')} ">
	<label for="entradas">
		<g:message code="cancelacionAlmacen.entradas.label" default="Entradas" />
		
	</label>
	<g:select name="entradas" from="${inventario.Entrada.list()}" multiple="multiple" optionKey="id" size="5" value="${cancelacionAlmacen?.entradas*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cancelacionAlmacen, field: 'salidas', 'error')} ">
	<label for="salidas">
		<g:message code="cancelacionAlmacen.salidas.label" default="Salidas" />
		
	</label>
	<g:select name="salidas" from="${inventario.Salida.list()}" multiple="multiple" optionKey="id" size="5" value="${cancelacionAlmacen?.salidas*.id}" class="many-to-many"/>
</div>

