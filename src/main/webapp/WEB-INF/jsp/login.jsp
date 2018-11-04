<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="fragments/includes.jsp"/>

    <script type="text/javascript" src="resources/js/login.js" defer></script>

    <title><spring:message code="app.title"/></title>
<body>
<div class="container">
    <form:form id="loginForm" class="form-signin align-content-center text-center" method="post"
          action="spring_security_check">
        <div id="err">
            <c:if test="${param.error}">
                <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
            </c:if>
            <c:if test="${not empty param.message}">
                <div class="message"><spring:message code="${param.message}"/></div>
            </c:if>
        </div>
        <br/>
        <img id="logo" class="mb-4" src="resources/images/logo.png" alt="">
        <%--<h4 class="h4 mb-3 font-weight-normal"><spring:message code="app.logininput"/></h4>--%>
        <div id="p-name">
            <p>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control"
                       placeholder="<spring:message code="login.email"/>" required autofocus>
            </p>
        </div>
        <div id="p-password">
            <p>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control"
                       placeholder="<spring:message code="login.password"/>" required>
            </p>
        </div>
        <button id="submit" class="btn btn-lg btn-primary btn-block btn-orange" type="submit"><spring:message code="login.btn_sign_in"/></button>
        <a class="btn btn-reg a-orange" id="btn-reg" onclick="registration()"><spring:message code="login.registration"/></a>
        <hr>
            <p class="mt-5 mb-3 text-muted">Cloudrom &copy; 2018
            <a class="a-orange ml-3" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">en</a>
            <a class="a-orange" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">ru</a>
            </p>
    </form:form>
</div>
</body>

<script type="text/javascript" defer>
  var i18n = [];
  i18n["authorization"] = '<spring:message code="login.authorization"/>';
  i18n["authorization"] = '<spring:message code="login.authorization"/>';
  i18n["registration"] = '<spring:message code="login.registration"/>';
  i18n["repeat"] = '<spring:message code="login.repeat"/>';
  i18n["notequal"] = '<spring:message code="login.note.passwords_not_equal"/>';
  i18n["signupbtn"] = '<spring:message code="login.btn_sign_up"/>';
  i18n["signinbtn"] = '<spring:message code="login.btn_sign_in"/>';
  i18n["fullname"] = '<spring:message code="login.fullname"/>';
  i18n["error"] = '<spring:message code="login.note.user_exist"/>';
  i18n["success"] = '<spring:message code="login.note.success_registration"/>';

  let errorRegistration = false;
  let successRegistration = false;
  if (${not empty errorReg}) {
      errorRegistration = true;
  }
  if (${not empty successReg}) {
    successRegistration = true;
  }
</script>

</html>
