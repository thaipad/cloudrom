let form = $('#detailsForm');
let ajaxUrl = "ajax/instances/";
let datatableApi;

function updateTable() {
  $.get(ajaxUrl, function (data) {
    datatableApi.clear().rows.add(data).draw()
  });
}

function makeEditable() {
  // form = $('#detailsForm');
  $(document).ajaxError(function (event, jqXHR, options, jsExc) {
    failNoty(jqXHR);
  });

  // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
  $.ajaxSetup({cache: false});

  const token = $("meta[name='_csrf']").attr("content");
  const header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

}

function add() {
  form = $('#detailsForm');
  $("#modalTitle").html(i18n["addTitle"]);
  form.find("#cpu").attr('readonly', false);
  form.find("#ram").attr('readonly', false);
  form.find("#hdd").attr('readonly', false);
  form.find("#os").attr('readonly', false);
  form.find("#id").val("");
  form.find("#name").val("");
  form.find("#description").val("");
  form.find("#cpu").val(1);
  form.find("#ram").val(1);
  form.find("#hdd").val(25);
  $("#editRow").modal();
}

function updateRow(id) {
  $.get(ajaxUrl + id, function (data) {
    if (data.runningDate != null) {
      form = $('#editNameForm');
      $("#editNameTitle").html(i18n["modifyTitle"]);
      form.find("#idInst").val(data.id);
      form.find("#nameInst").val(data.name);
      form.find("#descrInst").val(data.description);
      $('#editNameRow').modal();
    } else {
      form = $('#detailsForm');
      $("#modalTitle").html(i18n["modifyTitle"]);
      form.find("#id").val(data.id);
      form.find("#name").val(data.name);
      form.find("#description").val(data.description);
      form.find("#cpu").val(data.cpu);
      form.find("#ram").val(data.ram);
      form.find("#hdd").val(data.hdd);
      form.find("#os").val(data.os);
      form.find("#os").find('[value=' + data.os + ']').attr('selected', true);
      $('#editRow').modal();
    }
  });
}

function save() {
  form = $('#detailsForm');
  let os = form.find("#os").val();
  form.find("#osstr").val(os);

  $.ajax({
    type: "POST",
    url: ajaxUrl,
    data: form.serialize()
  }).done(function () {
    $("#editRow").modal("hide");
    updateTable();
    successNoty("common.note.saved");
  });
};

function saveName() {
  form = $('#editNameForm');
  $.ajax({
    type: "POST",
    url: ajaxUrl + "name",
    data: form.serialize()
  }).done(function () {
    $("#editNameRow").modal("hide");
    updateTable();
    successNoty("common.note.saved");
  });
};

function deleteRow(id) {
  $.ajax({
    url: ajaxUrl + id,
    type: "DELETE"
  }).done(function () {
    updateTable();
    successNoty("common.note.deleted");
  });
}

function runRow(idButton, id) {
  $('#'+idButton).html('<span class="fa fa-spinner fa-spin"></span>');
  $('#'+idButton).attr('disabled', true);
  $('#'+idButton).parent().find('#mod').attr('disabled', true);
  $('#'+idButton).parent().find('#del').attr('disabled', true);
  $.ajax({
    url: ajaxUrl + "run/" + id,
    type: "GET"
  }).done(function () {
    updateTable();
    successNoty("instance.note.run");
  });
}

function stopRow(idButton, id) {
  $('#'+idButton).html('<span class="fa fa-spinner fa-spin"></span>');
  $('#'+idButton).attr('disabled', true);
  $.ajax({
    url: ajaxUrl + "stop/" + id,
    type: "GET"
  }).done(function () {
    updateTable();
    successNoty("instance.note.stop");
  });
}

$(function () {
  $("#btn-add-instance").css({display: 'block'});

  datatableApi = $("#datatable").DataTable({
    "ajax": {
      "url": ajaxUrl,
      "dataSrc": ""
    },
    "paging": false,
    "info": true,
    "columns": [
      {
        "render": renderInstance
      },
    ],
    "initComplete": makeEditable
  });
});

function renderInstance(data, type, row) {
  let blockNames = function(name, description, os) {
    return '' +
      '<h4 class="instance-head">' + name + '</h4>' +
      '<p class="instance-desc">' + description + '</p>' +
      '<div class="instance-desc">' + i18n['os'] + ': ' + os + '</div>';
  };

  let blockBtn = function () {

    let buttonMod = '<button class="btn btn-instance" id="mod"' +
      ' title="' + i18n['descrmodify'] + (row.runningDate != null ? '"' : '"') +
      ' onclick="updateRow(' + row.id + ')">' +
      '<span class="fa fa-wrench"></span></button>';

    let buttonDel = '<button class="btn btn-instance" id="del"' +
      ' title="' + i18n['descrdelete'] + (row.runningDate != null ? '" disabled="disabled"' : '"') +
      ' onclick="deleteRow(' + row.id + ')">' +
      '<span class="fa fa-trash"></span></button>';

    let buttonRun = '<button class="btn btn-instance" id="run' + row.id + '"' +
      ' title="' + i18n['descrrun'] + (row.runningDate != null ? '" disabled="disabled"' : '"') +
      ' onclick="runRow(this.id, ' + row.id + ')"><span class="fa fa-play"></span></button>';

    let buttonStop = '<button class="btn btn-instance" id="stop' + row.id + '"' +
      ' title="' + i18n['descrstop'] + (row.runningDate == null ? '" disabled="disabled"' : '"') +
      ' onclick="stopRow(this.id, ' + row.id + ')"><span class="fa fa-stop"></span></button>';

    return '' +
      '<div class="col-sm-12 col-xs-12">' +
        buttonMod +
        buttonDel +
        buttonRun +
        buttonStop +
      '</div>';
  }

  let blockRes = function (name, unit, value) {
    return '' +
    '<div class="row instance-conf">' +
      '<div class="col-8">' + name + '</div>' +
      '<div class="col-2" style="text-align: right">' + value + '</div>' +
      '<div class="col-2">' + unit + '</div>' +
    '</div>';
  };

  let blockSummary = function () {
    let delta = 0;
    if (row.runningDate != null) {
      let runningDate = new Date(row.runningDate[0], row.runningDate[1] - 1, row.runningDate[2], row.runningDate[3], 0, 0);
      delta = Math.floor((new Date().getTime() - runningDate.getTime()) / 3660000);
    }

    return '' +
      '<div class="container">' +
        '<h4 class="instance-head">' + i18n['summary'] + ':</h4>' +
        '<h6>' + i18n['state'] + ': ' +
          '<span class=' +
            (row.runningDate != null ? '"green-text">' + i18n['runningstate'] : '"red-text">' + i18n['stoppedstate']) +
          '</span> </h6>' +
        '<h6>' + i18n['workingtime'] + ': <span class="orange-text">' + delta + ' </span>' + i18n['hour'] + '</h6>' +
        '<h6>' + i18n['price'] + ': <span  class="orange-text">$0.25 </span>' + i18n['perhour'] + '</h6>' +
      '</div>';
  };

  return '' +
  '<div class="container-fluid">' +
    '<div class="container">' +
      '<div id="instance-row" class="row my-4">' +
        blockBtn() +
        '<div class="col-sm-8 col-xs-12">' +
          '<div class="container">' +
            blockNames(row.name, row.description, row.os) +
            '<hr>' +
            '<div class="container-fluid">' +
              '<div class="container ml-2 mb-2">' +
                blockRes(i18n['cpu'], i18n['cpuUnit'], row.cpu) +
                blockRes(i18n['ram'], i18n['ramUnit'], row.ram) +
                blockRes(i18n['hdd'], i18n['hddUnit'], row.hdd) +
              '</div>' +
            '</div>' +
          '</div>' +
        '</div>' +
        '<div class="col-sm-4 col-xs-12">' +
          blockSummary() +
        '</div>' +
      '</div>' +
    '</div>' +
  '</div>';




}