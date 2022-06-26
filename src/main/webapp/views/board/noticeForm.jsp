<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/boardInsert.css">
</head>
<body>
	<div id="insertWrap">
		<div id="formSide">
		    <h3>고객센터</h3>
		    <ul>
		        <li class="sidemenu"><a href="${cp }/board/list">- 공지사항/이벤트</a></li>
		        <li class="sidemenu"><a href="${cp }/board/faqlist">- FAQ</a></li>
		        <li class="sidemenu"><a href="${cp }/board/qnalist">- Q&A</a></li>
		    </ul>
		</div>
		<div id="insertMain">
			<form method="post" action="${cp }/board/insert" enctype="multipart/form-data" name="insertForm">
				<div id="insertHeader">
					<select name="brdCat" id="category" onchange="changeSelect()" class="headerInfo">
						<option value="공지">공지</option>
						<option value="이벤트">이벤트</option>
					</select>
					<input type="text" name="brdTitle" id="brdTitle" class="headerInfo" placeholder="제목">
					<div id="popUp">
						팝업여부 <input type="checkbox" name="popUp" id="papUp" class="headerInfo">
					</div>
					<div id ="eventPeriod">
					</div>
				</div>
				<div>
					<textarea cols="138" name="brdCon" id="brdCon" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="내용"></textarea><br>
					<div class = "fileField">
						첨부파일<br>
				    	<label for = "uploadedFile">첨부파일</label>
				    	<input type = "file" name = "uploadFile" id = "uploadFile" onchange="setThumbnail(event)"><br>
				       	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
				        <div id = "thumbNailImg">
				        </div> 
				    </div>
				</div>
				<hr style="margin-top: 15px;">
				<div id="nav">
					<a onclick="window.history.back()" style="margin-left: 15px; cursor: pointer;">취소</a>&nbsp;|
					<a onclick="checkSpace()" style="cursor: pointer;">등록</a>
				</div>	
			</form>
		</div>
	</div>	
</body>
	<script type="text/javascript">
		function changeSelect() {
			let category=document.getElementById("category");
			let eventPeriod=document.getElementById("eventPeriod");
			if(category.value == '이벤트'){
				eventPeriod.innerHTML = "시작일 <input type='date' name='brdSdate' id='brdSdate' class='headerInfo'> ~ 종료일 <input type='date' name='brdFdate' id='brdFdate' class='headerInfo'>&nbsp;|&nbsp; ";
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
			let brdTitle=document.getElementById("brdTitle");
			let brdCon=document.getElementById("brdCon");
			if(brdTitle.value=='' || brdTitle.value==null) {
				alert("제목을 입력해주세요.")
				return false;
			}else if(brdCon.value=='' || brdCon.value==null) {
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
	          img.setAttribute("id", "img");
	          document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	          document.querySelector("div#thumbNailImg").appendChild(img);
	        };
	        reader.readAsDataURL(event.target.files[0]);
	      }
		
		function resize(obj) {
			  obj.style.height = "200px";
			  obj.style.height = (obj.scrollHeight)+"px";
			}
	</script>
</html>