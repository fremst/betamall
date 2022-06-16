<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<c:set var="cp" value="${pageContext.request.contextPath }"/>
	<table border="1" width="900">
		<tr>
			<th>상품번호</th>
			<th>대분류</th>
			<th>소분류</th>
			<th>상품이름</th>
			<th>해시태그</th>
			<th>가격</th>
		</tr>
	
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.itemNo }</td>
				<td>${dto.mcatNo }</td>
				<td>${dto.scatNo }</td>
				<td><a href="">${dto.itemName }</a></td>
				<td>${dto.hash }</td>
				<td>${dto.price }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<a href="${cp }/admin/item/list?pageNum=${i}">
					<span style="color:red">${i }</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="${cp}/admin/item/list?pageNum=${i }">
					<span style="color:gray">${i }</span>
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<a href="${cp }/admin/item/insert">상품등록</a>
</body>
</html>