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
		<th>상품번호</th>
		<th>대분류</th>
		<th>소분류</th>
		<th>상품이름</th>
		<th>썸네일</th>
		<th>상세이미지</th>
		<th>해시태그</th>
		<th>가격</th>
	</tr>
	
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.getItemNo() }</td>
			<td>${dto.mcatNo() }</td>
			<td>${dto.scatNo() }</td>
			<td>${dto.itemName() }</td>
			<td>${dto.tImg() }</td>
			<td>${dto.detImg() }</td>
			<td>${dto.hash() }</td>
			<td>${dto.price() }</td>
			<td>${dto.isItemDel() }</td>
	</c:forEach>
	</table>
</body>
</html>