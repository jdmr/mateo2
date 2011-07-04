<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="inventario.label" default="Inventario" /></title>
    </head>
    <body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="salida"><g:message code="salida.list.label" /></g:link></li>
				<li><g:link class="list" controller="entrada"><g:message code="entrada.list.label" /></g:link></li>
				<li><g:link class="list" controller="producto"><g:message code="producto.list.label" /></g:link></li>
				<li><g:link class="list" controller="tipoProducto"><g:message code="tipoProducto.list.label" /></g:link></li>
				<li><g:link class="list" controller="almacen"><g:message code="almacen.list.label" /></g:link></li>
			</ul>
		</div>
        <div class="content">
            <h1><g:message code="inventario.label" default="Inventario" /></h1>
        </div>
    </body>
</html>
