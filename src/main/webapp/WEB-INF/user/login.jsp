<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
    <h1>로그인</h1>
    <hr>
    <form action="/user/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text" required name="username" placeholder="이메일 입력해주세요">
        <input type="password" required name="password" placeholder="비밀번호">
        <button type="submit">로그인</button>
    </form>
<%@ include file="../bootstrap.jsp" %>
</body>
</html>
