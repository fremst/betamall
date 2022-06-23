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
	<h1>상품 상세 페이지</h1>
	
	<div class="context">
		<div class="tImg">
			<img src="${cp }/resources/uploads/admin/item/${dto.tImg}" width="200px" height="200px">
		</div>
		<div class="list">
			<label id="itemName">${dto.itemName }</label><br>
			<label>대분류</label>
			<input type="text" value="${dto.mcatNo }" readonly="readonly"><br>
			<label>소분류</label>
			<input type="text" value="${dto.scatNo }" readonly="readonly"><br>
			
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
		</div>
	</div>
	<div class ="quickMenu">
		<a href = "#"><img src = "${cp}/resources/images/cart.png" height = "25px"> 장바구니에 담기</a>
	</div>
	
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
	
	function del() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			document.deleteForm.submit();
		}else {
			return false;
		}
	}
</script>
</html>