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
	</style>
</head>
<body>
	<h2>결제</h2>
	<c:choose>
		<c:when test="${not empty ordNos}">
			<form method="post" action="${cp }/member/payment">
				<fieldset id="fieldset">
					<legend>배송지 정보</legend>
					받으시는 분<br>
					<input type="text" name = "recName" value = "${recName}" readonly = "readonly"><br>
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
				<fieldset id="fieldset">
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
					x <fmt:formatNumber value = "${ordItemPerBr.size()}" type = "number"/>
					= <fmt:formatNumber value = "${delFee*ordItemPerBr.size()}" type = "number"/>원
					<input type="hidden" name = "totDelFee" value = "${delFee*ordItemPerBr.size()}"> <br>
					<hr>
					결제 예정 금액<br>
					<fmt:formatNumber value="${totAmt - discAmt + delFee*ordItemPerBr.size()}" type="number"/>원
				</fieldset>
				<input type="submit" value="결제하기">
			</form>
		</c:when>
		<c:otherwise>
			진행중인 결제가 없습니다.
		</c:otherwise>
	</c:choose>
</body>
</html>