<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="side">
        <h2>고객센터</h2>
        <ul>
            <li><a href="${cp }/board/list">ㆍ공지사항/이벤트</a></li>
            <li><a href="${cp }/board/faqlist">ㆍFAQ</a></li>
            <li><a href="${cp }/board/qnalist">ㆍQ&A</a></li>
        </ul>
    </div>
	<table border="1" width="750">
		<tr>
			<th>카테고리</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.qnaCat }</td>
				<td><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}">${dto.qnaTitle }</a></td>
				<td>임시</td> <!-- 회원 작성자 추가하기 + 작성자로 검색하기 추가 -->
				<td>${dto.qnaWdate }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<a href="${cp }/board/qnalist?pageNum=${i}&field=${field}&keyword=${keyword}">
					<span style="color:red">${i }</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="${cp}/board/qnalist?pageNum=${i }&field=${field}&keyword=${keyword}">
					<span style="color:gray">${i }</span>
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<div>
		<form method="post" action="${cp }/board/qnalist">
			<select name="field">
				<option value="brdCat" <c:if test="${field=='brdCat' }">selected</c:if> >카테고리</option>
				<option value="brdTitle" <c:if test="${field=='brdTitle' }">selected</c:if>>제목</option>
			</select>
			<input type="text" name="keyword" value="${keyword }">
			<input type="submit" value="검색">
		</form>
	</div>
	<button onclick="location.href='${cp }/board/qnainsert'">게시글 작성</button>
	<button onclick="location.href='${cp }/board/qnalist'">전체글 보기</button>
	<button onclick="location.href='${cp }/home'">Home</button>
</body>
</html>