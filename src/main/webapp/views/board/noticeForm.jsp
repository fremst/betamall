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
	<form method="post" action="${cp }/board/insert" enctype="multipart/form-data">
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
		<input type="text" name="brdTitle"><br>
		내용<br>
		<textarea rows="10" cols="100" name="brdCon"></textarea><br>
		첨부파일<br>
		<div class = "fileField">
	    	<label for = "uploadedFile">첨부파일</label>
	    	<input type = "file" name = "uploadFile" id = "uploadFile" onchange="setThumbnail(event)"><br>
	       	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	        <div id = "thumbNailImg">
	        </div>
        </div>    
		<input type="button" value="취소" onclick="window.history.back()">
		<input type="submit" value="등록">
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