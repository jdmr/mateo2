
<%@ page import="contabilidad.Cuenta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuenta.label', default: 'Cuenta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cuenta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-cuenta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${session.navegacion}">
            <h2>
                <g:each in="${session.navegacion}" var="padre">
                <g:link action="lista" params="[padre:padre.id]">${padre.descripcion}</g:link>
                </g:each>
            </h2> 
            </g:if>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numero" title="${message(code: 'cuenta.numero.label', default: 'numero')}" />
					
						<th><g:message code="cuenta.padre.label" default="Padre" /></th>

						<g:sortableColumn property="descripcion" title="${message(code: 'cuenta.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
                <g:set var="padre" value="" />
                <g:set var="padre1" value="" />
                <g:set var="breadcrumb" value="" />
				<g:each in="${cuentas}" status="i" var="cuenta">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
                        <td><g:link action="ver" id="${cuenta.id}">${fieldValue(bean: cuenta, field: "numero")}</g:link></td>
					
						<td>${cuenta.padre?.descripcion}</td>
					
                        <td>
                            <g:if test="${cuenta.padre}">
                                <g:if test="${padre != cuenta.padre}">
                                    <g:if test="${padre1 != cuenta.padre}">
                                        <g:set var="breadcrumb" value="${breadcrumb}-&nbsp;" />
                                    </g:if>
                                    <g:else>
                                        <g:set var="breadcrumb" value="-&nbsp;" />
                                    </g:else>
                                </g:if>
                                <g:set var="padre" value="${cuenta.padre}" />
                                <g:if test="${!padre1}">
                                    <g:set var="padre1" value="${cuenta.padre}" />
                                </g:if>
                            </g:if>
                            <g:else>
                            <g:set var="padre" value="" />
                            <g:set var="padre1" value="" />
                            <g:set var="breadcrumb" value="" />
                            </g:else>
                            ${breadcrumb}${fieldValue(bean: cuenta, field: "descripcion")}
                        </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
