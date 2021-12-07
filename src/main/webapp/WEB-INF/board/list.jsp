<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

</head>
<body>
<div class="container-fluid p-2">
    <%@include file="../top.jsp"%>

    <h1>게시판</h1>
    <span>
            <a href="/board/register">
                <button type="button" class="btn btn-outline-primary">등록
                </button>
            </a>
        </span>

    <form action="/board/list" method="get" id="searchForm">
        <div class="input-group">
            <input type="hidden" name="page" value="1">
            <div class="input-group-prepend">
                <select class="custom-select" name="type">
                    <option selected="${pageRequestDTO.type == null}">-------</option>
                    <option value="t" selected="${pageRequestDTO.type =='t'}">제목</option>
                    <option value="t" selected="${pageRequestDTO.type =='c'}">내용</option>
                    <option value="tc" selected="${pageRequestDTO.type =='tc'}">제목 + 내용</option>
                </select>
            </div>
            <input class="form-control" name="keyword" value="${pageRequestDTO.keyword}">
            <div class="input-group-append" id="button-addon4">
                <button class="btn btn-outline-secondary btn-search" type="button">Search</button>
                <button class="btn btn-outline-secondary btn-clear" type="button">Clear</button>
            </div>
        </div>
    </form>
    <script inline="javascript">

        var msg = [[${msg}]];

        console.log(msg);

        if(msg){
            $(".modal").modal();
        }
        var searchForm = $("#searchForm");

        $("#searchForm.btn-search").on("click", function(e) {

            if (!searchForm.find("option:selected").val()) {
                alert("검색 종류를 선택하세요");
                return false;
            }

            if (!searchForm.find("input[name='keyword']").val()) {
                alert("키워드를 입력하세요");
                return false;
            }
        });

        $('searchForm.btn-clear').click(function(e){

            searchForm.empty().submit();

        });


    </script>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">등록일</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${empty boardList}">
                <tr><td colspan="4" align="center">작성된 게시물이 없습니다.</td></tr>
            </c:if>
            <c:if test="${!empty boardList}">
                <c:forEach var="board" items="${boardList.content}">
                    <tr>
                        <td> <a href="/board/read?bno=${board.bno}"> <c:out value="${board.bno}"/> </a>
                        <td><c:out value="${board.title}"/></td>
                        <td><c:out value="${board.writer}"/></td>
                        <td><fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" type="both"/>
                            <fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd"/></td>
                        <td><c:out value="${board.hit}"/> </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
<!--
    <ul class="pagination h-100 justify-content-center align-items-center">

        <li class="page-item " if="${result.prev}">
            <a class="page-link" href="board/list(page= ${result.start -1},
                    type=${pageRequestDTO.type} ,
                    keyword = ${pageRequestDTO.keyword} ) " tabindex="-1">Previous</a>
        </li>

        <li class=" 'page-item ' + ${result.page == page?'active':''} " each="page: ${result.pageList}">
            <a class="page-link" href="/board/list(page = ${page} ,
                   type=${pageRequestDTO.type} ,
                   keyword = ${pageRequestDTO.keyword}  )">
                [[${page}]]
            </a>
        </li>

        <li class="page-item" if="${result.next}">
            <a class="page-link" href="/board/list(page= ${result.end + 1} ,
                    type=${pageRequestDTO.type} ,
                    keyword = ${pageRequestDTO.keyword} )">Next</a>
        </li>

    </ul>


    <div class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Modal body text goes here.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
-->
</div>
<%@ include file="../bootstrap.jsp" %>
</body>
</html>
