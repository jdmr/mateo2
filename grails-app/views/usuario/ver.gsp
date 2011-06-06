
<%@ page import="general.Usuario" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-usuario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list usuario">
			
				<g:if test="${usuario?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="usuario.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${usuario}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="usuario.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${usuario}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="usuario.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${usuario}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.correo}">
				<li class="fieldcontain">
					<span id="correo-label" class="property-label"><g:message code="usuario.correo.label" default="Correo" /></span>
					
						<span class="property-value" aria-labelledby="correo-label"><g:fieldValue bean="${usuario}" field="correo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="usuario.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:checkBox name="accountExpired" value="${usuario?.accountExpired}" disabled="true" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="usuario.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:checkBox name="accountLocked" value="${usuario?.accountLocked}" disabled="true" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="usuario.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:checkBox name="enabled" value="${usuario?.enabled}" disabled="true" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuario?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="usuario.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:checkBox name="passwordExpired" value="${usuario?.passwordExpired}" disabled="true" /></span>
					
				</li>
				</g:if>

				<g:if test="${usuario?.empresa}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="usuario.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:fieldValue bean="${usuario}" field="empresa"/></span>
					
				</li>
				</g:if>

                <g:if test="${roles}">
                    <li class="fieldcontain">
                        <span id="authorities-label" class="property-label"><g:message code="usuario.authorities.label" default="Authorities" /></span>
                        
                        <span class="property-value" aria-labelledby="authorities-label">
                            <g:each var="entry" in="${roles}">
                                <g:checkBox disabled="true" name="${entry.key.authority}" value="${entry.value}"/> ${entry.key.authority}<br/>
                            </g:each>
                        </span>
                        
                    </li>
                </g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${usuario?.id}" />
					<g:link class="edit" action="edita" id="${usuario?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
