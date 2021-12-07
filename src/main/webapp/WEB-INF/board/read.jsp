<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
<h1 class="mt-4">Board Page</h1>

<div class="form-group">
    <label>Bno</label>
    <input type="text" class="form-control" name="bno" value="${dto.bno}" readonly>
</div>
<div class="form-group">
    <label>Title</label>
    <input type="text" class="form-control" name="title" value="${dto.title}" readonly>
</div>
<div class="form-group">
    <label>Writer</label>
    <input type="text" class="form-control" name="writer" value="${dto.writer}" readonly>
</div>
<div class="form-group">
    <label>Content</label>
    <textarea class="form-control" rows="5" name="content" readonly>[[${dto.content}]]</textarea>
</div>
<div class="form-group">
    <label>RegDate</label>
    <input type="text" class="form-control" name="regDate"
           value="${dto.regDate}" readonly>
</div>
<div class="form-group">
    <label>ModDate</label>
    <input type="text" class="form-control" name="modDate"
           value="${dto.modDate}" readonly>
</div>

<!--        <a th:href="@{/guestbook/modify(gno = ${dto.bno}, page=${requestDTO.page})}"><button type="button" class="btn btn-primary">Modify</button></a>-->

<!--        <a th:href="@{/guestbook/list(page=${requestDTO.page})}"><button type="button" class="btn btn-info">List</button></a>-->
<sec:authorize access="isAuthenticated()">
    <div if="${author.equals(dto.writer)}">
    <a href="/board/modify?bno=${dto.bno}">

    <button type="button" class="btn btn-light">Modify</button>
    </a>
    </div>
</sec:authorize>
    <a href="/board/list">
    <button type="button" class="btn btn-info">List</button>
    </a>

    <%@ include file="../bootstrap.jsp" %>
    </body>
</html>