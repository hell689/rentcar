<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <title>${title}</title>
</head>
<body >

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="<c:url value="/index.html"/>">Rent Car</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/index.html"/>">Главная <span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${currentUser.role.toString() == 'Admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/color.html"/>">Цвета</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/mark.html"/>">Марки авто</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/gearbox.html"/>">Коробки передач</a>
                    </li>
                </c:if>
                <c:if test="${not empty currentUser}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/car.html"/>">Автомобили</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/request.html"/>">Заявки</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/rent.html"/>">Аренда</a>
                    </li>
                </c:if>

            </ul>
            <c:if test="${not empty currentUser}">
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/logout.html"/>">
                      ${currentUser.login}
                      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Выйти</button>
                </form>
            </c:if>
        </div>
    </nav>

        <div class="container">
            <jsp:doBody/>
        </div>
    <div id="back"></div>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>