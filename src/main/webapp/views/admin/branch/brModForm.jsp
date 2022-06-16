<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점 페이지</title>
</head>
<body>
    <div>
    	<h2 id = "subtitle">지점 정보 수정/삭제</h2>
   	    <form method="post" enctype="multipart/form-data" onsubmit = "return validate()}">
        	<fieldset id = "fieldset">
        	<div>
	            <label for = "brNo">지점번호</label><br>
	            <input type= "text" id = "brNo" value = "${brDto.brNo}" class = "inputText" disabled = "disabled"><br>
	            <input type= "hidden" name = "brNo" value = "${brDto.brNo}">
	            <label for = "brName">지점명</label><br>
	            <input type= "text" id = "brName" value = "${brDto.brName}" class = "inputText" onkeyup = "resetCheck()" disabled = "disabled"><br>
	            <input type= "hidden" name = "brName" value = "${brDto.brName}">
	            <label for = "brAddr">지점 주소</label><br>
	            <input type= "text" name= "brAddr" id = "brAddr" value ="${brDto.brAddr}" class = "inputText"><br>
	            <label for = "brTel">지점 전화번호</label><br>
	            <input type= "text" name = "brTel" id = "brTel" value = "${brDto.brTel}" placeholder = "021234567" class = "inputText"><br>
	            <label for = "brDate">지점 개업일</label><br>
	            <input type= "date" id = "brDate" value = "${brDto.brDate}" class = "inputText" disabled = "disabled"><br>
	            <input type= "hidden" name = "brDate" value = "${brDto.brDate}"><br>
	        </div>
	        <div class = "fileField">
	            <label for = "uploadedFile">대표사진</label>
	            <input type = "file" name = "uploadFile" id = "uploadFile" accept="image/jpeg, image/png" onchange="setThumbnail(event)"><br>
	            <span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	            <div id = "thumbNailImg">
	            <c:if test="${not empty BrDto.mgrImg}">
		           	<img src = "${cp}/resources/uploads/admin/branch/${brDto.brImg}">
	            </c:if>
	            </div>
            </div>
            </fieldset>
            <div id = "btns">
		    	<input type = "submit" value= "수정" formaction="${cp }/admin/branch/update?brNo=${brDto.brNo}">
		    	<input type = "button" value = "취소" onclick = "location.href='${cp }/admin/branch/list'">
		    	<input type = "submit" value= "삭제" onclick="if(!confirm('한 번 삭제하면 되돌릴 수 없습니다. 삭제할까요?')) {return false;}" formaction="${cp }/admin/branch/delete?brNo=${brDto.brNo}">
           	</div>
        </form>
    </div>
</body>
<script type="text/javascript">
	let exists = -1;
	
	window.onload = function(){
		if(${res=='success'}){
			alert('성공적으로 처리되었습니다.');
		}else if(${res=='fail'}){
			alert('요청 처리에 실패하였습니다.');
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
			alert('지점명 입력해주세요.');
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
				}
			}
		}
		xhr.open('post','${cp }/brnamecheck?brName='+BrName,true);
		xhr.send();
	}
</script>

</html>

