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
	<form method="post" action="${cp }/board/update" enctype="multipart/form-data" name="uadateForm">
		<input type="text" name="brdNo" value="${dto.brdNo }" style="display: none">
		카테고리 <select name="brdCat" id="category" onchange="changeSelect()">
			<option value="공지">공지</option>
			<option value="이벤트">이벤트</option>
		</select>
		<div id ="eventPeriod">
		</div>
		팝업여부 <input type="checkbox" name="popUp"><br>
		제목<br>
		<input type="text" name="brdTitle" id="brdTitle" value="${dto.brdTitle }"><br>
		내용<br>
		<textarea rows="10" cols="100" name="brdCon" id="brdCon">${dto.brdCon }</textarea><br>
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
			let eventPeriod=document.getElementById("eventPeriod");
			if(category.value == '이벤트'){
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
		
		function updateCheck() {
			let brdTitle=document.getElementById("brdTitle");
			let brdCon=document.getElementById("brdCon");
			if(brdTitle.value=='' || brdTitle.value==null) {
				alert("제목을 입력해주세요.")
				return false;
			}else if(brdCon.value=='' || brdCon.value==null) {
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