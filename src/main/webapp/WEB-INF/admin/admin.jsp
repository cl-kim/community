<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

    <tr th:each="user : ${userList}" >
        <td scope="row">
            [[${user.email}]]
        </td>
        <td>[[${user.userName}]]</td>
        <td>[[${#temporals.format(user.regDate, 'yyyy/MM/dd')}]]</td>
        <td><a href="/admin/remove">X</a> </td>
    </tr>



    </tbody>
</table>
<%@ include file="../bootstrap.jsp" %>
</body>
</html>
