<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title> 
<link rel="stylesheet" href="${cp}/resources/css/mbrJoinFrom.css">
<link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<script type="text/javascript">
let exists = -1;

function resetCheck(){
	exists = -1;
}

function checkValue() { //회원가입 화면의 입력값들을 검사한다.
//const form  = document.userInfo;
let id = document.getElementById('id').value;
let pwd = document.getElementById('pwd').value;
let pwdCheck = document.getElementById('pwdCheck').value;
let name = document.getElementById('name').value;
let bd = document.getElementById('bd').value;
let tel = document.getElementById('tel').value;
let addr = document.getElementById('sample6_address').value;
let email = document.getElementById('email').value;

    if(!id){ //아이디 폼 체크
        alert("아이디를 입력하세요.");
        return false;
    }else if(exists == -1){ //아이디 중복체크 여부 확인
        alert("아이디 중복체크를 하세요.");
        return false;
    }else if(exists == 1){
		alert('다른 아이디를 사용해주세요.');
		return false;
	}else if(!pwd){
        alert("비밀번호를 입력하세요.");
        return false;
    }else if(!((/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/).test(pwd))){
		alert('비밀번호는 영어/숫자/특수문자를 조합해 8-12자로 이루어져야 합니다.');
		return false;
    }else if(pwd != pwdCheck){ // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인 
        alert("비밀번호를 동일하게 입력하세요.");
        return false;
    }else if(!name){
        alert("이름을 입력하세요.");
        return false;
    }else if(!bd){
        alert("생년월일을 입력하세요.");
        return false;
    }else if(!tel){
        alert("전화번호를 입력하세요.");
        return false;
    }else if(isNaN(tel)){
        alert("전화번호는 숫자만 입력가능 합니다.");
        return false;
    }else if(!addr){
        alert("주소를 입력하세요.");
        return false;
    }else if(!email){
        alert("이메일을 입력하세요.");
        return false;
    } else {
    return true;
    }
}

function checkId(){
	let id = document.getElementById("id").value;
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			exists = JSON.parse(xhr.responseText).exist;
			if(exists == 1){
				alert('사용중인 아이디입니다.');
			}else if(!(/^[a-z0-9]{4,12}$/g).test(id)){
				alert('아이디는 영어 소문자로 시작하며, 소문자 및 숫자를 포함해 4-12자로 이루어져야 합니다.');
			} else { 
				alert('사용 가능한 아이디입니다.');
			}
		}
	}
	xhr.open('post',"${cp }"+'/idcheck?id='+id,true);
	xhr.send();
}
</script>
<body>
<form method="post" name = "userInfo" id="userInfo" onsubmit="return checkValue()"> 
<span style="margin-left: 20px;"><h1>&nbsp;&nbsp;회원가입</h1></span><br>
    <hr>
<fieldset id=form>
<legend>회원 가입양식</legend>
<div style="color: red; font-size: 12px;">${errMsg}</div>
    <br>아이디&nbsp;&nbsp;&nbsp;<input type="text" name="id" id ="id" onkeypress="resetCheck()">&nbsp;&nbsp;
   <input type="button" onclick="checkId()" value=중복확인><span id= "setForm"> 4자~12자리 영문자,숫자/특수기호 제외</span><br><br>
    비밀번호&nbsp;&nbsp;<input type="password" name="pwd" id="pwd" >
    <span id= "setForm"> 영문 대문자, 소문자, 숫자, 특수문자를 3가지 이상 사용하여 8자 20자 이하로 설정하십시오.</span><br><br>
    비밀번호 확인 <input type="password" name="pwdCheck" id="pwdCheck"><br><br>
    이름&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" id="name"><br><br>
    생년월일&nbsp;&nbsp;&nbsp;<input type="date" name="bd" id="bd"><br><br>
    전화번호&nbsp;&nbsp;&nbsp;<input type="text" name="tel" id="tel" placeholder = "숫자만 입력해주세요."><br><br>
    주소 &nbsp;&nbsp;<input type="text" id="sample6_postcode"  name =postno placeholder="우편번호">&nbsp;&nbsp;
    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
    <input type="text" id="sample6_address" name="addr" placeholder="주소"><br>
    <input type="text" id="sample6_detailAddress" name="addr1" placeholder="상세주소"><br>
    <input type="text" id="sample6_extraAddress" name="addr2" placeholder="참고항목"><br><br>
    이메일&nbsp;&nbsp;&nbsp;<input type="email" name="email" id="email"><br><br>
    <div id=btnField>
    <input type="submit" value="회원가입" id=joinBtn>&nbsp;&nbsp;
    <input type="button" value="취소" id="backBtn" onclick = "history.back(); return false;">
    </div>
</fieldset>
</form>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
</script>
</html>