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
	    
   	    .btns{
			width: 400px;
			margin: auto;
			margin-top: 10px;
			text-align: center;
	    }
	    
	  	.purchaseBtn{
			width: 100px;
			height: 35px;
			margin: 10px;
			color: aliceblue;
			font-size: 15px;
			background-color: #2C8FED;
			border: 1px;
			border-radius: 5px;
		}
		
		.cancelBtn{
			width: 100px;
			height: 35px;
			margin: 10px;
			color: black;
			font-size: 15px;
			background-color: #4B484B3A;
			border: 1px;
			border-radius: 5px;
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
				
				<%--
				<div class="itemList">
					<c:set var = "oipbInd" value = "0"/>
					<c:forEach begin="0" end = "${ordBrList.size()-1}" varStatus="brSts">
						<c:if test="${brSts.first or (ordBrList[brSts.index].brNo != ordBrList[brSts.index-1].brNo)}">
							<c:set var = "oipbInd" value = "${oipbInd+1}"/>
							<fieldset class="fieldset">
								<legend><h3>${ordBrList[brSts.index].getBrName()}</h3></legend>
								<input type="hidden" value="${ordBrList[brSts.index].getBrNo()}" name="brNo">
								<table align="center" style="text-align: center">
									<tbody id="orderList">
										<tr>
											<th>상품 번호</th>
											<th>상품 이미지</th>
											<th>상품명</th>
											<th>정  가</th>
											<th>수  량</th>
											<th>총  액</th>
											<th><th>
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
													<td class="itemCnt">
														<fmt:formatNumber value="${ordCntList[itemSts.index]}" type="number" /> 개
													</td>
													<td class="itemCnt">
														<fmt:formatNumber value="${ordItemList[itemSts.index].price*ordCntList[itemSts.index]}" type="number" /> 원
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</fieldset>
						</c:if>
					</c:forEach>
				</div>
				 --%>
				
				
				<form method="post" action="${cp }/member/payment">
					<fieldset class="fieldset">
						<legend>배송지 정보</legend>
						받으시는 분<br>
						<input type="text" id="recName" name="recName" value="${recName}"><br>
						전화 번호<br>
						<input type="text" id="recTel" name="recTel" value="${recTel}"><br>
						주소<br>
						<input type="text" id="postcode" name="recpostno" value="${recpostno}" placeholder="우편번호">&nbsp;&nbsp;
			            <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			            <input type="text" id="address" name = "recAdr" value="${recAdr}" placeholder="주소" class="adr"><br>
			            <input type="text" id="detailAddress" name="recAdr1" value="${recAdr1}" placeholder="상세주소" class="adr"><br>
						<input type="text" id="extraAddress" name="recAdr2" value="${recAdr2}" placeholder="참고항목" class="adr"><br>
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
					
					<div class = "btns">
						<input type="button" value="결제취소" class="cancelBtn" onclick="location.href='${cp}/cancelPurchase'">
						<input type="submit" value="결제하기" onclick="return validate()" class="purchaseBtn">
					</div>
					
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