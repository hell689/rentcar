<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<u:hf title="Аренда автомобилей">
    <h1>Аренда автомобилей</h1>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <c:if test="${empty currentUser}">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="alert alert-warning" role="alert">
                    Для работы с системой необходимо авторизироваться!
                </div>
                <form method="post" action="<c:url value="/login.html"/>">
                    <div class="form-group">
                        <label for="login">Login (напр. admin или user1)</label>
                        <input type="text" class="form-control" name="login" id="login" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <label for="password">Password (напр. 123)</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
                <a href="<c:url value="/registration.html"/>">Зарегистрироваться</a>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty currentUser}">
        <c:if test="${currentUser.role.toString() == 'Admin'}">
            <div class="row">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Цвета</h5>
                            <p class="card-text">Цвета автомобилей</p>
                            <a href="<c:url value="/color.html"/>" class="btn btn-primary">Перейти</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Марки</h5>
                            <p class="card-text">Марки автомобилей</p>
                            <a href="<c:url value="/mark.html"/>" class="btn btn-primary">Перейти</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Коробки передач</h5>
                            <p class="card-text">Виды коробок передач</p>
                            <a href="<c:url value="/gearbox.html"/>" class="btn btn-primary">Перейти</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <br>
        <div class="row">
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Автомобили</h5>
                        <p class="card-text">Автомобили, для аренды</p>
                        <a href="<c:url value="/car.html"/>" class="btn btn-primary">Перейти</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Заявки</h5>
                        <p class="card-text">Заявки на аренду автомобиля</p>
                        <a href="<c:url value="/request.html"/>" class="btn btn-primary">Перейти</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Аренда</h5>
                        <p class="card-text">Обработанные заявки на аренду</p>
                        <a href="<c:url value="/rent.html"/>" class="btn btn-primary">Перейти</a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</u:hf>
