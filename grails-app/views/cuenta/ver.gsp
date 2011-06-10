
<%@ page import="contabilidad.Cuenta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuenta.label', default: 'Cuenta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cuenta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cuenta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cuenta">
			
				<g:if test="${cuenta?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="cuenta.numero.label" default="numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${cuenta}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="cuenta.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cuenta}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.padre}">
				<li class="fieldcontain">
					<span id="padre-label" class="property-label"><g:message code="cuenta.padre.label" default="Padre" /></span>
					
						<span class="property-value" aria-labelledby="padre-label"><g:link controller="cuenta" action="show" id="${cuenta?.padre?.id}">${cuenta?.padre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.auxiliares}">
				<li class="fieldcontain">
					<span id="auxiliares-label" class="property-label"><g:message code="cuenta.auxiliares.label" default="Auxiliares" /></span>
					
						<g:each in="${cuenta.auxiliares}" var="a">
						<span class="property-value" aria-labelledby="auxiliares-label"><g:link controller="auxiliar" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="cuenta.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${cuenta?.empresa?.id}">${cuenta?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.hijos}">
				<li class="fieldcontain">
					<span id="hijos-label" class="property-label"><g:message code="cuenta.hijos.label" default="Hijos" /></span>
					
						<g:each in="${cuenta.hijos}" var="h">
						<span class="property-value" aria-labelledby="hijos-label"><g:link controller="cuenta" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.tieneAuxiliares}">
				<li class="fieldcontain">
					<span id="tieneAuxiliares-label" class="property-label"><g:message code="cuenta.tieneAuxiliares.label" default="Tiene Auxiliares" /></span>
					
						<span class="property-value" aria-labelledby="tieneAuxiliares-label"><g:formatBoolean boolean="${cuenta?.tieneAuxiliares}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuenta?.tieneMovimientos}">
				<li class="fieldcontain">
					<span id="tieneMovimientos-label" class="property-label"><g:message code="cuenta.tieneMovimientos.label" default="Tiene Movimientos" /></span>
					
						<span class="property-value" aria-labelledby="tieneMovimientos-label"><g:formatBoolean boolean="${cuenta?.tieneMovimientos}" /></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
