<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>MyPage</title>
 	<link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/myPage.css">
</head>
<body>
    <div id="wrap">
        <div id="side">
            <h3>마이 페이지</h3>
            <ul>
                <li><a href="${cp }/member/update">나의 정보 수정</a></li>
                <li><a href=#>장바구니</a></li>
                <li><a href=#>지점 즐겨찾기</a></li>
                <li><a href=#>주문/배송 조회</a></li>
                <li><a href=#>내글보기</a></li>
                <li><a href=#>등급/쿠폰 조회</a></li>
            </ul>
        </div>
       <div style="color: red; font-size: 12px;">${errMsg}</div>
        <div id="center">
        	<form method="post" name="modForm" onsubmit="checkValue()">
            <input type="hidden" name="mbrNo" value="${mbrDto.mbrNo}"><br>
            아이디 <input type="text" name="mbrId" value="${mbrDto.mbrId}" disabled=disabled><br>
            새 비밀번호 <input type="password" name =mbrPwd id=mbrPwd><br>
            새 비밀번호 확인 <input type="password" id="pwd"><br>
            이름<input type="text" name="mbrName" value="${mbrDto.mbrName}" disabled=disabled><br>
            생년월일<input type="date" name="mbrBd" value="${mbrDto.mbrBd}" disabled=disabled><br>
            전화번호<input type="text" name="mbrTel" value="${mbrDto.mbrTel}" placeholder ="'-'하이픈 없이 숫자만 입력"><br>
		    주소<input type="text" name="mbrAdr" value="${mbrDto.mbrAdr}"><br>
		    이메일<input type="email" name="mbrEmail" value="${mbrDto.mbrEmail}"><br>
            가입일<input type="text" name="mbrRegdate" value="${mbrDto.mbrRegdate}" disabled=disabled><br>
           	회원등급<input type="text" name="mbrGrade" value="${mbrDto.mbrGrade}" disabled=disabled><br>
            총 결제금액 <input type="text" name="totAmt" value="${mbrDto.totAmt}" disabled=disabled><br>
           </form>
        </div>
        <div id="footer">
        	<input type="submit" value="삭제" formaction="${cp }/member/delete?mbNo=${mbrDto.mbrNo}" id="delBtn">&nbsp;&nbsp;
            <input type="submit" value="수정" formaction="${cp }/member/update?mbNo=${mbrDto.mbrNo}" id="modBtn" >&nbsp;&nbsp;
            <input type="button" value="취소" id="backBtn" onclick = "history.back(); return false;">
        </div>
    </div>
</body>
<script type="text/javascript">
window.onload=function(){
	if(${not empty errMsg}){
		{alert(${errMsg})
			};
	}
}

function modMember(){
	location.href="${cp}/member/update"
}
function delMember(){
	location.href="${cp}/member/delete"
}

const form  = document.modForm;
function checkValue() {
	if(!form.pwd.value){
        alert("비밀번호를 입력하세요.");
        form.pwd.focus();
    }else if(!((/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/).test(pwd))){
    		alert('비밀번호는 영어/숫자/특수문자를 조합해 8-12자로 이루어져야 합니다.');
    }else if(!form.pwdCheck.value){
        alert("비밀번호를 확인하세요.");
        form.pwdCheck.focus();
    } else if(form.pwd.value != form.pwdCheck.value ){// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인  
        alert("비밀번호를 동일하게 입력하세요.");
        return false;
    }else if(!form.name.value){
        alert("이름을 입력하세요.");
        return false;
    }else if(!form.bd.value){
        alert("생년월일을 입력하세요.");
        return false;
    }else if(isNaN(form.tel.value)){
        alert("전화번호는 숫자만 입력가능 합니다.");
        return false;
    }else if(!form.addr.value){
        alert("주소를 입력하세요.");
        return false;
    }else if(!form.email.value){
        alert("이메일을 입력하세요.");
        return false;
   	} else {
        return true;
    }
	
}
</script>
</html>