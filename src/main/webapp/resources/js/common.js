
let failedNote;

function closeNoty() {
  if (failedNote) {
    failedNote.close();
    failedNote = undefined;
  }
}

function successNoty(key) {
  closeNoty();
  new Noty({
    text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
    type: 'success',
    layout: "bottomRight",
    timeout: 1000
  }).show();
}

function failNoty(jqXHR) {
  closeNoty();
  failedNote = new Noty({
    text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + i18n["common.note.error_status"] + ": " + jqXHR.status + (jqXHR.responseText ? "<br>" + jqXHR.responseText : ""),
    type: "error",
    layout: "bottomRight"
  }).show();
}
