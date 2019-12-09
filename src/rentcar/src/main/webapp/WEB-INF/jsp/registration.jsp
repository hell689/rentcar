<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<u:hf title="Регистрация">
    <h1>Регистрация</h1>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <c:if test="${empty currentUser}">
        <div class="row">
            <form method="post" action="<c:url value="/registration.html"/>">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputName">Имя</label>
                        <input type="text" class="form-control" name="name" id="inputName" placeholder="Имя" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputSurname">Имя</label>
                        <input type="text" class="form-control" name="surname" id="inputSurname" placeholder="Фамилия" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputLogin">Логин</label>
                        <input type="text" class="form-control" name="login" id="inputLogin" placeholder="Логин" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPassword">Пароль</label>
                        <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputAddress">Адрес</label>
                    <input type="text" class="form-control" name="address"  id="inputAddress" placeholder="1234 Main St" required>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </form>
        </div>
    </c:if>
</u:hf>
