<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input {
  	outline: none;
  	border: none;
	}
</style>
</head>
<body>
	<h1>상품 상세내용</h1>
	
	<label>상품이름</label><br>
	<input type="text" value="${dto.itemName }" readonly="readonly"><br>
	<label>대분류</label><br>
	<input type="text" value="${dto.mcatNo }" readonly="readonly"><br>
	<label>소분류</label><br>
	<input type="text" value="${dto.scatNo }" readonly="readonly"><br>
	<label>해시태그</label><br>
	<input type="text" value="${dto.hash }" readonly="readonly"><br>
	<label>가격</label><br>
	<input type="text" value="${dto.price }원" readonly="readonly"><br>
	<label>썸네일</label><br>
	<img src="${cp }/resources/uploads/admin/item/${dto.tImg}" width="200px" height="200px"><br>
	<label>상세이미지</label><br>
	<img src="${cp }/resources/uploads/admin/item/${dto.detImg}" width="300px"><br>
	<label>판매여부</label><br>
	<c:choose>
		<c:when test="${dto.itemDel eq 'false' }">
		<input type="text" value="판매중" readonly="readonly"><br>
		<button id="itemDel" onclick="deleteItem()">판매중단</button>
		<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
		</c:when>
		<c:otherwise>
		<input type="text" value="판매중단" readonly="readonly"><br>
		<button onclick="location.href='${cp }/admin/item/list'">목록으로</button>
		</c:otherwise>
	</c:choose>
</body>
<script type="text/javascript">
	function deleteItem() {
		if(confirm("판매를 중단하시겠습니까?")==true){
			location.href='${cp}/admin/item/delete?itemNo=${dto.itemNo}';
		}else{
			return false;
		}
	}
</script>
</html>