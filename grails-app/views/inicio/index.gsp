<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenido a SUMA</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
            <div class="nav" role="navigation">
                <ul>
                    <li><g:link class="list" controller="contabilidad" ><g:message code="contabilidad.label" default="Contabilidad" /></g:link></li>
                    <li><g:link class="list" controller="inventario" ><g:message code="inventario.label" default="Inventario" /></g:link></li>
                    <li><g:link class="list" controller="admin" ><g:message code="admin.label" default="Admin" /></g:link></li>
                </ul>
            </div>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <div class="content" style="padding:10px 25px;">
                <h1 style="padding:0;margin:0;">Bienvenido a SUMA</h1>
                <p>Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display whatever
                content you may choose. Below is a list of controllers that are currently deployed in this application,
                click on each to execute its default action:</p>

                <div id="controller-list" role="navigation">
                    <h2>Available Controllers:</h2>
                    <ul style="padding:0 25px;">
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                        </g:each>
                    </ul>
                </div>
            </div>
		</div>
	</body>
</html>
