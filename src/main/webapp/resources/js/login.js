function registration() {
  $("#p-name").append('<p><label for="name" class="sr-only">' + i18n['fullname']+ '</label>' +
    '<input type="text" id="name" name="name" class="form-control" placeholder="' + i18n['fullname']+ '" required/></p>');
  $("#p-password").append('<p><label for="repeat" class="sr-only">' + i18n['repeat'] + '</label>' +
    '<input type="password" id="repeat" name="repeat" class="form-control" placeholder="' + i18n['repeat'] + '" onkeyup="verify()" required/></p>');
  $("#p-password").append('<p id="verify" style="color: #db1602"></p>')
  $("#loginForm").get(0).setAttribute('action', 'registration/');
  $("#btn-reg").html(i18n['authorization']).attr('onclick', 'authorization()');
  $("#submit").attr('disabled', true);
  $("#submit").html(i18n['signupbtn']);
  $("#password").attr('onkeyup', 'verify()');
}

function authorization() {
  $("#btn-reg").html(i18n['registration']).attr('onclick', 'registration()');
  $("#name").remove();
  $("#repeat").remove();
  $("#submit").attr('disabled', false);
  $("#submit").html(i18n['signinbtn']);
  $("#password").attr('onkeyup', '');
  $("#verify").html("");
  $("#loginForm").get(0).setAttribute('action', 'spring_security_check');
}

function verify() {
  let pass = $("#password").val();
  let repass = $("#repeat").val();
  if (pass !== repass) {
    $("#verify").html(i18n['notequal']);
    $("#submit").attr('disabled', true);
  } else {
    $("#verify").html("");
    $("#submit").attr('disabled', false);
  }
}


$(function () {
  if (successRegistration) {
    $("#err").append('<div class="alert alert-success">' + i18n["success"] + '</div>');
  }
  if (errorRegistration) {
    registration();
    $("#err").append('<div class="alert alert-danger">' + i18n["error"] + '</div>');
  }


});


