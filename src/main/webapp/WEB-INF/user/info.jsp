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
    <form id="user" action="/user/info/modify" method="post">
    <table>
        <tr>
            <td>
                <input hidden name="userId" value="${userDTO.userId}">
                <label>이메일 주소</label>
            </td>
            <td>
                <input type="text" name="email" value="${userDTO.email}" readonly><br>
            </td>
        </tr>
        <tr>
            <td><label> 이름 </label></td>
            <td>
                <input type="text" name="userName" value="${userDTO.userName}"><br>
            </td>
        </tr>
        <tr>
            <td><label> 변경할 비밀번호 </label></td>
            <td><input type="password" name="password" placeholder="변경할 비밀번호" required><br></td>
        </tr>
        <tr>
            <td>주소</td>
            <td><input type="text" id="postcode" name="postcode" value="${userDTO.postcode}" placeholder="우편번호">
                <button type="button" onclick="kakaoPostcode()">우편번호 찾기</button></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="text" id="address" name="address" value="${userDTO.address}" readonly required></td>
            <td><input type="text" name="detailAddress" value="${userDTO.detailAddress}" placeholder="상세 주소"></td>
        </tr>

    </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <button type="submit" class="btn btn-primary">변경</button>
    </form>
</div>

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
