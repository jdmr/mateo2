<%@ page import="inventario.Producto" %>



<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="producto.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="6" required="" value="${productoInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="producto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="128" required="" value="${productoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="254" value="${productoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="producto.marca.label" default="Marca" />
		
	</label>
	<g:textField name="marca" maxlength="32" value="${productoInstance?.marca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'modelo', 'error')} ">
	<label for="modelo">
		<g:message code="producto.modelo.label" default="Modelo" />
		
	</label>
	<g:textField name="modelo" maxlength="32" value="${productoInstance?.modelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precioUnitario', 'error')} required">
	<label for="precioUnitario">
		<g:message code="producto.precioUnitario.label" default="Precio Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="precioUnitario" min="0" required="" value="${fieldValue(bean: productoInstance, field: 'precioUnitario')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'existencia', 'error')} required">
	<label for="existencia">
		<g:message code="producto.existencia.label" default="Existencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="existencia" min="0" required="" value="${fieldValue(bean: productoInstance, field: 'existencia')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'unidadMedida', 'error')} ">
	<label for="unidadMedida">
		<g:message code="producto.unidadMedida.label" default="Unidad Medida" />
		
	</label>
	<g:textField name="unidadMedida" maxlength="16" value="${productoInstance?.unidadMedida}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="producto.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: productoInstance, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'almacen', 'error')} required">
	<label for="almacen">
		<g:message code="producto.almacen.label" default="Almacen" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="almacen" name="almacen.id" from="${inventario.Almacen.list()}" optionKey="id" required="" value="${productoInstance?.almacen?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'entradaId', 'error')} required">
	<label for="entradaId">
		<g:message code="producto.entradaId.label" default="Entrada Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="entradaId" required="" value="${fieldValue(bean: productoInstance, field: 'entradaId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'fraccion', 'error')} ">
	<label for="fraccion">
		<g:message code="producto.fraccion.label" default="Fraccion" />
		
	</label>
	<g:checkBox name="fraccion" value="${productoInstance?.fraccion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'imagenes', 'error')} ">
	<label for="imagenes">
		<g:message code="producto.imagenes.label" default="Imagenes" />
		
	</label>
	<g:select name="imagenes" from="${general.Imagen.list()}" multiple="multiple" optionKey="id" size="5" value="${productoInstance?.imagenes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'lotesEntrada', 'error')} ">
	<label for="lotesEntrada">
		<g:message code="producto.lotesEntrada.label" default="Lotes Entrada" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${productoInstance?.lotesEntrada?}" var="l">
    <li><g:link controller="loteEntrada" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="loteEntrada" action="create" params="['producto.id': productoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'lotesSalida', 'error')} ">
	<label for="lotesSalida">
		<g:message code="producto.lotesSalida.label" default="Lotes Salida" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${productoInstance?.lotesSalida?}" var="l">
    <li><g:link controller="loteSalida" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="loteSalida" action="create" params="['producto.id': productoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'loteSalida.label', default: 'LoteSalida')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'salidaId', 'error')} required">
	<label for="salidaId">
		<g:message code="producto.salidaId.label" default="Salida Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="salidaId" required="" value="${fieldValue(bean: productoInstance, field: 'salidaId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tiempoEntrega', 'error')} required">
	<label for="tiempoEntrega">
		<g:message code="producto.tiempoEntrega.label" default="Tiempo Entrega" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="tiempoEntrega" required="" value="${fieldValue(bean: productoInstance, field: 'tiempoEntrega')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tipoProducto', 'error')} required">
	<label for="tipoProducto">
		<g:message code="producto.tipoProducto.label" default="Tipo Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoProducto" name="tipoProducto.id" from="${inventario.TipoProducto.list()}" optionKey="id" required="" value="${productoInstance?.tipoProducto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'ultimoPrecio', 'error')} required">
	<label for="ultimoPrecio">
		<g:message code="producto.ultimoPrecio.label" default="Ultimo Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="ultimoPrecio" required="" value="${fieldValue(bean: productoInstance, field: 'ultimoPrecio')}"/>
</div>

