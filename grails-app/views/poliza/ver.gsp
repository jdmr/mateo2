
<%@ page import="contabilidad.Poliza" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-poliza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevaIngreso"><g:message code="poliza.nueva.ingreso.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-poliza" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list poliza">
			
				<g:if test="${poliza?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="poliza.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${poliza}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="poliza.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${poliza}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="poliza.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${poliza}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="poliza.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${poliza}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.libro}">
				<li class="fieldcontain">
					<span id="libro-label" class="property-label"><g:message code="poliza.libro.label" default="Libro" /></span>
					
						<span class="property-value" aria-labelledby="libro-label"><g:link controller="libro" action="show" id="${poliza?.libro?.id}">${poliza?.libro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.ejercicio}">
				<li class="fieldcontain">
					<span id="ejercicio-label" class="property-label"><g:message code="poliza.ejercicio.label" default="Ejercicio" /></span>
					
						<span class="property-value" aria-labelledby="ejercicio-label"><g:link controller="ejercicio" action="show" id="${poliza?.ejercicio?.id}">${poliza?.ejercicio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="poliza.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${poliza?.empresa?.id}">${poliza?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="poliza.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${poliza?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${poliza?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="poliza.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${poliza?.lastUpdated}" /></span>
					
				</li>
				</g:if>

			</ol>
            <g:if test="${poliza.transacciones}">
            <fieldset class="form" style="margin:0;padding:0;">
                <ol class="property-list poliza" style="margin:0;padding:0;">
                    <li class="fieldcontain"><span>
                            <div id="transacciones">
                              <table>
                                <thead>
                                  <tr>
                                    <th style="width:200px;"><g:message code="transaccion.folio.label" /></th>
                                    <th><g:message code="transaccion.descripcion.label" /></th>
                                    <th style="width:200px;text-align:right;"><g:message code="transaccion.tags.label" /></th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <g:each var="transaccion" in="${poliza.transacciones}">
                                    <g:set var="transaccionId" value="${transaccion.id}"/>
                                    <tr>
                                        <td>${transaccion.folio}</td>
                                        <td>${transaccion.descripcion}</td>
                                        <td style="text-align:right;">${transaccion.tags}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th style="width:100px;">${message(code:'transaccion.cuenta.label')}</th>
                                                        <th style="width:100px;">${message(code:'transaccion.auxiliar.label')}</th>
                                                        <th>${message(code:'transaccion.concepto.label')}</th>
                                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.parcial.label')}</th>
                                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.debe.label')}</th>
                                                        <th style='text-align:right;width:130px;'>${message(code:'transaccion.haber.label')}</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <g:set var="counter" value="${1}" />
                                                    <g:set var="counter2" value="${1}" />
                                                    <g:set var="movimientos" value="${origenes[transaccion.id]}"/>
                                                    <g:each var="movimiento" in="${movimientos}">
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
                                                        <g:if test="${movimiento.ultimo}"><g:set var="counter2" value="${1}" /></g:if>
                                                    </g:each>
                                                    <g:set var="counter" value="${1}" />
                                                    <g:set var="counter2" value="${1}" />
                                                    <g:set var="movimientos" value="${destinos[transaccion.id]}"/>
                                                    <g:each var="movimiento" in="${movimientos}">
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
                                                        <g:if test="${movimiento.ultimo}"><g:set var="counter2" value="${1}" /></g:if>
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
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${poliza?.id}" />
                    <g:if test="${poliza.estatus == 'ABIERTA'}">
                        <g:link class="edit" action="edita" id="${poliza?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					    <g:actionSubmit class="save" action="cierra" value="${message(code: 'poliza.button.cierra.label', default: 'Cierra')}" onclick="return confirm('${message(code: 'poliza.button.cierra.confirm.message', default: 'Are you sure?')}');" />
                        <g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
