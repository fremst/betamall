<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<c:choose>
	<c:when test="${not empty list }">
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
				<th colspan = 2>재고 관리</th>
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
					<td> <fmt:formatNumber value="${itemDto.price }" type="number" /> 원 </td>
					<c:choose>
						<c:when test="${itemDto.itemDel}">
							<td>판매중단</td>
						</c:when>
						<c:otherwise>
							<td>판매중</td>
						</c:otherwise>
					</c:choose>
					<td>
					<c:choose>
						<c:when test="${role == 'admin0' }">
							-
						</c:when>
						<c:otherwise>
							<c:forEach var="stockDto" items="${stockList}">									
								<c:if test="${itemDto.itemNo == stockDto.itemNo}">
									${stockDto.stkCnt}								
								</c:if>
							</c:forEach>
								<td><a href="${cp }/admin/stock/update?itemNo=${itemDto.itemNo}">수정</a></td>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<div>
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<c:choose>
					<c:when test="${i==pageNum }">
						<a href="${cp }/admin/item/list?pageNum=${i }&field=${field}&keyword=${keyword}">
						<span style="color:red">${i }</span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${cp}/admin/item/list?pageNum=${i }&field=${field}&keyword=${keyword}">
						<span style="color:gray">${i }</span>
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<c:choose>
			<c:when test="${role == 'admin0' }">
				<a href="${cp }/admin/item/insert">상품등록</a>
				<a href="${cp }/admin/item/list">전체목록</a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/admin/item/list">전체목록</a>
			</c:otherwise>
		</c:choose>
			<div>
				<form method="post" action="${cp }/admin/item/list">
					<select name="field">
						<option value="mcatName" <c:if test="${field=='mcatName' }">selected</c:if>>대분류</option>
		            	<option value="scatName" <c:if test="${field=='scatName' }">selected</c:if>>소분류</option>
						<option value="itemName" <c:if test="${field=='itemName' }">selected</c:if>>이름</option>
					</select>
					<input type="text" name="keyword" value="${keyword }">
					<input type="submit" value="검색">
				</form>
				
			</div>
		</c:when>
		<c:otherwise>
			<div class = "nothing">
				<h3>검색 항목이 없습니다.</h3>
				<a href="${cp }/admin/item/list">목록으로 돌아가기</a>
			</div>
		</c:otherwise>
	</c:choose>
	<div>
	</div>
</body>
</html>