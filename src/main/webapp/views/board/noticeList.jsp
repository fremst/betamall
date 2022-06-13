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
	<c:set var="cp" value="${pageContext.request.contextPath }"/>
	<table border="1" width="750">
		<tr>
			<th>글번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.getBrdNo() }</td>
				<td>${dto.brdCat }</td>
				<td>${dto.brdTitle }</td>
				<td>임시</td><%-- managerDao merge 후 수정--%>
				<td>${dto.brdWdate }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<a href="${cp }/board/list?pageNum=${i}&field=${field}&keyword=${keyword}">
					<span style="color:red">${i }</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="${cp}/board/list?pageNum=${i }&field=${field}&keyword=${keyword}">
					<span style="color:gray">${i }</span>
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<div>
		<form method="post" action="${cp }/board/list">
			<select name="field">
				<option value="brdCat" <c:if test="${field=='brdCat' }">selected</c:if> >카테고리</option>
				<option value="brdTitle" <c:if test="${field=='brdTitle' }">selected</c:if>>제목</option>
			</select>
			<input type="text" name="keyword" value="${keyword }">
			<input type="submit" value="검색">
		</form>
	</div>
	<a href="${pageContext.request.contextPath }/board/insert">게시글 작성</a>
	<a href="${cp }/board/list">전체글보기</a> | <a href="${cp }/home">home</a>
</body>
</html>