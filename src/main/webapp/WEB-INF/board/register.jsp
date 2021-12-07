<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
<h1 class="mt-4">Board Register Page</h1>

<form action="/board/register" method="post">
    <div class="form-group">
        <label >Title</label>
        <input type="text" class="form-control" name="title" placeholder="Enter Title">
    </div>
    <div class="form-group row">
        <label>writer</label>
        <input type="hidden" name="writer" class="form-control" id="inputWriter" value="${writer}">
    </div>
    <div class="form-group">
        <label >Content</label>
        <textarea class="form-control" rows="5" name="content"></textarea>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="button"
</form>
<%@ include file="../bootstrap.jsp" %>

</body>
</html>
