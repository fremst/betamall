<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 등록</h1>
	<form method="post" action="${cp }/board/qnainsert" enctype="multipart/form-data" name="insertForm">
		카테고리 <select name="qnaCat" id="category" onchange="changeSelect()">
			<option value="입고">입고</option>
			<option value="배송">배송</option>
			<option value="상품">상품</option>
		</select>
		<div id ="eventPeriod">
		</div>
		비밀글 <input type="checkbox" name="srcret"><br>
		제목<br>
		<input type="text" name="qnaTitle" id="qnaTitle"><br>
		내용<br>
		<textarea rows="10" cols="100" name="qnaCon" id="qnaCon"></textarea><br>
		첨부파일<br>
		<div class = "fileField">
	    	<label for = "uploadedFile">첨부파일</label>
	    	<input type = "file" name = "uploadFile" id = "uploadFile" onchange="setThumbnail(event)"><br>
	       	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	        <div id = "thumbNailImg">
	        </div>
        </div>    
		<input type="button" value="취소" onclick="window.history.back()">
		<input type="button" value="등록" onclick=checkSpace()>
	</form>
</body>
	<script type="text/javascript">
		function changeSelect() {
			let category=document.getElementById("category");
			let eventPeriod=document.getElementById("eventPeriod");
			if(category.value == '상품'){
				eventPeriod.innerHTML = "시작일 <input type='date' name='brdSdate' id='brdSdate'><br> 종료일 <input type='date' name='brdFdate' id='brdFdate'>";
				let brdSdate=document.getElementById("brdSdate");
				let brdFdate=document.getElementById("brdFdate");
				brdSdate.addEventListener('change', function() {
				    if (brdSdate.value)
				    	brdFdate.min = brdSdate.value;
				}, false);
				brdFdate.addEventLiseter('change', function() {
				    if (brdFdate.value)
				    	brdSdate.max = brdFdate.value;
				}, false);
			}else{
				eventPeriod.innerHTML = "";
			}
		}
		
		function checkSpace() {
			let qnaTitle=document.getElementById("qnaTitle");
			let qnaCon=document.getElementById("qnaCon");
			if(qnaTitle.value=='' || qnaTitle.value==null) {
				alert("제목을 입력해주세요.")
				return false;
			}else if(qnaCon.value=='' || qnaCon.value==null) {
				alert("내용을 입력해주세요.")
				return false;
			}else {
				document.insertForm.submit();
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