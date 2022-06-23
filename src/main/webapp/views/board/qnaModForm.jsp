<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<form method="post" action="${cp }/board/qnaupdate" enctype="multipart/form-data" name="uadateForm">
		<input type="text" name="qnaNo" value="${dto.qnaNo }" style="display: none">
		카테고리 <select name="qnaCat" id="category" onchange="changeSelect()">
			<option value="입고">입고</option>
			<option value="배송">배송</option>
			<option value="상품">상품</option>
		</select><br>
		<select name="itemNo" id="itemNo">
			<c:forEach var="idto" items="${idto }">
				<option value="${idto.itemNo }">${idto.itemName }</option>
			</c:forEach>
		</select>
		비밀글 <input type="checkbox" name="secret"><br>
		제목<br>
		<input type="text" name="qnaTitle" id="qnaTitle" value="${dto.qnaTitle }"><br>
		내용<br>
		<textarea rows="10" cols="100" name="qnaCon" id="qnaCon">${dto.qnaCon }</textarea><br>
		첨부파일<br>
		<div class = "fileField">
	    	<label for = "uploadedFile">첨부파일</label>
	    	<input type = "file" name = "uploadFile" id = "uploadFile" onchange="setThumbnail(event)"><br>
	       	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	        <div id = "thumbNailImg">
	        </div>
        </div>  
		<input type="button" value="취소" onclick="window.history.back()">
		<input type="button" value="수정" id="update" onclick="updateCheck()">
	</form>
</body>
	<script type="text/javascript">
		function changeSelect() {
			let category=document.getElementById("category");
			let itemNo=document.getElementById("itemNo");
			if(category.value == '입고' || category.value == '상품'){
				itemNo.style.display = 'block';
				itemNo.disabled = false;
			}else {
				itemNo.style.display = 'none';
				itemNo.disabled = true;
			} 
		}
		
		function updateCheck() {
			let qnaTitle=document.getElementById("qnaTitle");
			let qnaCon=document.getElementById("qnaCon");
			if(qnaTitle.value=='' || qnaTitle.value==null) {
				alert("제목을 입력해주세요.")
				return false;
			}else if(qnaCon.value=='' || qnaCon.value==null) {
				alert("내용을 입력해주세요.")
				return false;
			}else {
				if(confirm("정말 수정하시겠습니까?")==true) {
					document.uadateForm.submit();
				}else {
					return false;
				}
			}
		}
		
		function setThumbnail(event) {
	        var reader = new FileReader();

	        reader.onload = function(event) {
	          var img = document.createElement("img");
	          img.setAttribute("src", event.target.result);
	          document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	          document.querySelector("div#thumbNailImg").appendChild(img);
	        };
	        reader.readAsDataURL(event.target.files[0]);
	      }
	</script>
</html>