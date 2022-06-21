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
	<title>결제</title>
	<style type="text/css">
	
		.main{
			width: 1024px;
			height: auto;
			margin: auto;
			margin-bottom: 27px;
		}
		
		#purchaseWrap{
			width: 700px;
			margin: auto;
		}
		
		.fieldset{
			width: 400px;
			padding-top:10px;
			padding-right:20px;
			padding-bottom:10px;
			padding-left:20px;
		}
		
		.contents{
			width: 500px;
			margin: auto;
		}
		
		#purchaseBtn{
			width: 80px;
			height: 25px;
			margin-top: 10px;
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
	<div id="purchaseWrap">
	<h2>결제</h2><hr>
	<c:choose>
		<c:when test="${not empty ordNos}">
			<div class = "contents">
			<form method="post" action="${cp }/member/payment">
				<fieldset class="fieldset">
					<legend>배송지 정보</legend>
					받으시는 분<br>
					<input type="text" name = "recName" value = "${recName}"><br>
					전화 번호<br>
					<input type="text" name = "recTel" value = "${recTel}"><br>
					우편 번호<br>
					<input type="text" name = "recpostno" value = "${recpostno}"><br>
					주소<br>
					<input type="text" name = "recAdr" value = "${recAdr}"><br>
					<input type="text" name = "recAdr1" value = "${recAdr1}"><br>
					<input type="text" name = "recAdr2" value = "${recAdr2}"><br>
				</fieldset>
				<br>
				<fieldset class="fieldset">
					<c:forEach var = "i" begin = "0" end = "${ordNos.size()-1}">
					<input type="hidden" name="ordNos" value="${ordNos[i]}" id = "ordNos">
					</c:forEach>
					<legend>결제 금액</legend>
					총 주문 금액:
					<fmt:formatNumber value = "${totAmt}" type = "number"/>원<br>
					할인 금액:
					<fmt:formatNumber value = "${discAmt}" type = "number"/>원<br>
					<input type="hidden" name = "discAmt" value = "${discAmt}">
					배송비:
					<fmt:formatNumber value = "${delFee}" type = "number"/>
					<input type="hidden" name = "delFee" value = "${delFee}" >
					x <fmt:formatNumber value = "${ordNos.size()}" type = "number"/>
					= <fmt:formatNumber value = "${delFee*ordNos.size()}" type = "number"/>원
					<input type="hidden" name = "totDelFee" value = "${delFee*ordNos.size()}"> <br>
					<hr>
					결제 예정 금액<br>
					<fmt:formatNumber value="${totAmt - discAmt + delFee*ordNos.size()}" type="number"/>원
				</fieldset>
				<input type="submit" value="결제하기" id = "purchaseBtn">
			</form>
			</div>
		</c:when>
		<c:otherwise>
		<div class = "nothing">
			<h3>결제 대기 상품이 없습니다.</h3>
		</div>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>