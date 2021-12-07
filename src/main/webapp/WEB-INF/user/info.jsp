<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
<div class="container-fluid p-5">
    <h2 class="">회원 정보 페이지</h2>
    <hr>
    <form th:action="/user/info/modify" method="post">
        <input hidden name="memberId" value="${userDTO.memberId}">
        <label>이메일 주소</label>
        <input type="text" name="email" value="${userDTO.email}" readonly>
        <label> 이름 </label>
        <input type="text" name="userName" value="${userDTO.userName}">
        <label> 변경할 비밀번호 </label>
        <input type="password" name="password" placeholder="변경할 비밀번호" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <button type="button" class="btn btn-primary modifyBtn">변경</button>
        <button type="button" class="btn btn-danger removeBtn">탈퇴</button>
        <script inline="javascript">

            var actionForm = $("form"); //form 태그 객체

            $(".removeBtn").click(function () {
                if (!confirm("정말 탈퇴하시겠습니까?")) {
                    return;
                }
                actionForm
                    .attr("action", "/user/info/remove")
                    .attr("method", "post");

                actionForm.submit();

            });

            $(".modifyBtn").click(function () {

                if (!confirm("수정하시겠습니까?")) {
                    return;
                }

                actionForm
                    .attr("action", "/user/info/modify")
                    .attr("method", "post")
                    .submit();
            });


        </script>
    </form>
</div>
</body>
</html>
