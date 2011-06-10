<%@ page import="contabilidad.Poliza" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
		<title><g:message code="poliza.nueva.ingreso.label" /></title>
        <r:require module="jquery" />
	</head>
	<body>
		<a href="#create-poliza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-poliza" class="content scaffold-create" role="main">
			<h1><g:message code="poliza.nueva.ingreso.label" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${poliza}">
			<ul class="errors" role="alert">
				<g:eachError bean="${poliza}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="crea" >
                <g:if test="${poliza.tipo}">
                    <g:hiddenField name="tipo" value="${poliza.tipo}" />
                </g:if>
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
        <r:script>
            $(document).ready(function() {
                $('#descripcion').focus();
            });
        </r:script>
	</body>
</html>
