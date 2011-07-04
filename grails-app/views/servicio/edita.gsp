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
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                <g:if test="${servicio.transacciones}">
                <fieldset class="form" style="margin:0;padding:0;">
                    <ol class="property-list servicio" style="margin:0;padding:0;">
                        <li class="fieldcontain"><span>
                                <div id="transacciones">
                                  <table>
                                    <thead>
                                      <tr>
                                        <th><g:message code="transaccion.descripcion.label" /></th>
                                        <th><g:message code="transaccion.tags.label" /></th>
                                        <th style="text-align:right; width:150px;"></th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <g:each var="transaccion" in="${servicio.transacciones}">
                                        <g:set var="transaccionId" value="${transaccion.id}"/>
                                        <tr>
                                            <td>${transaccion.descripcion}</td>
                                            <td>${transaccion.tags}</td>
                                            <td style="text-align:right; width:150px;"><g:link controller="servicioTransaccion" action="edita" id="${transaccion.id}"><g:message code="default.button.edit.label" /></g:link> | <g:link controller="servicioTransaccion" action="elimina" id="${transaccion.id}"><g:message code="default.button.delete.label" /></g:link></td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <table>
                                                    <thead>
                                                        <tr>
                                                            <th style="width:100px;">${message(code:'transaccion.cuenta.label')}</th>
                                                            <th style="width:100px;">${message(code:'transaccion.auxiliar.label')}</th>
                                                            <th>${message(code:'transaccion.nombre.label')}</th>
                                                            <th style='text-align:right;width:130px;'>${message(code:'transaccion.parcial.label')}</th>
                                                            <th style='text-align:right;width:130px;'>${message(code:'transaccion.debe.label')}</th>
                                                            <th style='text-align:right;width:130px;'>${message(code:'transaccion.haber.label')}</th>
                                                            <th style='text-align:right;width:100px;'>${message(code:'servicioTransaccion.preguntar.label',default:'¿Preguntar?')}</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <g:set var="counter" value="${1}" />
                                                        <g:set var="counter2" value="${1}" />
                                                        <g:set var="movimientos" value="${origenes[transaccion.id]}"/>
                                                        <g:each var="movimiento" in="${movimientos}" status="i">
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
                                                                <td style="text-align:center;"><g:checkBox name="preguntar$i" value="${movimiento.preguntar}" /></td>
                                                            </tr>
                                                            <g:if test="${movimiento.ultimo}"><g:set var="counter2" value="${1}" /></g:if>
                                                        </g:each>
                                                        <g:set var="counter" value="${1}" />
                                                        <g:set var="counter2" value="${1}" />
                                                        <g:set var="movimientos" value="${destinos[transaccion.id]}"/>
                                                        <g:each var="movimiento" in="${movimientos}" status="i">
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
                                                                <td style="text-align:center;"><g:checkBox name="preguntar$i" value="${movimiento.preguntar}" /></td>
                                                            </tr>
                                                            <g:if test="${movimiento.ultimo}"><g:set var="counter2" value="${1}" /></g:if>
                                                        </g:each>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <th colspan="4" style="text-align:right;">TOTAL</th>
                                                            <th style="text-align:right;"><g:formatNumber type="currency" number="${transaccion.importe}" /></th>
                                                            <th style="text-align:right;"><g:formatNumber type="currency" number="${transaccion.importe}" /></th>
                                                            <th>&nbsp;</th>
                                                        </tr>
                                                    </tfoot>
                                                </table>
                                            </td>
                                        </tr>
                                      </g:each>
                                    </tbody>
                                  </table>
                                </div>
                            </span>
                        </li>
                    </ol>
                </fieldset>
                </g:if>
				<fieldset class="buttons">
					<g:link class="create" controller="servicioTransaccion" action="nueva" id="${servicio?.id}"><g:message code="servicio.nuevaTransaccion" default="Nueva Transaccion" /></g:link>
					<g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
