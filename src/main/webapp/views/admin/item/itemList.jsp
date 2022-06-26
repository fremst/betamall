<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <link rel="stylesheet" href="${cp}/resources/css/itemList.css">
</head>
<body>
	<c:choose>
	<c:when test="${not empty list }">
	<div id="itemListWrap">
			<h1 id="itemListTitle">상품목록</h1>
			<div id="itemList">
			<table id="itemTable">
				<tr>
					<th class="itemInfo_header" >상품번호</th>
					<th class="itemInfo_header" >대분류</th>
					<th class="itemInfo_header" >소분류</th>
					<th class="itemInfo_header" >상품이름</th>
					<th class="itemInfo_header" >해시태그</th>
					<th class="itemInfo_header" >가격</th>
					<th class="itemInfo_header" >판매여부</th>
					<th class="itemInfo_header" colspan = 2>재고 관리</th>
				</tr>
		
				<c:forEach var="itemDto" items="${list}">
					<tr>
						<td class="itemInfo">${itemDto.itemNo}</td>
						<c:forEach var="mcatDto" items="${mcatList}">
							<c:if test="${itemDto.mcatNo == mcatDto.mcatNo}">
								<td class="itemInfo">${mcatDto.mcatName }</td>
								<c:forEach var="scatDto" items="${scatList}">
								<c:if test="${(itemDto.mcatNo == scatDto.mcatNo) && (itemDto.scatNo == scatDto.scatNo)}">
									<td class="itemInfo">${scatDto.scatName}</td>
								</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<td class="itemInfo"><a href="${cp}/admin/item/detail?itemNo=${itemDto.itemNo}">${itemDto.itemName}</a></td>
						<td class="itemInfo">${itemDto.hash }</td>
						<td class="itemInfo"> <fmt:formatNumber value="${itemDto.price }" type="number" /> 원 </td>
						<c:choose>
							<c:when test="${itemDto.itemDel}">
								<td class="itemInfo">판매중단</td>
							</c:when>
							<c:otherwise>
								<td class="itemInfo">판매중</td>
							</c:otherwise>
						</c:choose>
						<td class="itemInfo">
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
									<td class="itemInfo"><a href="${cp }/admin/stock/update?itemNo=${itemDto.itemNo}">수정</a></td>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
			<div id="itemListNum">
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a href="${cp }/admin/item/list?pageNum=${i }&field=${field}&keyword=${keyword}">
							<span id="SrowNum">${i }</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${cp}/admin/item/list?pageNum=${i }&field=${field}&keyword=${keyword}">
							<span id="rowNum">${i }</span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
				<div id="itemPageNav">
					<form method="post" action="${cp }/admin/item/list" id="searchItem">
						<select name="field">
							<option value="mcatName" <c:if test="${field=='mcatName' }">selected</c:if>>대분류</option>
			            	<option value="scatName" <c:if test="${field=='scatName' }">selected</c:if>>소분류</option>
							<option value="itemName" <c:if test="${field=='itemName' }">selected</c:if>>이름</option>
						</select>
						<input type="text" name="keyword" value="${keyword }" id="itemList_search">
						<input type="submit" value="검색" id="itemList_search_btn">
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
		<div id="listAll">
		<c:choose>
			<c:when test="${role == 'admin0' }">
				<a href="${cp }/admin/item/insert">상품등록</a> &nbsp;|
				<a href="${cp }/admin/item/list">전체목록</a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/admin/item/list">전체목록</a>
			</c:otherwise>
		</c:choose>
		</div>
</body>
</html>