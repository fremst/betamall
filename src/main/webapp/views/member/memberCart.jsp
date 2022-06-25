<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@page import="com.betamall.dto.ItemDto" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link rel="stylesheet" href="${cp}/resources/css/mbrCart.css">
</head>
<body>
<div id="cartWrap">
<h2>장바구니</h2>
<hr>
	<c:choose>
		<c:when test="${not empty ordBrList }">
			<form method="post" action="${cp }/member/cart">
				<div id="itemListArea">
					<c:set var="oipbInd" value="0" />
					<c:forEach begin="0" end="${ordBrList.size()-1}" varStatus="brSts">
						<c:if
							test="${brSts.first or (ordBrList[brSts.index].brNo != ordBrList[brSts.index-1].brNo)}">
							<c:set var="oipbInd" value="${oipbInd+1}" />
							<fieldset class="itemInfoField">
								<legend>
									<span class="legendText">${ordBrList[brSts.index].getBrName()}</span>
								</legend>
								<input type="hidden" value="${ordBrList[brSts.index].getBrNo()}" name="brNo">
								<table id="orderList">
									<tr>
										<th>상품 번호</th>
										<th>상품 이미지</th>
										<th>상품명</th>
										<th>정 가</th>
										<th>수 량</th>
										<th>총 액</th>
										<th></th>
									</tr>
									<c:forEach begin="${brSts.index}" end="${brSts.index+ordItemPerBr[oipbInd-1]-1}" varStatus="itemSts">
										<c:if test="${itemSts.first or (ordBrList[itemSts.index].brNo == ordBrList[itemSts.index-1].brNo)}">
											<tr>
												<td class="itemNo">
													${ordItemList[itemSts.index].itemNo}
													<input type="hidden" value="${ordItemList[itemSts.index].itemNo}" name="itemNofbr${ordBrList[brSts.index].getBrNo()}">
												</td>
												<td class="itemImg">
													<a href="${cp}/item/detail?itemNo=${ordItemList[itemSts.index].itemNo}">
														<img src="${cp }/resources/uploads/admin/item/${ordItemList[itemSts.index].tImg}" class="itemThumbNail">
													</a>
												</td>
												<td class="itemName">
													<a href="${cp}/item/detail?itemNo=${ordItemList[itemSts.index].itemNo}">${ordItemList[itemSts.index].itemName}</a><br><br>
												</td>
												<td class="itemPrice">
													<fmt:formatNumber value="${ordItemList[itemSts.index].price}" type="number" /> 원
												</td>
												<td class="itemCnt">
													<a href="${cp}/member/addcart?brNo=${ordBrList[itemSts.index].brNo}&itemNo=${ordItemList[itemSts.index].itemNo}&ordCnt=-1&status=cart">
														<b>－</b>
													</a>
													<fmt:formatNumber value="${ordCntList[itemSts.index]}" type="number" /> 개
													<input type="hidden"
														value="${ordCntList[itemSts.index]}"
														name="cntfbr${ordBrList[brSts.index].getBrNo()}">
													<a href="${cp}/member/addcart?brNo=${ordBrList[itemSts.index].brNo}&itemNo=${ordItemList[itemSts.index].itemNo}&ordCnt=1&status=cart">
														<b>＋</b>
													</a>
												</td>
												<td class="itemCnt">
													<fmt:formatNumber value="${ordItemList[itemSts.index].price*ordCntList[itemSts.index]}" type="number" /> 원
												</td>
												<td class="totAmt">
													<a href="${cp}/member/addcart?brNo=${ordBrList[itemSts.index].brNo}&itemNo=${ordItemList[itemSts.index].itemNo}&ordCnt=0&status=cart">
													 Ⅹ
													</a>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</fieldset>
						</c:if>
					</c:forEach>
				</div>
				<div id="pmtInfoArea">
					<input type="hidden" name="mbrNo" value="${mbrNo}" id="mbrNo"><br>
					<fieldset class="pmtInfoField">
						<legend>
							<span class="legendText">결제 금액</span>
						</legend>
						총 주문 금액: <fmt:formatNumber value="${totAmt}" type="number" /> 원<br>
						<input type="hidden" value="${totAmt}" name="totAmt">
						할인 금액: <fmt:formatNumber value="${discAmt}" type="number" /> 원<br>
						<input type="hidden" value="${discAmt}" name="discAmt">
						배송비: <fmt:formatNumber value="${delFee}" type="number"/>
						 x <fmt:formatNumber value="${ordItemPerBr.size()}" type="number"/>
						 = <fmt:formatNumber value="${delFee*ordItemPerBr.size()}" type="number" /> 원
						<input type="hidden" value="${delFee*ordItemPerBr.size()}"name="totDelFee"> <br>
						<hr>
						결제 예정 금액: <fmt:formatNumber value="${totAmt - discAmt + delFee*ordItemPerBr.size()}" type="number"/> 원
					</fieldset>
					<div class="btnArea">
						<input type="button" value="계속 쇼핑하기" class="cancelBtn" onclick="history.back()">
						<input type="submit" value="주문하기" class="orderBtn">
					</div>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="nothing">
				<h3>주문 상품이 없습니다.</h3>
			</div>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>