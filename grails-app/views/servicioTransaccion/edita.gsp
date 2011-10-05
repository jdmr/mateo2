<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'transaccion.label', default: 'Transacción')}" />
  <g:set var="servicioName" value="${message(code: 'servicio.label', default: 'Servicio')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
  <r:require module="tagit" />
</head>
<body>
  <a href="#edit-cuenta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="edit" controller="servicio" action="edita" id="${transaccion.servicio.id}"><g:message code="default.edit.label" args="[servicioName]" /></g:link></li>
      <li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="edit-transaccion" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${transaccion}">
      <ul class="errors" role="alert">
        <g:eachError bean="${transaccion}" var="error">
          <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
      </ul>
    </g:hasErrors>
    <g:form action="actualiza" method="post" >
      <g:hiddenField name="id" value="${transaccion?.id}" />
      <g:hiddenField name="version" value="${transaccion?.version}" />
      <g:hiddenField name="cuentaId" value="" />
      <g:hiddenField name="auxiliarId" value="" />

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
                  <th style='text-align:right;width:130px;'>${message(code:'transaccion.debe.label')}</th>
                  <th style='text-align:right;width:100px;'>${message(code:'servicioTransaccion.preguntar.label',default:'¿Preguntar?')}</th>
                </tr>
              </thead>
              <tbody>
              <g:set var="counter" value="${1}" />
              <g:set var="counter2" value="${1}" />
              <g:each var="movimiento" in="${origenes}" status="i">
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
              </g:each>
              <g:set var="counter" value="${1}" />
              <g:set var="counter2" value="${1}" />
              <g:each var="movimiento" in="${destinos}" status="i">
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
          </div>

        </fieldset>
      </g:if>

      <fieldset class="form">
        <div class="fieldcontain">
          <h3>
            <g:message code="transaccion.tags.label" default="tags" />
          </h3>
          <ul name="tags">
            <g:each in="${transaccion?.tags?.tokenize(',')}">
              <li>${it}</li>
            </g:each>
          </ul>
        </div>

        <div class="fieldcontain ${hasErrors(bean: transaccion, field: 'descripcion', 'error')} required">
          <label for="descripcion">
            <g:message code="transaccion.descripcion.label" default="descripcion" />
            <span class="required-indicator">*</span>
          </label>
          <g:textArea name="descripcion" maxlength="200" required="" value="${transaccion?.descripcion}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: transaccion, field: 'importe', 'error')} required">
          <label for="importe">
            <g:message code="transaccion.importe.label" default="importe" />
          </label>
          <g:textField name="importe" maxlength="200" value="" style="width:400px;"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: transaccion, field: 'cuenta', 'error')} required">
          <label for="cuenta">
            <g:message code="transaccion.cuenta.label" default="cuenta" />
          </label>
          <g:textField name="cuenta" maxlength="200" value="" style="width:400px;"/>
        </div>

        <div id="auxiliarDiv" class="fieldcontain ${hasErrors(bean: transaccion, field: 'auxiliar', 'error')} required" style="display:none;">
          <label for="auxiliar">
            <g:message code="transaccion.auxiliar.label" default="auxiliar" />
          </label>
          <g:textField name="auxiliar" maxlength="200" value="" style="width:400px;"/>
        </div>

        <div class="fieldcontain">
          <label for="esDebe">
            <g:message code="transaccion.esDebe.label" default="esDebe" />
          </label>
          <input type="checkbox" name="esDebe" value="" id="esDebe" />
        </div>

        <div class="fieldcontain">
          <label for="preguntar">
            <g:message code="servicioTransaccion.preguntar.label" default="¿Preguntar?" />
          </label>
          <input type="checkbox" name="preguntar" value="" id="preguntar" />
        </div>

        <div class="fieldcontain">
          <label for="agrega"><g:message code="transaccion.agrega.label" /></label>
          <g:submitButton name="agrega" class="save" value="${message(code: 'transaccion.nuevoMovimiento.button')}"/>
        </div>

      </fieldset>
      <fieldset class="buttons">
        <g:actionSubmit class="save" action="actualizaIngreso" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        <g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        <g:link class="edit" controller="servicio" action="edita" id="${transaccion.servicio.id}"><g:message code="default.edit.label" args="[servicioName]" /></g:link>
      </fieldset>
    </g:form>
  </div>
<r:script>
  $(document).ready(function() {
  $('#cuenta').autocomplete({
  source:"${createLink(action:'cuentas')}"
  , select: function(event, ui) {
  $('#cuentaId').val(ui.item.id);
  if (ui.item.auxiliar) {
  $("#auxiliarId").val(ui.item.auxiliarId);
  $("#auxiliarDiv").toggle('blind',{},500, function() {
  $("#auxiliar").val($("#cuenta").val());
  $("#cuenta").val(ui.item.cuenta);
  $("#esDebe").focus();
  });
  } else if (ui.item.tieneAuxiliares) {
  $("#auxiliarDiv").toggle('blind',{},500, function() {
  $("#auxiliar").focus();
  });
  } else {
  $("#esDebe").focus();
  }
  }
  });
  $('#auxiliar').autocomplete({
  source:"${createLink(action:'auxiliares')}/"+$('#cuentaId').val()
  ,select: function(event,ui) {
  $('#auxiliarId').val(ui.item.id);
  $('#cuentaId').val(ui.item.cuentaId);
  $('#cuenta').val(ui.item.cuenta);
  $("#esDebe").focus();
  }
  });

  $("ul[name='tags']").tagit({select:true, tagSource: "${g.createLink(action: 'tags')}"});
$('#descripcion').select();
$('#descripcion').focus();
});
</r:script>
</body>
</html>
