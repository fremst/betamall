<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${cp }/reviewinsert" name="insertForm">
		<input type="text" name="ordNo" value="${ordNo }" style="display: none">
		<input type="text" name="itemNo" value="${dto.itemNo }" style="display: none">
		상품명 
		<p>${dto.itemName }</p>
		평점 <select name="rate">
			<option value="5" selected="selected">5점</option>
			<option value="45">4.5점</option>
			<option value="4">4점</option>
			<option value="35">3.5점</option>
			<option value="3">3점</option>
			<option value="25">2.5점</option>
			<option value="2">2점</option>
			<option value="15">1.5점</option>
			<option value="1">1점</option>
			<option value="50">0.5점</option>
		</select><br>
		내용<br>
		<textarea rows="10" cols="100" name="review" id="review"></textarea><br>
		<input type="button" value="취소" onclick="window.history.back()">
		<input type="button" value="등록" onclick=checkSpace()>
	</form>
</body>
	<script type="text/javascript">
		function checkSpace() {
			let review=document.getElementById("review");
			if(review.value=='' || review.value==null) {
				alert("후기내용을 입력해주세요.")
				return false;
			}else {
				document.insertForm.submit();
			}
		}
	</script>
</html>
