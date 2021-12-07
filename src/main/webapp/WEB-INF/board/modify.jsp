<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top.jsp"%>
    <h1 class="mt-4">게시글 수정 페이지</h1>

    <form action="/board/modify" method="post">

        <div class="form-group">
            <label >Bno</label>
            <input type="text" class="form-control" name="bno" value="${dto.bno}" readonly >
        </div>

        <div class="form-group">
            <label >Title</label>>
            <input type="text" class="form-control" name="title" value="${dto.title}" >
        </div>
        <div class="form-group">
            <label >Writer</label>
            <input type="text" class="form-control" name="writer" value="${dto.writer}" readonly >
        </div>
        <div class="form-group">
            <label >Content</label>
            <textarea class="form-control" rows="5" name="content">[[${dto.content}]]</textarea>
        </div>
        <div class="form-group">
            <label >RegDate</label>
            <fmt:parseDate value="${dto.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseRegDateTime" type="both"/>
            <input type="text" class="form-control" name="regDate" value="${parseRegDateTime}" readonly>
        </div>
        <div class="form-group">
            <label >ModDate</label>
            <fmt:parseDate value="${dto.modDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseModDateTime" type="both"/>
            <input type="text" class="form-control" name="modDate" value="${parseModDateTime}" readonly>
        </div>
        <button type="button" class="btn btn-primary modifyBtn">Modify</button>

        <button type="button" class="btn btn-info listBtn">List</button>

        <button type="button" class="btn btn-danger removeBtn">Remove</button>
        <script>

            var actionForm = $("form"); //form 태그 객체

            $(".removeBtn").click(function(){

                actionForm
                    .attr("action", "/board/remove")
                    .attr("method","post");

                actionForm.submit();

            });

            $(".modifyBtn").click(function() {

                if(!confirm("수정하시겠습니까?")){
                    return ;
                }

                actionForm
                    .attr("action", "/board/modify")
                    .attr("method","post")
                    .submit();
            });

            $(".listBtn").click(function() {

                //var pageInfo = $("input[name='page']");
                var page = $("input[name='page']");
                var type = $("input[name='type']");
                var keyword = $("input[name='keyword']");

                actionForm.empty(); //form 태그의 모든 내용을 지우고

                actionForm.append(page);
                actionForm.append(type);
                actionForm.append(keyword);


                actionForm
                    .attr("action", "/board/list")
                    .attr("method","get");

                actionForm.submit();

            });

        </script>
    </form>

</div>
    <%@ include file="../bootstrap.jsp" %>
</body>
</html>
