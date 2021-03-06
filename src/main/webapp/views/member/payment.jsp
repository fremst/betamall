<%@page import="com.betamall.dto.ItemDto" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${cp}/resources/css/payment.css">
</head>
<body>
	<div id="paymentWrap">
		<h2>결제</h2><hr>
		<c:choose>
			<c:when test="${not empty ordNos}">
				<form method="post" action="${cp }/member/payment">
				<div class="contents">
					<div class="itemList">
						<fieldset class="ordListField">
							<legend><span class="legendText">주문 목록</span></legend>
							<table id="orderList">
									<tr>
										<th>상품명</th>
										<th>정  가</th>
										<th>수  량</th>
										<th>총  액</th>
										<th>주문지점</th>
										<th></th>
									</tr>
									<c:forEach var="orderSheet" items="${orderSheets}">
										<tr>
											<td class="itemName">
												<a href="${cp}/item/detail?itemNo=${orderSheet.itemNo}">${orderSheet.itemName}</a>
											</td>
											<td class="itemPrice">
												<fmt:formatNumber value="${orderSheet.price}" type="number"/> 원
											</td>
											<td class="itemCnt">
												<fmt:formatNumber value="${orderSheet.ordCnt}" type="number" /> 개
											</td>
											<td class="totAmt">
												<fmt:formatNumber value="${orderSheet.price*orderSheet.ordCnt}" type="number" /> 원
											</td>
											<td class="brName">
												${orderSheet.brName}
											</td>
										</tr>
									</c:forEach>
							</table>
						</fieldset>
					</div>
						<fieldset class="delInfoField">
							<legend><span class="legendText">배송지 정보</span></legend>
							받으시는 분<br>
							<input type="text" id="recName" name="recName" value="${recName}" class="inputText"><br>
							전화 번호<br>
							<input type="text" id="recTel" name="recTel" value="${recTel}" class="inputText"><br>
							주소<br>
							<input type="text" id="postcode" name="recpostno" value="${recpostno}" placeholder="우편번호" class="inputText">
				            <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="postBtn"><br>
				            <input type="text" id="address" name = "recAdr" value="${recAdr}" placeholder="주소" class="inputText">
				            <input type="text" id="detailAddress" name="recAdr1" value="${recAdr1}" placeholder="상세주소" class="inputText">
							<input type="text" id="extraAddress" name="recAdr2" value="${recAdr2}" placeholder="참고항목" class="inputText">
						</fieldset>
						<fieldset class="pmtInfoField">
							<c:forEach var = "i" begin = "0" end = "${ordNos.size()-1}">
								<input type="hidden" name="ordNos" value="${ordNos[i]}" id = "ordNos">
							</c:forEach>
							<legend><span class="legendText">결제 금액</span></legend>
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
						
						<div class = "btnArea">
							<input type="button" value="결제취소" class="cancelBtn" onclick="location.href='${cp}/cancelPurchase'">
							<input type="submit" value="결제하기" onclick="return validate()" class="purchaseBtn">
						</div>
				</div>
			</form>
			</c:when>
			<c:otherwise>
				<div class = "nothing">
					<h3>결제 대기 상품이 없습니다.</h3>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function (data) {
	            var addr = '';
	            var extraAddr = '';

	            if (data.userSelectedType === 'R') {
	                addr = data.roadAddress;
	            } else {
	                addr = data.jibunAddress;
	            }

	            if (data.userSelectedType === 'R') {
	                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
	                    extraAddr += data.bname;
	                }
	                if (data.buildingName !== '' && data.apartment === 'Y') {
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                if (extraAddr !== '') {
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                document.getElementById("extraAddress").value = extraAddr;

	            } else {
	                document.getElementById("extraAddress").value = '';
	            }
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("address").value = addr;
	            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	
	function validate(){
		
		let recName = document.getElementById('recName').value;
		let recTel = document.getElementById('recTel').value;
		let postcode = document.getElementById('postcode').value;
		let address = document.getElementById('address').value;
		let detailAddress = document.getElementById('detailAddress').value;
		
		if(!recName){
			alert('받으시는 분 성함을 입력해주세요.');
			return false;
		}else if(!recTel){
			alert('전화번호를 입력해주세요.');
			return false;
		}else if(!(/[0-9]{9,13}/).test(recTel)){
			alert('전화번호를 정확히 입력해주세요.');
			return false;
		}else if(!postcode){
			alert('우편 번호를 입력해주세요.');
			return false;
		}else if(!address){
			alert('주소를 입력해주세요.');
			return false;
		}else if(!detailAddress){
			alert('상세 주소를 입력해주세요.');
			return false;
		}else{
			return true;
		}
	}
</script>
</html>