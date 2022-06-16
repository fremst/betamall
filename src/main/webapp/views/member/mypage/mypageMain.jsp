<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyPage</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <%-- <link rel="stylesheet" href="${cp}/resources/css/myPage.css"> --%>
</head>
<script type="text/javascript">
function checkValue() {
	let mbrPwd = document.getElementById("mbrPwd").value;
    let pwd = document.getElementById("pwd").value;
    let mbrTel = document.getElementById("mbrTel").value;
    let mbrAdr = document.getElementById("mbrAdr").value;
    let mbrEmail = document.getElementById("mbrEmail").value;
    if (!mbrPwd) {
        alert("비밀번호를 입력하세요.");
        mbrEmail.focus();
    } else if (!((/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/).test(mbrPwd))) {
        alert('비밀번호는 영어/숫자/특수문자를 조합해 8-12자로 이루어져야 합니다.');
    } else if (mbrPwd !== pwd) {//비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
        alert("비밀번호를 동일하게 입력하세요.");
        return false;
    } else if (isNaN(mbrTel)) {
        alert("전화번호는 숫자만 입력가능 합니다.");
        return false;
    } else if (!mbrAdr) {
        alert("주소를 입력하세요.");
        return false;
    } else if (!mbrEmail) {
        alert("이메일을 입력하세요.");
        return false;
    } else {
        return true;
    }
} 
</script>
<body>
<div id="myPageWrap">
    <div id="side">
        <h3>마이 페이지</h3>
        <ul>
            <li><a href="${cp }/member/update">나의 정보 수정</a></li>
            <li><a href=#>장바구니</a></li>
            <li><a href=#>지점 즐겨찾기</a></li>
            <li><a href=#>주문/배송 조회</a></li>
            <li><a href=#>내글보기</a></li>
            <li><a href="${cp }/member/userGrade">등급/쿠폰 조회</a></li>
        </ul>
    </div>
    <div style="color: red; font-size: 12px;">${errMsg}</div>
    <div id="center">
        <form method="post" name="modForm" onsubmit="return checkValue()">
         <fieldset id=form>
        <legend>회원 정보 수정양식</legend>
            <input type="hidden" name="mbrNo" value="${mbrDto.mbrNo}"><br>
            아이디 <input type="text"  value="${mbrDto.mbrId}" disabled=disabled><br>
            <input type="hidden" name="mbrId" value="${mbrDto.mbrId}">
            새 비밀번호 <input type="password" name=mbrPwd id=mbrPwd><br>
            새 비밀번호 확인 <input type="password" id="pwd"><br>
            이름<input type="text" name="mbrName" value="${mbrDto.mbrName}" disabled=disabled><br>
            <input type="hidden" name="mbrName" value="${mbrDto.mbrName}">
            생년월일<input type="date" value="${mbrDto.mbrBd}" disabled=disabled><br>
            <input type="date" name="mbrBd" value="${mbrDto.mbrBd}" hidden="hidden"><br>
            전화번호<input type="text" name="mbrTel" id="mbrTel" value="${mbrDto.mbrTel}" placeholder="'-'하이픈 없이 숫자만 입력"><br>
            주소<input type="text" id="sample6_postcode" name="postno" value="${mbrDto.mbrAdr}">&nbsp;&nbsp;
            <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" id="sample6_address" name="addr" id="mbrAdr" value="${mbrDto.mbrAdr}"><br>
            <input type="text" id="sample6_detailAddress" name="addr1"  value="${mbrDto.mbrAdr}"><br>
            <input type="text" id="sample6_extraAddress" name="addr2"  value="${mbrDto.mbrAdr}"><br>
            이메일<input type="email" name="mbrEmail" id="mbrEmail" value="${mbrDto.mbrEmail}"><br>
            가입일<input type="text" name="mbrRegdate" value="${mbrDto.mbrRegdate}" disabled=disabled><br>
            <input type="hidden" name="mbrRegdate" value="${mbrDto.mbrRegdate}">
            총 결제금액 <input type="text" name="totAmt" value="${mbrDto.totAmt}" disabled=disabled><br>
            <input type="hidden" name="totAmt" value="${mbrDto.totAmt}">
	           <div id=btnField>
			        <input type="submit" value="삭제" id="delBtn" onclick="if(!confirm('한 번 삭제하면 되돌릴 수 없습니다. 삭제할까요?')) {return false;}" formaction="${cp }/member/delete">&nbsp;&nbsp;
			        <input type="submit" value="수정" id="modBtn">&nbsp;&nbsp;
			        <input type="button" value="취소" id="backBtn" onclick="history.back(); return false;">
		        </div>
	        </fieldset>
        </form>
    </div>
    </div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
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
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
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