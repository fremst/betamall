<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@page import="com.betamall.dto.ItemDto" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>장바구니</title>
	<style type="text/css">
	
		.main{
			width: 1024px;
			height: auto;
			margin: auto;
			margin-bottom: 27px;
		}
		
		.itemThumbNails {
			width: 100px;
		}
		.itemList {
			width: 1050px;
			margin: auto;
		}
		.itemNo{
		width: 80px;
		}
		.itemImg{
			width: 200px;
		}
		.itemName{
			width: 600px;
			text-align: left;
		}
		.itemPrice{
			width: 100px;
		}
		.purchase{
			width: 100px;
		}
	    .itemThumbNails{
	        width: 150px;
	        height: 150px;
	    }
	    .nothing{
	    	height: 768px;
	    	vertical-align: center;
	    	padding-top: 200px;
	    	text-align: center;
    	}
	</style>
</head>

<body>
	<h2>장바구니</h2>
	<hr>
	<c:choose>
		<c:when test="${not empty ordBrList }">
			<form method="post" action="${cp }/member/cart">
				<div class="itemList">
					<c:set var = "oipbInd" value = "0"/>
					<c:forEach begin="0" end = "${ordBrList.size()-1}" varStatus="brSts">
						<c:if test="${brSts.first or (ordBrList[brSts.index].brNo != ordBrList[brSts.index-1].brNo)}">
							<c:set var = "oipbInd" value = "${oipbInd+1}"/>
							<fieldset id="fieldset">
								<legend>${ordBrList[brSts.index].getBrName()}</legend>
								<input type="hidden" value="${ordBrList[brSts.index].getBrNo()}" name="brNo">
								<table align="center" style="text-align: center">
									<tbody id="orderList">
										<tr>
											<th>상품 번호</th>
											<th>상품 이미지</th>
											<th>상품명</th>
											<th>정가</th>
											<th>수량</th>
										</tr>
										<c:forEach begin = "${brSts.index}" end = "${brSts.index+ordItemPerBr[oipbInd-1]-1}" varStatus="itemSts">
											<c:if test = "${itemSts.first or (ordBrList[itemSts.index].brNo == ordBrList[itemSts.index-1].brNo)}">
												<tr>
													<td class="itemNo">
														${ordItemList[itemSts.index].itemNo}
														<input type="hidden" value="${ordItemList[itemSts.index].itemNo}" name="itemNofbr${ordBrList[brSts.index].getBrNo()}">
													</td>
													<td class="itemImg">
														<a href="${cp}/item/detail?itemNo=${ordItemList[itemSts.index].itemNo}">
															<img src="${cp }/resources/uploads/admin/item/${ordItemList[itemSts.index].tImg}" class="itemThumbNails">
														</a>
													</td>
													<td class="itemName">
														<a href="${cp}/item/detail?itemNo=${ordItemList[itemSts.index].itemNo}">${ordItemList[itemSts.index].itemName}</a><br><br>
													</td>
													<td class="itemPrice">
														<fmt:formatNumber value="${ordItemList[itemSts.index].price}" type="number"/> 원
													</td>
													<td>
														<fmt:formatNumber value="${ordCntList[itemSts.index]}" type="number" /> 개
														<input type="hidden" value="${ordCntList[itemSts.index]}" name="cntfbr${ordBrList[brSts.index].getBrNo()}">
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</fieldset>
						</c:if>
					</c:forEach>
					<input type="hidden" name="mbrNo" value="${mbrNo}" id="mbrNo"><br>
					<fieldset id="fieldset">
						<legend>결제 금액</legend>
						총 주문 금액:
						<fmt:formatNumber value="${totAmt}" type="number"/>원<br>
						<input type="hidden" value="${totAmt}" name="totAmt">
						할인 금액:
						<fmt:formatNumber value="${discAmt}" type="number"/>원<br>
						<input type="hidden" value="${discAmt}" name="discAmt">
						배송비:
						<fmt:formatNumber value="${delFee}" type="number"/>
						x <fmt:formatNumber value="${ordItemPerBr.size()}" type="number"/>
						= <fmt:formatNumber value="${delFee*ordItemPerBr.size()}" type="number"/>원
						<input type="hidden" value="${delFee*ordItemPerBr.size()}" name="totDelFee"> <br>
						<hr>
						결제 예정 금액<br>
						<fmt:formatNumber value="${totAmt - discAmt + delFee*ordItemPerBr.size()}" type="number"/>원
					</fieldset>
					<input type="submit" value="구매하기">
				</div>
			</form>
		</c:when>
		<c:otherwise>
		<div class = "nothing">
			<h3>주문 상품이 없습니다.</h3>
		</div>
		</c:otherwise>
	</c:choose>
</body>
</html>