<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 관리</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/orderList.css">
</head>
<body>
<div id="orderListWrap">
    <h2 id="orderListTitle">주문 관리</h2>
	<h3>검색 조건</h3>
	<form action="${cp}/admin/order/list" method="get">
		<input type="checkbox" id="ordSta" name="ordStaChk" checked="checked">
		<label for="ordSta">주문상태</label>
			<select name="ordSta">
				<option value="결제완료" >결제완료</option>
				<option value="발송전"<c:if test="${param.ordSta=='발송전'}">selected</c:if>>발송전</option>
				<option value="배송중"<c:if test="${param.ordSta=='배송중'}">selected</c:if>>배송중</option>
				<option value="결제대기"<c:if test="${param.ordSta=='결제대기'}">selected</c:if>>결제대기</option>
				<option value="배송완료"<c:if test="${param.ordSta=='배송완료'}">selected</c:if>>배송완료</option>
				<option value="구매확정"<c:if test="${param.ordSta=='구매확정'}">selected</c:if>>구매확정</option>
				<option value="결제취소"<c:if test="${param.ordSta=='결제취소'}">selected</c:if>>결제취소</option>
			</select>
		<input type="submit" value = "검색">
		<input type="button" value = "전체 보기" onclick = "location.href='${cp}/admin/order/list'">
	</form>
    <br>
    <h3>${brName} 주문 목록</h3>
    <div id="orderList">
    	<c:choose>
    		<c:when test="${not empty orderList}">
    		<table id="orderTable">
            <tr>
                <th class="orderInfo_header">주문번호</th>
                <th class="orderInfo_header">상품번호</th>
                <th class="orderInfo_header">상품명</th>
                <th class="orderInfo_header">수량</th>
                <th class="orderInfo_header">총액</th>
                <th class="orderInfo_header">주문일자</th>
                <th class="orderInfo_header">주문상태</th>
                <th class="orderInfo_header">관리</th>
                <th class="orderInfo_header">아이디</th>
                <th class="orderInfo_header">수신인</th>
                <th class="orderInfo_header">연락처</th>
                <th class="orderInfo_header">배송지</th>
            </tr>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td class="orderInfo">${order.ordNo}</td>
                    <td class="orderInfo">${order.itemNo}</td>
                    <td class="orderInfo">${order.itemName}</td>
                    <td class="orderInfo">${order.ordCnt}</td>
                    <td class="orderInfo">
                    	<fmt:formatNumber value="${order.price * order.ordCnt}" type="number"/>
                    </td>
                    <td class="orderInfo">${order.ordDate}</td>
                    <td class="orderInfo">${order.ordSta}</td>
                	<td class="orderInfo">
                    	<c:choose>
                    		<c:when test="${order.ordSta=='결제대기'}">
                    			<form action="${cp}/admin/order/list" method="post">
                    				<input type="hidden" value = "${order.ordNo}" name="ordNo">
                    				<input type="hidden" value = "결제취소" name="ordSta">
                    				<input type="submit" value = "주문취소">
                   				</form>
                    		</c:when>
                    		<c:when test="${order.ordSta=='결제완료'}">
                    			<form action="${cp}/admin/order/list" method="post">
                    				<input type="hidden" value = "${order.ordNo}" name="ordNo">
                    				<input type="hidden" value = "발송전" name="ordSta">
                    				<input type="submit" value = "주문확인">
                   				</form>
                    		</c:when>
                    		<c:when test="${order.ordSta=='발송전'}">
                    		    <form action="${cp}/admin/order/list" method="post">
                    				<input type="hidden" value = "${order.ordNo}" name="ordNo">
                    				<input type="hidden" value = "배송중" name="ordSta">
                    				<input type="submit" value = "발송완료">
                   				</form>
                    		</c:when>
                    		<c:when test="${order.ordSta=='배송중'}">
                    		    <form action="${cp}/admin/order/list" method="post">
                    				<input type="hidden" value = "${order.ordNo}" name="ordNo">
                    				<input type="hidden" value = "배송완료" name="ordSta">
                    				<input type="submit" value = "배송완료">
                   				</form>
                    		</c:when>
                    	</c:choose>
                    </td>
                    <td class="orderInfo">${order.mbrId}</td>
                    <td class="orderInfo">${order.ordName}</td>
                    <td class="orderInfo">${order.ordTel}</td>
                    <td class="orderInfo">${order.ordAdr}</td>
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
</div>
</body>
</html>