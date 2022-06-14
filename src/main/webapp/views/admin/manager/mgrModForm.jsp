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
   	    <form method="post" enctype="multipart/form-data"
   	    onsubmit = "if(${isDel=='true'}){
   	    				if(!confirm('한 번 삭제하면 되돌릴 수 없습니다. 삭제할까요?')) {return false;}
   	    			} else {
   	    			return validate()}">
        	<input type = "text" value = "${mgrInfoDto.mgrNo}" hidden="hidden">
        	<fieldset id = "fieldset">
        		<div>
	        		<label for = "mgrNo">점장번호</label><br>
		            <input type = "text" name = "mgrNo" value="${mgrInfoDto.mgrNo}" disabled="disabled" class = "inputText"><br>
		            <label for = "mgrName">성명</label><br>
		            <input type= "text" name = "mgrName" id = "mgrName" value = "${mgrInfoDto.mgrName}" class = "inputText"><br>
		            <label for = "mgrTel">전화번호</label><br>
		            <input type= "text" name = "mgrTel" id = "mgrTel" value = "${mgrInfoDto.mgrTel}" placeholder = "01012345678" class = "inputText"><br>
		            <label for = "mgrEmail">이메일</label><br>
		            <input type= "email" name= "mgrEmail" id = "mgrEmail" value ="${mgrInfoDto.mgrEmail}" placeholder = "example@mail.com" class = "inputText"><br>
	           		<label for = "mgrId">아이디</label><br>
	            	<input type= "text" name= "mgrId" value = "${mgrInfoDto.mgrId}" id ="mgrId" class = "inputText"><br>
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
					<input type = "file" name = "uploadFile" accept="image/jpeg, image/png" onchange="setThumbnail(event)"><br>
	            	<span class = "notice">※ 파일 업로드 최대 용량 5MB 이하<br>(최대 사이즈 150px * 150px)</span>
	            	<div id = "thumbNailImg">
		            <c:if test="${not empty mgrInfoDto.mgrImg}">
			           	<img src = "${cp}/resources/uploads/admin/manager/${mgrInfoDto.mgrImg}">
		            </c:if>
		            </div>
				</div>
            </fieldset>
            <div id = "btns">
	            <c:if test="${role == 'master' && relation != 'self'}">
			        <input type="submit" value="삭제" formaction="${cp }/admin/manager/delete?mgrNo=${mgrInfoDto.mgrNo}">
	           	</c:if>
	           	<c:if test="${relation == 'self'}">
		            <input type="submit" value="수정" formaction="${cp }/admin/manager/update?mgrNo=${mgrInfoDto.mgrNo}">
	           	</c:if>
	           	<button onclick = "history.back(); return false;">취소</button>
           	</div>
        </form>
    </div>
</body>
<script type="text/javascript">
	let h2 = document.getElementById("subtitle");
	if(${relation == 'self'}){
		h2.innerHTML = '정보 수정';
		document.getElementById("mgrId").disabled = true;
		document.getElementById("brName").disabled = true;
	}else{
		h2.innerHTML = '조회/삭제';
		document.getElementById("fieldset").disabled = true;
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
    
    function validate(){
    	let name = document.getElementById('mgrName').value;
    	let tel = document.getElementById('mgrTel').value;
    	let email = document.getElementById('mgrEmail').value;
    	let id = document.getElementById('mgrId').value;
		let pwd = document.getElementById('mgrPwd').value;
    	
    	if(name == ''){
			alert('이름을 입력해주세요.');
    		return false;
		}else if(tel == ''){
			alert('전화번호를 입력해주세요.');
    		return false;
		}else if(email == ''){
			alert('이메일을 입력해주세요.');
    		return false;
    	}else if(!((/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/).test(pwd))){
    		alert('비밀번호는 영어/숫자/특수문자를 조합해 8-12자로 이루어져야 합니다.');
    	}else{
    		return true;
    	}
    }
</script>
</html>