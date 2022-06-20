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
		<h1>상품목록</h1><br>
		<tr>
			<th>상품번호</th>
			<th>대분류</th>
			<th>소분류</th>
			<th>상품이름</th>
			<th>해시태그</th>
			<th>가격</th>
			<th>판매여부</th>
		</tr>

		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.itemNo }</td>
				<c:choose>
					<c:when test="${dto.mcatNo == 1}">
						<td>수납</td>
					</c:when>
					<c:when test="${dto.mcatNo ==2}">
						<td>필기류</td>
					</c:when>
					<c:when test="${dto.mcatNo ==3}">
						<td>일반사무용품</td>
					</c:when>
					<c:otherwise>
						<td>화일/바인더</td>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${dto.mcatNo == 1 && dto.scatNo == 1}">
						<td>서류함</td>
					</c:when>
					<c:when test="${dto.mcatNo  == 1 && dto.scatNo == 2}">
						<td>서류꽃이/책꽃이</td>
					</c:when>
					<c:when test="${dto.mcatNo == 2 && dto.scatNo == 1}">
						<td>볼펜</td>
					</c:when>
					<c:when test="${dto.mcatNo == 2 && dto.scatNo == 2}">
						<td>연필</td>
					</c:when>
					<c:when test="${dto.mcatNo == 2 && dto.scatNo == 3}">
						<td>샤프/샤프심</td>
					</c:when>
					<c:when test="${dto.mcatNo == 2 && dto.scatNo == 4}">
						<td>형광펜</td>
					</c:when>
					<c:when test="${dto.mcatNo == 3 && dto.scatNo == 2}">
						<td>수정페이프/지우개</td>
					</c:when>
					
					<c:when test="${dto.mcatNo == 3 && dto.scatNo == 2}">
						<td>자/칼/가위</td>
					</c:when>
					<c:when test="${dto.mcatNo == 3 && dto.scatNo == 3}">
						<td>풀/접착제</td>
					</c:when>
					<c:when test="${dto.mcatNo == 3 && dto.scatNo == 3}">
						<td>테이프</td>
					</c:when>
					<c:when test="${dto.mcatNo == 4 && dto.scatNo == 1}">
						<td>클리어화일/내지</td>
					</c:when>
					<c:when test="${dto.mcatNo == 4 && dto.scatNo == 2}">
						<td>폴대화일/L홀더</td>
					</c:when>
					<c:otherwise>
						<td>바인더</td>
					</c:otherwise>
				</c:choose>

				<td><a href="${cp}/admin/item/detail?itemNo=${dto.itemNo}">${dto.itemName }</a></td>
				<td>${dto.hash }</td>
				<td>${dto.price }</td>
				<c:choose>
					<c:when test="${dto.itemDel}">
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