
<%@ page import="contabilidad.Auxiliar" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auxiliar.label', default: 'Auxiliar')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-auxiliar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-auxiliar" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auxiliar">
			
				<g:if test="${auxiliar?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="auxiliar.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${auxiliar}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${auxiliar?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="auxiliar.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${auxiliar}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${auxiliar?.cuentas}">
				<li class="fieldcontain">
					<span id="cuentas-label" class="property-label"><g:message code="auxiliar.cuentas.label" default="Cuentas" /></span>
					
						<g:each in="${auxiliar.cuentas}" var="c">
						<span class="property-value" aria-labelledby="cuentas-label"><g:link controller="cuenta" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${auxiliar?.organizacion}">
				<li class="fieldcontain">
					<span id="organizacion-label" class="property-label"><g:message code="auxiliar.organizacion.label" default="Organizacion" /></span>
					
						<span class="property-value" aria-labelledby="organizacion-label"><g:link controller="organizacion" action="show" id="${auxiliar?.organizacion?.id}">${auxiliar?.organizacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${auxiliar?.id}" />
					<g:link class="edit" action="edita" id="${auxiliar?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
