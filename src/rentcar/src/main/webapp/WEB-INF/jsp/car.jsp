<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<u:hf title="Автомобили">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1>Автомобили</h1>
            <c:if test="${not empty param.errorMessage}">
                <div class="alert alert-danger" role="alert">
                    ${param.errorMessage}
                </div>
            </c:if>
            <table class="table table-hover">
                <thead>
                <th scope="col">Марка</th>
                <th scope="col">Цвет</th>
                <th scope="col">Объем двигателя</th>
                <th scope="col">Коробка передач</th>
                <c:if test="${currentUser.role.toString() == 'Admin'}">
                    <th scope="col"></th>
                </c:if>
                </thead>
                <tbody>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td>${car.mark.mark}</td>
                        <td>${car.color.color}</td>
                        <td>${car.volume}</td>
                        <td>${car.gearbox.gearbox}</td>
                        <c:if test="${currentUser.role.toString() == 'Admin'}">
                            <td>
                                <form action="<c:url value="car/delete.html"/>" method="post">
                                <c:if test="${not empty car.id}"><input name="deleteId" value="${car.id}" type="hidden">
                                </c:if>
                                <button class="">Удалить</button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${currentUser.role.toString() == 'Admin'}">
            <div  class="col-md-10 offset-md-2">
                <form class="form-inline" action="<c:url value="/car/save.html"/>" method=post>
                <div class="form-row align-items-center">
                    <div class="col-auto">
                        <select class="form-control" name="mark">
                            <c:forEach var="mark" items="${marks}">
                                <option value="${mark.id}">${mark.mark}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-auto">
                        <select class="form-control" name="color">
                            <c:forEach var="color" items="${colors}">
                                <option value="${color.id}">${color.color}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-auto">
                        <input type="text" class="form-control mr-sm-2" id="volume" name="volume" placeholder="Объём" required>
                    </div>
                    <div class="col-auto">
                        <select class="form-control" name="gearbox">
                            <c:forEach var="gearbox" items="${gearboxes}">
                                <option value="${gearbox.id}">${gearbox.gearbox}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-2">Добавить</button>
                    </div>
                </div>
                </form>
            </div>
        </c:if>
    </div>

</u:hf>
