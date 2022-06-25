<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
			<form method="post" action="${cp }/board/update" enctype="multipart/form-data" name="uadateForm">
				<input type="text" name="brdNo" value="${dto.brdNo }" style="display: none">
				<div id="insertHeader">
					<select name="brdCat" id="category" onchange="changeSelect()" class="headerInfo">
						<option value="공지">공지</option>
						<option value="이벤트">이벤트</option>
					</select>
					<input type="text" name="brdTitle" id="brdTitle" value="${dto.brdTitle }" class="headerInfo" placeholder="제목">
					<div id="popUp">
						팝업여부 <input type="checkbox" name="popUp" id="papUp" class="headerInfo">
					</div>
					<div id ="eventPeriod">
					</div>
				</div>
				<div>
					<textarea rows="10" cols="145" name="brdCon" id="brdCon">${dto.brdCon }</textarea><br>
					<div class = "fileField">
						첨부파일<br>
				    	<label for = "uploadedFile">첨부파일</label>
				    	<input type = "file" name = "uploadFile" id = "uploadFile" onchange="setThumbnail(event)"><br>
				       	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
				        <div id = "thumbNailImg">
					        <c:if test="${not empty dto.brdImg}">
								<img src = "${cp}/resources/uploads/admin/board/${dto.brdImg}" id="img">
							</c:if>
				        </div> 
				    </div>
				</div>
				<hr style="margin-top: 15px;">
				<div id="nav">
					<a onclick="window.history.back()" style="margin-left: 15px; cursor: pointer;">취소</a>&nbsp;|
					<a onclick="updateCheck()" id="update" style="cursor: pointer;">수정</a>
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
	          img.setAttribute("id", "img");
	          document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	          document.querySelector("div#thumbNailImg").appendChild(img);
	        };
	        reader.readAsDataURL(event.target.files[0]);
	      }
	</script>
</html>