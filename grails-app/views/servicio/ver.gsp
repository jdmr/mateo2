
<%@ page import="contabilidad.Servicio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'servicio.label', default: 'Servicio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-servicio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-servicio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list servicio">
			
				<g:if test="${servicio?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="servicio.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${servicio}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${servicio?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="servicio.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${servicio}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${servicio?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="servicio.tags.label" default="Tags" /></span>
					
						<span class="property-value" aria-labelledby="tags-label"><g:fieldValue bean="${servicio}" field="tags"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${servicio?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="servicio.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${servicio?.empresa?.id}">${servicio?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${servicio?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="servicio.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${servicio?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${servicio?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="servicio.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${servicio?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
            <g:if test="${servicio.transacciones}">
            <fieldset class="form" style="margin:0;padding:0;">
                <ol class="property-list servicio" style="margin:0;padding:0;">
                    <li class="fieldcontain"><span>
                            <div id="transacciones">
                              <table>
                                <thead>
                                  <tr>
                                    <th><g:message code="transaccion.descripcion.label" /></th>
                                    <th style="width:200px;text-align:right;"><g:message code="transaccion.tags.label" /></th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <g:each var="transaccion" in="${servicio.transacciones}">
                                    <g:set var="transaccionId" value="${transaccion.id}"/>
                                    <tr>
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
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${servicio?.id}" />
					<g:link class="edit" action="edita" id="${servicio?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="create" controller="servicioTransaccion" action="nueva" id="${servicio?.id}"><g:message code="servicio.nuevaTransaccion" default="Nueva Transaccion" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
