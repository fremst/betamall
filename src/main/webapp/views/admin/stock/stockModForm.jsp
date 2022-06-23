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
</head>
<body>
	<c:choose>
		<c:when test="${role == 'admin' }">
	<h1>재고 등록/수정</h1>
	
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
			<%--
			<c:choose>
				<c:when test="${role == 'admin0' }">
					<label>판매여부</label>
						<c:choose>
							<c:when test="${dto.itemDel eq 'false' }">
							<input type="text" value="판매중" readonly="readonly">
							<button onclick="deleteItem()">판매중단</button><br>
							<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
							</c:when>
							<c:otherwise>
							<input type="text" value="판매중단" readonly="readonly"><br>
							<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
							</c:otherwise>
						</c:choose>
				</c:when>
				
				<c:when test="${role == 'admin' }">
					<label>판매여부</label>
						<c:choose>
							<c:when test="${dto.itemDel eq 'false' }">
							<input type="text" value="판매중" readonly="readonly"><br>
							<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
							</c:when>
							<c:otherwise>
							<input type="text" value="판매중단" readonly="readonly"><br>
							<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
							</c:otherwise>
						</c:choose>
				</c:when>
			</c:choose>
			 --%>
			<form action="${cp}/admin/stock/update" method="post">
				<input type="hidden" value="${dto.itemNo}" name="itemNo">
				<input type="hidden" value="${brNo}" name="brNo">
			<label>현재재고
				<c:if test="${nullStk == 'true'}">
					(입고전)
				</c:if>
			</label>
				<c:choose>
					<c:when test="${nullStk == 'true'}">
						<input type="text" value="0" readonly="readonly" id="currStk"><br>
					</c:when>
					<c:otherwise>
						<input type="text" value="${stkDto.stkCnt}" readonly="readonly" id="currStk"><br>
					</c:otherwise>
				</c:choose>
				<label>증감량</label>
				<input type="text" value="0" name="deltaStk" onkeyup="return changeStk()" id="deltaStk"><br>
				<label>예상재고</label>
				<input type="text" value="${stkDto.stkCnt}" readonly="readonly" id="nextStk"><br>
				<input type="submit" onclick="return validateStk()" value="재고변경">
			</form>
		</div>
	</div>

	---------------------------------------------------------
	<div>
	</c:when>
	<c:otherwise>
	<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
	</c:otherwise>
</c:choose>	
	
	
	</div>

</body>
<script type="text/javascript">


	function changeStk(){
		let currStk = document.getElementById('currStk');
		let nextStk = document.getElementById('nextStk');
		let deltaStk = document.getElementById('deltaStk');
		nextStk.value = parseInt(currStk.value) + parseInt(deltaStk.value);
		
		if (!(/[0-9]{1,3}/).test(deltaStk.value)) {
			alert('숫자만 입력 가능합니다.');
			deltaStk.value = 0;
			return false;
		}
		
		if(nextStk.value < 0){
			alert('경고');
			deltaStk.value = 0;
			nextStk.value = parseInt(currStk.value) + parseInt(deltaStk.value);
		}
	}
	
	function validateStk(){
		let deltaStk = document.getElementById('deltaStk');
		if (!(/[0-9]{1,3}/).test(deltaStk.value)) {
			alert('숫자만 입력 가능합니다.');
			deltaStk.value = 0;
			return false;
		}
	}

</script>
</html>