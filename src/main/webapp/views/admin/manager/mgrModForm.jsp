<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점장 페이지</title>
<link rel = "stylesheet" href="${cp}/resources/css/mgrForm.css">
</head>
<body>
	<div>
		<c:if test="${role == 'master' && relation != 'self'}">
			<c:set var="isDel" value="true"/>
		</c:if>
		<h2 id = "subtitle"></h2>
		<form method="post" enctype="multipart/form-data">
			<input type = "text" value = "${mgrInfoDto.mgrNo}" hidden="hidden">
			<fieldset id = "fieldset">
		<div class = "inputTextField">
			<label for = "mgrNo">점장번호</label><br>
			<input type = "text" name = "mgrNo" value="${mgrInfoDto.mgrNo}" disabled="disabled" class = "inputText"><br>
			<label for = "mgrName">성명</label><br>
			<input type= "text" name = "mgrName" id = "mgrName" value = "${mgrInfoDto.mgrName}" class = "inputText"><br>
			<label for = "mgrTel">전화번호</label><br>
			<input type= "text" name = "mgrTel" id = "mgrTel" value = "${mgrInfoDto.mgrTel}" placeholder = "01012345678" class = "inputText"><br>
			<label for = "mgrEmail">이메일</label><br>
			<input type= "email" name= "mgrEmail" id = "mgrEmail" value ="${mgrInfoDto.mgrEmail}" placeholder = "example@mail.com" class = "inputText"><br>
			<label for = "mgrId">아이디</label><br>
			<input type= "text" name= "mgrId" value = "${mgrInfoDto.mgrId}" id ="mgrId" class = "inputText" disabled="disabled"><br>
			<c:if test="${relation == 'self'}">
				<label for = "mgrPwd">비밀번호</label><br>
				<input type= "password" name = "mgrPwd" id = "mgrPwd" placeholder = "8~12자 영문/숫자/특수 문자 조합" class = "inputText"><br>
			</c:if>
			<label for = "brName">담당지점</label><br>
			<select name = 'brName' id = "brName" class = "inputText">
				<option>${mgrInfoDto.brName}</option>
			</select><br>
		</div>
		<div class = "fileField">
			<label for = "uploadedFile">증명사진</label>
			<input type = "file" name = "uploadFile" id = "uploadFile" accept="image/jpeg, image/png" onchange="setThumbnail(event)" value = "default"><br>
			<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
			<div id = "thumbNailImg">
				<c:if test="${not empty mgrInfoDto.mgrImg}">
					<img src = "${cp}/resources/uploads/admin/manager/${mgrInfoDto.mgrImg}" id="mgrImg">
				</c:if>
			</div>
		</div>
		</fieldset>
		<div id = "btns">
			<c:if test="${role == 'master' && relation != 'self'}">
			<input type = "submit" value="삭제" onclick="if(!confirm('한 번 삭제하면 되돌릴 수 없습니다. 삭제할까요?')) {return false;}" formaction="${cp }/admin/manager/delete?mgrNo=${mgrInfoDto.mgrNo}">
			</c:if>
			<c:if test="${relation == 'self'}">
			<input type = "submit" value = "수정" formaction="${cp }/admin/manager/update?mgrNo=${mgrInfoDto.mgrNo}" onclick = "return validate()">
			</c:if>
			<input type = "button" value = "취소" onclick = "location.href='${cp }/admin/manager/list'">
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
	        img.setAttribute("id", "mgrImg");
	        document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	        document.querySelector("div#thumbNailImg").appendChild(img);
	    };
	    reader.readAsDataURL(event.target.files[0]);
	}
	
	function validate(){
		let name = document.getElementById('mgrName').value;
		let tel = document.getElementById('mgrTel').value;
		let email = document.getElementById('mgrEmail').value;
		let pwd = document.getElementById('mgrPwd').value;
		let brName = document.getElementById('brName').value;
		let file = document.getElementById('uploadFile').value;
		
		if(!name){
			alert('이름을 입력해주세요.');
			return false;
		}else if(!tel){
			alert('전화번호를 입력해주세요.');
			return false;
		}else if(!(/[0-9]{9,13}/).test(tel)){
			alert('전화번호를 정확히 입력해주세요.');
			return false;
		}else if(!email){
			alert('이메일을 입력해주세요.');
			return false;
		}else if(!((/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/).test(pwd))){
			alert('비밀번호는 영어/숫자/특수문자를 조합해 8-12자로 이루어져야 합니다.');
			return false;
		}else{
			return true;
		}
	}
	
	function checkId(){
		let id = document.getElementById("mgrId").value;
		let xhr = new XMLHttpRequest();
		
	    if(!(/^[a-z0-9]{4,12}$/g).test(id)){
			alert('아이디는 영어 소문자로 시작하며, 소문자 및 숫자를 포함해 4-12자로 이루어져야 합니다.');
			return;
		}
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				exists = JSON.parse(xhr.responseText).exist;
				if(exists == 1){
					alert('사용중인 아이디입니다.');
				}else{
					alert('사용 가능한 아이디입니다.');
				}
			}
		}
		xhr.open('post',"${cp }"+'/idcheck?id='+id,true);
		xhr.send();
	}
	
</script>
</html>