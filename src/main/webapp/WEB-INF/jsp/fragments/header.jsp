<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top border-bottom shadow-sm">
    <div class="container">
        <a href="/" class="navbar-brand"><img src="resources/images/logo.png" alt="cloudrom.ru"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="fa fa-bars"></span>
        </button>


        <div class="collapse navbar-collapse navbar-left" id="navbarSupportedContent">
            <ul class="nav navbar-nav">
                <li class="nav-item pl-3">
                    <a href="/" class="nav-link float-left"><spring:message code="app.home"/></a>
                </li>
                <li class="nav-item pl-3">
                    <a href="/instances" class="nav-link"><spring:message code="instance.title"/></a>
                </li>
                <li class="nav-item dropdown pl-3">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">${name} ${email}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="nav-item">
                            <a class="nav-link" href="#"><spring:message code="app.account"/></a>
                        </li>
                        <li class="nav-item">
                            <form:form class="form-inline my-2" action="logout" method="post">
                                <button class="nav-link" type="submit">
                                    <spring:message code="app.logout"/>
                                </button>
                            </form:form>
                            <%--<a class="nav-link" href="logout"><spring:message code="app.logout"/></a>--%>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown pl-5 ">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<span class="caret"></a>
                    <ul class="dropdown-menu">
                        <li class="nav-item">
                        <a class="nav-link" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
        <button id="btn-add-instance" class="btn btn-primary  btn-orange"
                title="<spring:message code="instance.form.add_title"/>" style="display: none" onclick="add()">
            <span class="fa fa-plus"></span>
        </button>
    </div>
</nav>
