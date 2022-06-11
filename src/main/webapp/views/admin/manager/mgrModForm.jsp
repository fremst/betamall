<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점장 페이지</title>
<style type="text/css">
	fieldset {
		width: 500px;
		margin: auto;
	}
	#btns {
		width: 100px;
		margin: auto;
	}
	span {
		color: gray;
		font-size: 10pt
	}
	img {
		width: 150px;
		height: 150px;
	}
</style>
</head>
<body>
    <div>
	    <c:if test="${role == 'master'}">
	    	<c:set var="isDel" value="true"></c:set>
	    </c:if>
    	<h2 id = "subtitle"></h2>
   	    <form method="post" enctype="multipart/form-data"
   	    onsubmit = "if(${isDel=='true'}){if(!confirm('한 번 삭제하면 되돌릴 수 없습니다. 삭제할까요?')){return false;}}">
        	<input type = "text" value = "${mgrInfoDto.mgrNo}" hidden="hidden">
        	<fieldset id = "fieldset">
        		<c:if test="${not empty mgrInfoDto.mgrNo}">
	        		<label for = "mgrNo">점장번호:</label>
		            <input type = "text" name = "mgrNo" value="${mgrInfoDto.mgrNo}" disabled="disabled"><br>
        		</c:if>
	            <label for = "mgrName">성명</label><br>
	            <input type= "text" name = "mgrName" value = "${mgrInfoDto.mgrName}"><br>
	            <label for = "mgrTel">전화번호</label><br>
	            <input type= "text" name = "mgrTel" value = "${mgrInfoDto.mgrTel}" placeholder = "01012345678"><br>
	            <label for = "mgrEmail">이메일</label><br>
	            <input type= "text" name= "mgrEmail" value ="${mgrInfoDto.mgrEmail}" placeholder = "example@mail.com"><br>
           		<!-- 유효성 검증 추가 예정 -->
	            <c:if test="${relation == 'self'}">
	            	<label for = "mgrPwd">비밀번호</label><br>
		            <input type= "password" name = "mgrPwd" placeholder = "8~12자 영문/숫자/특수 문자"><br>
	            </c:if>
            	<label for = "brName">담당지점</label><br>
	            <select name = 'brName' id = "brName">
            		<option>${mgrInfoDto.brName}</option>
	            </select><br>
	            <label for = "uploadedFile">증명사진</label>
	            <input type = "file" name = "uploadFile" accept="image/jpeg, image/png"><br>
	            <span>※ 파일 업로드 최대 용량 5MB 이하 (최대 사이즈 150px * 150px)</span>
	            <!-- 미리 보기 기능 추가 예정 -->
	           	<c:if test="${not empty mgrInfoDto.mgrImg}">
		           	<img src = "${cp}/resources/uploads/admin/manager/${mgrInfoDto.mgrImg}">
	            </c:if>
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
</script>
</html>