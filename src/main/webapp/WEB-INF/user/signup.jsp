<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
        <h1>회원 가입</h1>
            <form action="/user/signup" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="text" name="email" placeholder="이메일을 입력해주세요" required>
                <input type="password" name="password" placeholder="비밀번호" required>
                <input type="text"name="userName" placeholder="이름을 입력하세요" required>
                <input type="text" id="postcode" name="postcode" placeholder="우편번호">
                <button type="button" onclick="kakaoPostcode()">우편번호 찾기</button><br>
                <input type="text" id="address" name="address" readonly required>
                <input type="text" name="detailAddress" placeholder="상세 주소">
                <br><button class="btn btn-light" type="submit">가입하기</button>
            </form>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function kakaoPostcode(){
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.roadAddress; // 주소 넣기
                document.getElementById('postcode').value = data.zonecode;
            }
        }).open();
    }
</script>
<%@ include file="../bootstrap.jsp" %>
</body>
</html>
