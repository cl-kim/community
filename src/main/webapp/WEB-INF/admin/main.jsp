<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">회원 이메일</th>
        <th scope="col">이름</th>
        <th scope="col">가입일자</th>
        <th scope="col">삭제</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${user.email}"></c:out> </td>
            <td><c:out value="${user.userName}"></c:out> </td>
            <td><fmt:parseDate value="${user.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="paresDateTime"></fmt:parseDate>
                <fmt:formatDate value="${paresDateTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            <td><a href="/admin/remove">X</a> </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<%@ include file="../bootstrap.jsp" %>
</body>
</html>