<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/includes.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <br>
    <br>
    <div class="container-fluid">
        <div class="row my-4">
        <div class="col-sm-6 col-xs-12">
            <h4>Количество инстансов: 7</h4>
            <hr>
            <ul>
                <li>CPU: 22 ядра</li>
                <li>RAM: 48 Gb</li>
                <li>HDD: 2500 Gb</li>
            </ul>
            <hr>
            <p class="green-text">Работает: 5</p>
            <p class="red-text">Остановлено: 2</p>
        </div>
        <div class="col-sm-6 col-xs-12">
            <h4>Стоимость: $2.50 в час</h4>
            <hr>
            <ul>
                <li>CPU: $1.00 в час</li>
                <li>RAM: $0.70 в час</li>
                <li>HDD: $0.80 в час</li>
            </ul>
            <hr>
            <p></p>
            <p></p>
        </div>
        </div>
    </div>
</div>

<div class="modal-dialog modal" id="exampleModal" tabindex="-1" role="document" aria-labelledby="exampleModal"
     aria-hidden="true">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">About</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <div class="container-fluid">
                <div class="row text-center">
                    <div class="col-4">Hello!</div>
                    <div class="col-8">World!</div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button class="btn btn-primary">Save</button>
        </div>
    </div>
</div>

</body>
</html>