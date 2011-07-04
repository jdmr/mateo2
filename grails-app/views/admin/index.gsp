<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="admin.label" default="Admin" /></title>
    </head>
    <body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="cliente"><g:message code="cliente.list.label" /></g:link></li>
				<li><g:link class="list" controller="tipoCliente"><g:message code="tipoCliente.list.label" /></g:link></li>
				<li><g:link class="list" controller="proveedor"><g:message code="proveedor.list.label" /></g:link></li>
				<li><g:link class="list" controller="empresa"><g:message code="empresa.list.label" /></g:link></li>
				<li><g:link class="list" controller="organizacion"><g:message code="organizacion.list.label" /></g:link></li>
			</ul>
		</div>
        <div class="content">
            <h1><g:message code="admin.label" default="Admin" /></h1>
        </div>
    </body>
</html>
