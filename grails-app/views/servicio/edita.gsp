<%@ page import="contabilidad.Servicio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'servicio.label', default: 'Servicio')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
        <r:require module="tagit" />
	</head>
	<body>
		<a href="#edit-servicio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-servicio" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${servicio}">
			<ul class="errors" role="alert">
				<g:eachError bean="${servicio}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${servicio?.id}" />
				<g:hiddenField name="version" value="${servicio?.version}" />
                <g:if test="${origenes}">
                    <fieldset class="form" style="margin:0;padding:0;">
                        <div id="movimientosDiv">
                            <table style="margin:0;">
                                <thead>
                                    <tr>
                                        <th style="width:100px;">${message(code:'transaccion.cuenta.label')}</th>
                                        <th style="width:100px;">${message(code:'transaccion.auxiliar.label')}</th>
                                        <th>${message(code:'transaccion.nombre.label')}</th>
                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.parcial.label')}</th>
                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.debe.label')}</th>
                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.haber.label')}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <g:set var="counter" value="${1}" />
                                    <g:set var="counter2" value="${1}" />
                                    <g:each var="movimiento" in="${origenes}">
                                        <tr>
                                            <td <g:if test="${movimiento.padre}">style="text-decoration:underline;"</g:if>><g:if test="${!movimiento.auxiliar}">${movimiento.cuenta.numero}</g:if></td>
                                            <td>${movimiento.auxiliar?.numero}</td>
                                            <td <g:if test="${movimiento.padre}">style="text-decoration:underline;"</g:if>><g:if test="${!movimiento.auxiliar}">${movimiento.cuenta.descripcion}</g:if><g:else>${movimiento.auxiliar.descripcion}</g:else></td>
                                            <td style='text-align:right;<g:if test="${movimiento.ultimo}">text-decoration:underline;</g:if>'>
                                                <g:if test="${movimiento.auxiliar}">
                                                    <g:if test="${(counter2++) == 1}">
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" />
                                                    </g:if>
                                                    <g:else>
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" currencySymbol="" />
                                                    </g:else>
                                                </g:if>
                                            </td>
                                            <td style='text-align:right;'>
                                                <g:if test="${!movimiento.auxiliar}">
                                                    <g:if test="${(counter++) == 1}">
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" />
                                                    </g:if>
                                                    <g:else>
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" currencySymbol="" />
                                                    </g:else>
                                                </g:if>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </g:each>
                                    <g:set var="counter" value="${1}" />
                                    <g:set var="counter2" value="${1}" />
                                    <g:each var="movimiento" in="${destinos}">
                                        <tr>
                                            <td <g:if test="${movimiento.padre}">style="text-decoration:underline;"</g:if>><g:if test="${!movimiento.auxiliar}">${movimiento.cuenta.numero}</g:if></td>
                                            <td>${movimiento.auxiliar?.numero}</td>
                                            <td style='padding-left:30px;<g:if test="${movimiento.padre}">text-decoration:underline;</g:if>'><g:if test="${!movimiento.auxiliar}">${movimiento.cuenta.descripcion}</g:if><g:else>${movimiento.auxiliar.descripcion}</g:else></td>
                                            <td style='text-align:right;<g:if test="${movimiento.ultimo}">text-decoration:underline;</g:if>'>
                                                <g:if test="${movimiento.auxiliar}">
                                                    <g:if test="${(counter2++) == 1}">
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" />
                                                    </g:if>
                                                    <g:else>
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" currencySymbol="" />
                                                    </g:else>
                                                </g:if>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td style='text-align:right;'>
                                                <g:if test="${!movimiento.auxiliar}">
                                                    <g:if test="${(counter++) == 1}">
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" />
                                                    </g:if>
                                                    <g:else>
                                                        <g:formatNumber type="currency" number="${movimiento.importe}" currencySymbol="" />
                                                    </g:else>
                                                </g:if>
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="4" style="text-align:right;">TOTAL</th>
                                        <th style="text-align:right;"><g:formatNumber type="currency" number="${transaccion.importe}" /></th>
                                        <th style="text-align:right;"><g:formatNumber type="currency" number="${transaccion.importe}" /></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
        
                    </fieldset>
                </g:if>

				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
