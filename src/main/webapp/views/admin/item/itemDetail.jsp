<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="${cp}/resources/css/itemForm.css">
<style type="text/css">

#purchaseBox{
	width: 800px;
	padding: 20px;
	margin: auto;
}

#purchaseForm {
	width: 200px;
	margin: 0;
	display: inline-block;
}

.deltaBtns{
	width: 30px;
}

.inputText{
	width: 80px;
}

.btns{
	width: 80px;

}

</style>
</head>
<body>
	<h1>상품 상세 페이지</h1>
	
	<div class="context">
		<div class="tImg">
			<img src="${cp }/resources/uploads/admin/item/${dto.tImg}" width="200px" height="200px">
		</div>
		<div class="list">
			<label id="itemName">${dto.itemName }</label><br>
			<c:forEach var="mcatDto" items="${mcatList}">
				<c:if test="${dto.mcatNo == mcatDto.mcatNo}">
						<label>대분류</label>
						<input type="text" value="${mcatDto.mcatName}" readonly="readonly"><br>
					<c:forEach var="scatDto" items="${scatList}">
					<c:if test="${(dto.mcatNo == scatDto.mcatNo) && (dto.scatNo == scatDto.scatNo)}">
						<label>소분류</label>
						<input type="text" value="${scatDto.scatName}" readonly="readonly"><br>
					</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
			<label>가격</label>
			<input type="text" value="<fmt:formatNumber value="${dto.price}" type="number" /> 원" readonly="readonly"><br>
			<label>해시태그</label>
			<input type="text" value="${dto.hash }" readonly="readonly"><br>
			
			<c:choose>
				<c:when test="${role == 'admin0' }">
					<label>판매여부</label>
						<c:choose>
							<c:when test="${dto.itemDel eq 'false' }">
							<input type="text" value="판매중" readonly="readonly">
							<button onclick="deleteItem()">판매중단</button><br>
							<button onclick="history.back()">목록으로</button>
							</c:when>
							<c:otherwise>
							<input type="text" value="판매중단" readonly="readonly"><br>
							<button onclick="history.back()">목록으로</button>
							</c:otherwise>
						</c:choose>
				</c:when>
				
				<c:when test="${role == 'admin' }">
					<label>판매여부</label>
						<c:choose>
							<c:when test="${dto.itemDel eq 'false' }">
							<input type="text" value="판매중" readonly="readonly"><br>
							<button onclick="history.back()">목록으로</button>
							</c:when>
							<c:otherwise>
							<input type="text" value="판매중단" readonly="readonly"><br>
							<button onclick="history.back()">목록으로</button>
							</c:otherwise>
						</c:choose>
				</c:when>
			</c:choose>		
		</div>
	</div>
	
	<c:choose>
		<c:when test="${not empty stkDtos}"> 
			<fieldset id="purchaseBox">
					<legend><h3>구매하기</h3></legend>
					<c:forEach var="i" begin="0" end="${brDtos.size()-1}">
						<c:if test="${stkDtos[i].stkCnt>0}">
						<form action="${cp}/member/addcart" method="get" id="purchaseForm">
						<h3>${brDtos[i].brName}</h3>
						(재고<fmt:formatNumber value="${stkDtos[i].stkCnt}" type="number"/>개) <br>
							<input type="button" value ="－" class="deltaBtns" onclick="subtractCnt(${i}, ${stkDtos[i].stkCnt})">  
							<input type="text" id="DOrdCnt${i}" value="1" class="inputText" readonly>
							<input type="button" value ="＋" class="deltaBtns" onclick="addCnt(${i}, ${stkDtos[i].stkCnt})"><br>
							상품금액
							<input type="text" id="DEachAmt${i}" value="<fmt:formatNumber value="${dto.price}" type="number"/>" class="inputText"> 원<br>
							배송비
							<input type="text" id="DDelAmt" value="<fmt:formatNumber value="${2500}" type="number"/>" class="inputText"> 원<br>
							총액
							<input type="text" id="DTotAmt${i}" value="<fmt:formatNumber value="${dto.price+2500}" type="number"/>" class="inputText"> 원<br>
							<input type="submit" value="구매하기" onclick="return purchase()" class="btns">
							
							<input type="hidden" id="ordCnt${i}" name="ordCnt" value="1">
							<input type="hidden" id="eachAmt${i}" value="${dto.price}">
							<input type="hidden" id="delAmt" value="${2500}">
							<input type="hidden" id="totAmt${i}" value="${dto.price+2500}">
							
							<input type="hidden" name="brNo" value="${brDtos[i].brNo}">
							<input type="hidden" name="itemNo" value="${dto.itemNo}">
						</form>
						</c:if>
					</c:forEach>
			</fieldset>
		</c:when>
		<c:otherwise>
			재고가 없습니다.
		</c:otherwise>
	</c:choose>
	
	<div class="img">
	<label id="detImg">상세이미지</label><br>
	<img src="${cp }/resources/uploads/admin/item/${dto.detImg}" width="800px"><br>
	</div>
	
	------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	<div>
		<h1>후기</h1>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach var="list" items="${list }">
					<form action="${cp }/reviewdelete" name="deleteForm">
						<label>작성자</label>
						<input type="text" value="${list.mbrId }" readonly="readonly"><br>
						<label>작성일자</label>
						<input type="text" value="${list.revDate }" readonly="readonly"><br>
						<img src="${cp }/resources/uploads/admin/rate/${list.rate }.jpg"><br>
						<input type="text" value="${list.review }" readonly="readonly"><br>
						<input type="text" name="ordNo" value="${list.ordNo }" style="display: none">    
		                <input type="text" name="itemNo" value="${list.itemNo }" style="display: none"> 
		               	<c:if test="${list.mbrId == id || role == 'admin' || role == 'admin0'}">
		                	<button id="del" onclick="del()">삭제</button>
		             	</c:if>
					</form>
					--------------------------------------------------------------------------------------------------------------------------<br>
				</c:forEach>
			</c:when>
			<c:otherwise>
				등록된 후기가 없습니다.
			</c:otherwise>
		</c:choose>
	</div>

</body>
<script type="text/javascript">
	function deleteItem() {
		if(confirm("판매를 중단하시겠습니까?")==true){
			location.href='${cp}/admin/item/delete?itemNo=${dto.itemNo}';
		}else{
			return false;
		}
	}

  
	function addCnt(m, n){
		let ordCnt = document.getElementById('ordCnt'+m);
		let eachAmt = document.getElementById('eachAmt'+m);
		let delAmt = document.getElementById('delAmt');
		let totAmt = document.getElementById('totAmt'+m);
		
		let DOrdCnt = document.getElementById('DOrdCnt'+m);
		let DEachAmt = document.getElementById('DEachAmt'+m);
		let DDelAmt = document.getElementById('DDelAmt');
		let DTotAmt = document.getElementById('DTotAmt'+m);
		
		if(parseInt(DOrdCnt.value) < n){
			ordCnt.value = parseInt(ordCnt.value)+1;
			eachAmt.value = ${dto.price}*parseInt(ordCnt.value);
			totAmt.value = parseInt(eachAmt.value)+2500;
			
			DOrdCnt.value = parseInt(ordCnt.value).toLocaleString('en-US');
			DEachAmt.value = parseInt(eachAmt.value).toLocaleString('en-US');
			DTotAmt.value = parseInt(totAmt.value).toLocaleString('en-US');
		}
	}
	
	function subtractCnt(m, n){
		let ordCnt = document.getElementById('ordCnt'+m);
		let eachAmt = document.getElementById('eachAmt'+m);
		let delAmt = document.getElementById('delAmt');
		let totAmt = document.getElementById('totAmt'+m);
		
		let DOrdCnt = document.getElementById('DOrdCnt'+m);
		let DEachAmt = document.getElementById('DEachAmt'+m);
		let DDelAmt = document.getElementById('DDelAmt');
		let DTotAmt = document.getElementById('DTotAmt'+m);
		
		if(parseInt(DOrdCnt.value) > 1){
			ordCnt.value = parseInt(ordCnt.value)-1;
			eachAmt.value = ${dto.price}*parseInt(ordCnt.value);
			totAmt.value = parseInt(eachAmt.value)+2500;
			
			DOrdCnt.value = parseInt(ordCnt.value).toLocaleString('en-US');
			DEachAmt.value = parseInt(eachAmt.value).toLocaleString('en-US');
			DTotAmt.value = parseInt(totAmt.value).toLocaleString('en-US');
		}
	}

	function purchase(){
		if(${empty id}){
			alert('로그인이 필요한 서비스입니다.');
			return true;
		}else{
			if(${IpOrd == 'true'}){
				if(confirm('결제 대기 상품이 있습니다. 결제창으로 이동하시겠습니까?')){
				 	location.href="${cp}/member/payment";
				}
			}else{
				return confirm('구매하시겠습니까?');
			}
		}
		return false;
	}
	

	function del() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			document.deleteForm.submit();
		}else {
			return false;
		}
	}

</script>
</html>