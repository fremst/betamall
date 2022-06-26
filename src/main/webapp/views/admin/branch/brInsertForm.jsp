<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점 페이지</title>
<link rel = "stylesheet" href="${cp}/resources/css/brForm.css">
</head>
<body>
    <div>
    	<h2 id = "subtitle">지점 등록</h2>
   	    <form method="post" enctype="multipart/form-data" onsubmit="return validate()" action="${cp }/admin/branch/insert">
        	<fieldset id = "fieldset">
        	<div class = "inputTextField">
	            <label for = "brName">지점명</label><br>
	            <input type= "text" name = "brName" id = "brName" value = "${brDto.brName}" class = "inputText" onkeyup = "resetCheck()">
				<input type = "button" value = "중복 확인" onclick = "checkName()"><br>
	            <label for = "brAddr">지점 주소</label><br>
	            <input type= "text" name= "brAddr" id = "brAddr" value ="${brDto.brAddr}" class = "inputText"><br>
	            <label for = "brTel">지점 전화번호</label><br>
	            <input type= "text" name = "brTel" id = "brTel" value = "${brDto.brTel}" placeholder = "021234567" class = "inputText"><br>
	            <label for = "brDate">지점 개업일</label><br>
	            <input type= "date" name = "brDate" id = "brDate" value = "${brDto.brDate}" class = "inputText"><br>
	        </div>
	        <div class = "fileField">
	            <label for = "uploadedFile">대표사진</label>
	            <input type = "file" name = "uploadFile" id = "uploadFile" accept="image/jpeg, image/png" onchange="setThumbnail(event)"><br>
	            <span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	            <div id = "thumbNailImg">
	            </div>
            </div>
            </fieldset>
            <div id = "btns">
		    	<input type="submit" value="등록">
		    	<button onclick = "history.back(); return false;">취소</button>
           	</div>
        </form>
    </div>
</body>
<script type="text/javascript">
	let exists = -1;
	
	window.onload = function(){
		if(${res=='fail'}){
			alert('등록에 실패하였습니다.');
		}
	}
	
	function setThumbnail(event) {
	    let reader = new FileReader();
	    reader.onload = function(event) {
	        let img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	        img.setAttribute("id", "brImg");
	        document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	        document.querySelector("div#thumbNailImg").appendChild(img);
	    };
	    reader.readAsDataURL(event.target.files[0]);
	}
	
	function resetCheck(){
		exists = -1;
	}
	
	function validate(){
		
		let name = document.getElementById('brName').value;
		let addr = document.getElementById('brAddr').value;
		let tel = document.getElementById('brTel').value;
		let date = document.getElementById('brDate').value;
		let file = document.getElementById('uploadFile').value;
		
		if(!name){
			alert('지점명을 입력해주세요.');
			return false;
		}else if(!addr){
			alert('지점 주소를 입력해주세요.');
			return false;
		}else if(!tel){
			alert('지점 전화번호를 입력해주세요.');
			return false;
		}else if(!date){
			alert('지점 개업일을 입력해주세요.');
			return false;
		}else if(!(/[0-9]{9,13}/).test(tel)){
			alert('전화번호를 정확히 입력해주세요.');
			return false;
		}else if(!file){
			alert('대표 사진을 업로드해주세요.');
			return false;
		}else if(exists == -1){
			alert('지점명 중복 확인을 해주세요.');
			return false;
		}else if(exists == 1){
			alert('다른 지점명을 사용해주세요.');
			return false;
		}else{
			return true;
		}
		
	}
	
	function checkName(){
		let BrName = document.getElementById("brName").value;
		let xhr = new XMLHttpRequest();
		
	    if(!(/^.{2,10}$/g).test(BrName)){
			alert('지점명은 2자 이상 10자 이하로 이루어져야 합니다.');
			return;
		}
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				exists = JSON.parse(xhr.responseText).exist;
				if(exists == 1){
					alert('사용중인 지점명입니다.');
				}else{
					alert('사용 가능한 지점명입니다.');
					console.log('잉'+exists);
				}
			}
		}
		xhr.open('post','${cp }/brnamecheck?brName='+BrName,true);
		xhr.send();
	}
</script>

</html>

