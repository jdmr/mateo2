<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="usuario.perfil.label" /></title>
	</head>
	<body>
		<a href="#edit-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="edit-empresa" class="content scaffold-edit" role="main">
			<h1><g:message code="usuario.perfil.label" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${usuario}">
			<ul class="errors" role="alert">
				<g:eachError bean="${usuario}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${usuario?.id}" />
				<g:hiddenField name="version" value="${usuario?.version}" />
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: usuario, field: 'empresa', 'error')} required">
                        <label for="username">
                            <g:message code="usuario.empresa.label" default="Empresa" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select name="empresa.id" required="" value="${usuario?.empresa.id}" optionKey="id" optionValue="nombreCanonico" from="${empresas}" />
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: usuario, field: 'password', 'error')} required">
                        <label for="password">
                            <g:message code="usuario.password.label" default="Password" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:passwordField name="password" required="" value="${usuario?.password}"/>
                    </div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualizaPerfil" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
