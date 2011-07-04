<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="contabilidad.label" default="Contabilidad" /></title>
    </head>
    <body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="poliza"><g:message code="poliza.list.label" /></g:link></li>
				<li><g:link class="list" controller="auxiliar"><g:message code="auxiliar.list.label" /></g:link></li>
				<li><g:link class="list" controller="cuenta"><g:message code="cuenta.list.label" /></g:link></li>
				<li><g:link class="list" controller="libro"><g:message code="libro.list.label" /></g:link></li>
				<li><g:link class="list" controller="ejercicio"><g:message code="ejercicio.list.label" /></g:link></li>
			</ul>
		</div>
        <div class="content">
            <h1><g:message code="contabilidad.label" default="Contabilidad" /></h1>
        </div>
    </body>
</html>
