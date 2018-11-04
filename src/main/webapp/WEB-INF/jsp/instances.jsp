<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/includes.jsp"/>
<script type="text/javascript" src="resources/js/instances.js" defer></script>

<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container-fluid">
    <div class="container mt-4">
        <table class="table table-sm table-borderless w-100" id="datatable">
            <thead>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="osstr" name="osstr">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="instance.form.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="instance.form.name"/>"/>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="instance.form.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="instance.form.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="cpu" class="col-form-label"><spring:message code="instance.form.cpu"/> (<spring:message code="instance.form.cpu_unit"/>)</label>
                        <input type="number" class="form-control" id="cpu" name="cpu"
                               placeholder=<spring:message code="instance.form.cpu"/>>
                    </div>
                    <div class="form-group">
                        <label for="ram" class="col-form-label"><spring:message code="instance.form.ram"/> (<spring:message code="instance.form.ram_unit"/>)</label>
                        <input type="number" class="form-control" id="ram" name="ram"
                               placeholder=<spring:message code="instance.form.ram"/>>
                    </div>
                    <div class="form-group">
                        <label for="hdd" class="col-form-label"><spring:message code="instance.form.hdd"/> (<spring:message code="instance.form.hdd_unit"/>)</label>
                        <input type="number" class="form-control" id="hdd" name="hdd"
                               placeholder=<spring:message code="instance.form.hdd"/>>
                    </div>
                    <div class="form-group">
                        <label for="os" class="col-form-label"><spring:message code="instance.form.os"/></label>
                        <form:select id="os" path="os">
                            <form:option value="" label="Select OS"/>
                            <form:options items="${os}" />
                        </form:select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    Cancel
                </button>
                <button type="button" class="btn btn-primary btn-orange" onclick="save()">
                    <span class="fa fa-check"></span>
                    Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editNameRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="editNameTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="editNameForm">
                    <input type="hidden" id="idInst" name="idInst">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="instance.form.name"/></label>
                        <input type="text" class="form-control" id="nameInst" name="nameInst"
                               placeholder="<spring:message code="instance.form.name"/>"/>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="instance.form.description"/></label>
                        <input type="text" class="form-control" id="descrInst" name="descrInst"
                               placeholder="<spring:message code="instance.form.description"/>">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    Cancel
                </button>
                <button type="button" class="btn btn-primary btn-orange" onclick="saveName()">
                    <span class="fa fa-check"></span>
                    Save
                </button>
            </div>
        </div>
    </div>
</div>


</body>

<script type="text/javascript">

  var i18n = [];
  i18n['start'] = "start";
  i18n["addTitle"] = '<spring:message code="instance.form.add_title"/>';
  i18n["modifyTitle"] = '<spring:message code="instance.form.edit_title"/>';
  i18n["cpu"] = '<spring:message code="instance.form.cpu"/>';
  i18n["cpuUnit"] = '<spring:message code="instance.form.cpu_unit"/>';
  i18n["hdd"] = '<spring:message code="instance.form.hdd"/>';
  i18n["hddUnit"] = '<spring:message code="instance.form.hdd_unit"/>';
  i18n["ram"] = '<spring:message code="instance.form.ram"/>';
  i18n["ramUnit"] = '<spring:message code="instance.form.ram_unit"/>';
  i18n["os"] = '<spring:message code="instance.form.os"/>';
  i18n['summary'] = '<spring:message code="instance.summary"/>';
  i18n['state'] = '<spring:message code="instance.state"/>';
  i18n['runningstate'] = '<spring:message code="instance.running_state"/>';
  i18n['stoppedstate'] = '<spring:message code="instance.stopped_state"/>';
  i18n['workingtime'] = '<spring:message code="instance.working_time"/>';
  i18n['hour'] = '<spring:message code="instance.hour"/>';
  i18n['price'] = '<spring:message code="instance.price"/>';
  i18n['perhour'] = '<spring:message code="instance.per_hour"/>';
  i18n['descrmodify'] = '<spring:message code="instance.btn_edit_descr"/>';
  i18n['descrdelete'] = '<spring:message code="instance.btn_delete_descr"/>';
  i18n['descrstop'] = '<spring:message code="instance.btn_stop_descr"/>';
  i18n['descrrun'] = '<spring:message code="instance.btn_run_descr"/>';

  <c:forEach var="key" items='<%=new String[]{
          "common.note.deleted",
          "common.note.saved",
          "common.note.enabled",
          "common.note.disabled",
          "instance.note.run",
          "instance.note.stop",
          "common.note.error_status"
  }%>'>
  i18n["${key}"] = "<spring:message code="${key}"/>";
  </c:forEach>
</script>

</html>
