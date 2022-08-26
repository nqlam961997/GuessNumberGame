<%@page import="cybersoft.java18.backend.guessnumber.utils.UrlUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <style>
        .custom-container {
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            position: absolute;
            width: 100%;
        }
    </style>
</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light"
         style="background-color: #e3f2fd;">
        <a class="navbar-brand font-weight-bold" href="#">Trò Chơi Đoán
            Số</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active"><a
                        class="nav-link font-weight-bold"
                        href="<%=request.getContextPath() + UrlUtils.ROOT%>">Home<span
                        class="sr-only">(current)</span></a></li>
                <li class="nav-item"><a class="nav-link font-weight-bold"
                                        href="<%=request.getContextPath() + UrlUtils.GAME%>">Game</a></li>
                <li class="nav-item"><a class="nav-link font-weight-bold"
                                        href="<%=request.getContextPath() + UrlUtils.RANKED%>">Ranking</a>
                </li>
            </ul>
        </div>
        <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle font-weight-bold" href="#"
               role="button" data-bs-toggle="dropdown" aria-expanded="false"
               id="dropdownMenuButton1"> ${sessionScope.currentUser.name} </a>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item"
                       href="<%=request.getContextPath() + UrlUtils.SIGN_OUT%>">Sign
                    Out</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="container vh-100">
    <section class="custom-container">
        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <div class="row justify-content-center">
                        <form action="<%=request.getContextPath() + UrlUtils.NEW_GAME%>" method="post"
                              class="mt-2 mr-5 float-right">
                            <input type="text" name="game-session" value="${game.id}" hidden>
                            <div class="form-row align-items-center">
                                <button type="submit" class="btn btn-outline-success btn-lg">GAME MỚI</button>
                            </div>
                        </form>
                        <div class="container">
                            <div class="row justify-content-center mt-5 clearfix">
                                <div class="col-md-8" ${game.isCompleted ? 'hidden': ''}>
                                    <h2 class="text text-primary text-center">MỜI BẠN ĐOÁN SỐ</h2>
                                </div>
                                <div class="col-md-8" ${game.isCompleted ? '': 'hidden'}>
                                    <h2 class="text text-success text-center">PINGO!!! PINGO!!! PINGO!!!</h2>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-md-8">
                                    <form action="<%=request.getContextPath() + UrlUtils.GAME%>"
                                          method="post" ${game.isCompleted ? 'hidden': ''}>
                                        <input type="text" name="game-session" value="${game.id}" hidden>
                                        <div class="form-group form-row">
                                            <label for="number"></label>
                                            <input type="number" name="guess"
                                                   class="form-control form-control-lg text-center col-4 offset-4"
                                                   id="number" required ${game.isCompleted ? 'readonly': ''}>
                                        </div>
                                        <div class="form-row align-items-center">
                                            <button type="submit" class="btn btn-outline-primary btn-lg col-4 offset-4">
                                                Đoán
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="row justify-content-center mt-5">
                                <div class="col-md-8">
                                    <table class="table table-borderless">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Số đoán</th>
                                            <th scope="col">Kết quả</th>
                                            <th scope="col">Thời gian</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="guess" items="${game.guess}" varStatus="loop">
                                            <c:choose>
                                                <c:when test="${guess.result == 0}">
                                                    <tr class="table-success">
                                                        <th scope="row">${loop.index + 1}</th>
                                                        <td>${guess.value}</td>
                                                        <td>PINGO!!!</td>
                                                        <td>${guess.timestamp}</td>
                                                    </tr>
                                                </c:when>
                                                <c:when test="${guess.result == -1}">
                                                    <tr class="table-danger">
                                                        <th scope="row">${loop.index + 1}</th>
                                                        <td>${guess.value}</td>
                                                        <td>Số vừa đoán bé hơn kết quả.</td>
                                                        <td>${guess.timestamp}</td>
                                                    </tr>
                                                </c:when>
                                                <c:when test="${guess.result == 1}">
                                                    <tr class="table-warning">
                                                        <th scope="row">${loop.index + 1}</th>
                                                        <td>${guess.value}</td>
                                                        <td>Số vừa đoán lớn hơn kết quả.</td>
                                                        <td>${guess.timestamp}</td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/js/jquery-1.8.3.min.js"
        integrity="sha512-J9QfbPuFlqGD2CYVCa6zn8/7PEgZnGpM5qtFOBZgwujjDnG5w5Fjx46YzqvIh/ORstcj7luStvvIHkisQi5SKw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/js/jquery-ui-1.9.2.custom.min.js"
        integrity="sha512-/j/PhXKFsf5Gc8eMuXoUn1tBjDJthhC5kBpOBPlNyANs9vxbJ8DM/gsciQ4ykymnUtHuk0yedLnlpg7DPmjZZg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>