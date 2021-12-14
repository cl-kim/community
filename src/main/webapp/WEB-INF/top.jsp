<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Community Service</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                    <sec:authorize access="isAnonymous()">
                         <li class="nav-item">
                         <a class="nav-link" href="/user/login">로그인</a>
                         </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                        <a class="nav-link" href="/user/logout">로그아웃</a>
                </li>
                    </sec:authorize>

                    <sec:authorize access="isAnonymous()">
                <li class="nav-item">
                        <a class="nav-link" href="/user/signup">회원가입</a>
                </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item">
                        <a class="nav-link" href="/admin/">어드민</a>
                </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_USER')">
                <li class="nav-item">
                        <a class="nav-link" href="/user/info">내정보</a>
                </li>
                    </sec:authorize>

            </ul>
        </div>
    </div>
</nav>
