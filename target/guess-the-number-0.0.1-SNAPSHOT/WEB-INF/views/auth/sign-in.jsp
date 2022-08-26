<%@page import="cybersoft.java18.backend.guessnumber.utils.UrlUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Ip</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <link href="/WEB-INF/views/assets/css/custom.css">
</head>
<body>
<div class="container-fluid">
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div
                    class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                    <div class="card" style="border-radius: 1rem;">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-none d-md-block">
                                <img src="https://picsum.photos/200/300" alt="login form"
                                     class="img-fluid"
                                     style="border-radius: 1rem 0 0 1rem; width: 100%; height: 100%"/>
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                <div class="card-body p-4 p-lg-5 text-black">
                                    <form action="<%=request.getContextPath() + UrlUtils.SIGN_IN%>"
                                          method="post">
                                        <div class="mb-3 pb-1">
                                            <span class="h1 fw-bold mb-0">Guess Number</span>
                                        </div>

                                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign
                                            into your account</h5>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="username">User Name</label> <input
                                                type="text" id="username"
                                                class="form-control form-control-lg" name="username"/>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Password</label> <input
                                                type="password" id="password"
                                                class="form-control form-control-lg" name="password"/>
                                        </div>

                                        <div class="pt-1 mb-4">
                                            <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
                                        </div>

                                        <a class="small text-muted" href="#!">Forgot password?</a>
                                        <p class="mb-5 pb-lg-2" style="color: #393f81;">
                                            Don't have an account? <a href="/guess-the-number/sign-up"
                                                                      style="color: #393f81;">Register here</a>
                                        </p>
                                        <a href="#!" class="small text-muted">Terms of use.</a> <a
                                            href="#!" class="small text-muted">Privacy policy</a>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>