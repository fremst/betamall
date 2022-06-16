<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<form method="post" action="${cp }/board/update" name="uadateForm">
		<input type="text" name="brdNo" value="${dto.brdNo }" style="display: none">
		카테고리 <select name="brdCat" id="category" onchange="changeSelect()">
			<option value="공지">공지</option>
			<option value="이벤트">이벤트</option>
			<option value="FAQ">FAQ</option>
			<option value="회원가입">회원가입</option>
			<option value="주문결제">주문결제</option>
			<option value="배송">배송</option>
		</select>
		<div id ="eventPeriod">
		</div>
		팝업여부 <input type="checkbox" name="popUp"><br>
		제목<br>
		<input type="text" name="brdTitle" value="${dto.brdTitle }"><br>
		내용<br>
		<textarea rows="10" cols="100" name="brdCon">${dto.brdCon }</textarea><br>
		첨부파일<br>
		<input type="file" name="brdImg"><br>
		<input type="button" value="취소" onclick="window.history.back()">
		<input type="button" value="수정" id="update" onclick="updateCheck()">
	</form>
</body>
	<script type="text/javascript">
		function changeSelect() {
			let category=document.getElementById("category");
			let eventPeriod=document.getElementById("eventPeriod");
			if(category.value == '이벤트'){
				eventPeriod.innerHTML = "시작일 <input type='date' name='brdSdate' id='brdSdate'><br> 종료일 <input type='date' name='brdFdate' id='brdFdate'>";
			}else{
				eventPeriod.innerHTML = "";
			}
		}
		
		function updateCheck() {
			if(confirm("정말 수정하시겠습니까?")==true) {
				document.uadateForm.submit();
			}else {
				return false;
			}
		}
	</script>
</html>