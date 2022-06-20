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
	<table border="1" width="1000">
		<h1>상품목록</h1>
		<br>
		<tr>
			<th>상품번호</th>
			<th>대분류</th>
			<th>소분류</th>
			<th>상품이름</th>
			<th>해시태그</th>
			<th>가격</th>
			<th>판매여부</th>
		</tr>

		<c:forEach var="itemDto" items="${list}">
			<tr>
				<td>${itemDto.itemNo}</td>
				<c:forEach var="mcatDto" items="${mcatList}">
					<c:if test="${itemDto.mcatNo == mcatDto.mcatNo}">
						<td>${mcatDto.mcatName }</td>
						<c:forEach var="scatDto" items="${scatList}">
						<c:if test="${(itemDto.mcatNo == scatDto.mcatNo) && (itemDto.scatNo == scatDto.scatNo)}">
							<td>${scatDto.scatName}</td>
						</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
				<td><a href="${cp}/admin/item/detail?itemNo=${itemDto.itemNo}">${itemDto.itemName}</a></td>
				<td>${itemDto.hash }</td>
				<td>${itemDto.price }</td>
				<c:choose>
					<c:when test="${itemDto.itemDel}">
						<td>판매중단</td>
					</c:when>
					<c:otherwise>
						<td>판매중</td>
					</c:otherwise>
				</c:choose>
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