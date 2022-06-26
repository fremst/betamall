<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link rel="stylesheet" href="${cp}/resources/css/search.css">
</style>
<body>
   <h2>상품 검색</h2>
    <div class = "itemList">
    	<div class = "searchItem">
    	<form action="/betamall/item/search">
	        <select name = "field" style = "font-size: 16px">
	            <option value = "itemName" <c:if test="${param.field=='itemName'}">selected</c:if>>상품명</option>
	            <option value = "brName" <c:if test="${param.field=='brName'}">selected</c:if>>지점명</option>
	            <option value = "mcatName" <c:if test="${param.field=='mcatName'}">selected</c:if>>대분류</option>
	        </select>
	        <input type="search" name = "keyword" value = "${param.keyword}" class = "srchText">
	        <input type="submit" value="검색" class = "srchBtn">
	        <input type="button" value="전체 보기" onclick = "location.href='${cp}/item/search'" class = "allBtn">
        </form>
    </div>
    <hr>
        <c:choose>
	        <c:when test = "${not empty iDtos}">
	        <table style = "text-align: center">
		           	<thead>
		           		<tr style = "height: 40px">
			           		<td><strong>상품 번호</strong></td>
			           		<td><strong>대분류</strong></td>
			           		<td><strong>상품 이미지</strong></td>
			           		<td><strong>상품명</strong></td>
			           		<td><strong>가  격</strong></td>
			           		<td><strong>구  매</strong></td>
			           		<td></td>
		           		</tr>
		           	</thead>
		           	<c:forEach var="iDto" items="${iDtos}">
	           			<tr>
			                <td class = "itemNo">
			                    ${iDto.itemNo}
			                </td>
			                 <td class = "mcatName">
			                    ${iDto.mcatName}
			                </td>
			                <td class = "itemImg">
			                    <a href = "${cp}/item/detail?itemNo=${iDto.itemNo}"><img src = "${cp }/resources/uploads/admin/item/${iDto.tImg}" class = "itemThumbNails"></a>
			                </td>
			                <td class = "itemName">
			                	<span class = "itemNames">
			                		<a href = "${cp}/item/detail?itemNo=${iDto.itemNo}">${iDto.itemName}</a><br><br>
			                	</span>
			                	<span class = 'itemStocks'>매장 재고:
			                		<c:choose>
					               		<c:when test="${not empty iDto.brName}">
					                		${iDto.brName} ${iDto.stkCnt}개
					               		</c:when>
					               		<c:otherwise>
					               			(입고전)<br>
					               		</c:otherwise>
				               		</c:choose>
		        				</span><br>
		                   		${iDto.hash}
		                	</td>
			                <td class = "itemPrice">
			                	<fmt:formatNumber value="${iDto.price}" type="number"/> 원
			                </td>
			                <c:choose>
				                <c:when test="${iDto.stkCnt>0}">
					                <td>
					                	<input type = "button" value="장바구니"
					                	class="cartBtn" onclick="addCart('${cp}',${not empty id},${IpOrd=='true'},${iDto.brNo},${iDto.itemNo},1,'search')">
					                	<input type = "button" value="바로구매"
					                	class="pcBtn" onclick="purchase('${cp}',${not empty id},${IpOrd=='true'},${iDto.brNo},${iDto.itemNo},1)">
					                </td>
				                </c:when>
				                <c:otherwise>
				                	<td>
				                		재고 없음
				                	</td>
			                	</c:otherwise>
			                </c:choose>
	            		</tr>
	            	</c:forEach>
	        </table>
	        </c:when>
	        <c:otherwise>
	        	<div class = "nothing">
					<h3>검색 결과가 없습니다.</h3>
				</div>
	        </c:otherwise>
       </c:choose>
    </div>
</body>
<script type="text/javascript" src="${cp}/resources/js/addCart.js"></script>
<script type="text/javascript" src="${cp}/resources/js/purchase.js"></script>
</html>