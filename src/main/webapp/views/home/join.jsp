<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
   <!-- <script type="text/javascript">
//         // 회원가입 화면의 입력값들을 검사한다.        
//         function checkValue() {
//             var form  = document.userInfo;
//             //아이디 폼 체크
//             if(!form.id.value){
//                 alert("아이디를 입력하세요.");
//                 return false;
//             }else if(!(id<"0"||id>"9") && (id<"A" || id>"Z") && (id<"a" || id>"z")){
//                 alert("아이디에 특수문자는 사용할 수 없습니다.");
//             }else if(!(id.length >3 || id.length <13)){
//                 alert("아이디는 4~12자리 영문자와 숫자입니다.");
//             }

//             if(!form.pwd.value){
//                 alert("비밀번호를 입력하세요.");
//                 return false;
//    <!--  <script type="text/javascript">
//         // 회원가입 화면의 입력값들을 검사한다.        
//         function checkValue() {
//            const form  = document.userInfo;
//             //아이디 폼 체크
//             if(!form.id.value){
//                 alert("아이디를 입력하세요.");
//                 form.id.focus();
//             }
//             //아이디 중복체크 여부 확인
//             if(form.idDuplication.value !="idCheck"){
//                 alert("아이디 중복체크를 하세요.");
//                 return false;
//             }
//             if(!form.pwd.value){
//                 alert("비밀번호를 입력하세요.");
//                 form.pwd.focus();
//             }
//             // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인           
//             if(form.pwd.value != form.pwdCheck.value ){
//                 alert("비밀번호를 동일하게 입력하세요.");
//                 return false;
//             }
//             if(!form.name.value){
//                 alert("이름을 입력하세요.");
//                 return false;
//             }
//             if(!form.bd.value){
//                 alert("생년월일을 입력하세요.");
//                 return false;
//             }
//             if(isNaN(form.tel.value)){
//                 alert("전화번호는 숫자만 입력가능 합니다.");
//                 return false;
//             }
//            if(!form.addr.value){
//                 alert("주소를 입력하세요.");
//                 return false;
//             }
//             if(!form.email.value){
//                 alert("이메일을 입력하세요.");
//                 return false;
//             } 
//         }
//         //아이디 중복체크 창
//         var xhr = null;
//         function idcheck() {
//             xhr = new XMLHttpRequest();
//             xhr.onreadystatechange=success;
//             let id = document.getElementById("id").value;
//             if(id==""){
//             	document.getElementById("idcheck").innerHTML="";
//             	return;
//             }
//             xhr.open('get', '${cp}/idCheck?id=' + id, true);
//             xhr.send();
//         }

//         function success() {
//             if (xhr.readyState == 4 && xhr.status == 200) {
//             	let data = xhr.responseText;
// 				let idInfo = JSON.parse(data)[0];
//                 let span = document.getElementById("idcheck");
//                 if(idInfo=='fail'){
//                 span.innerHTML="사용중인 아이디입니다."
//                 }else{
//                     span.innerHTML="사용가능한 아이디입니다."
//                 }
//             }
//         } 
        
//         function sendTermsOfUse(){
//             window.name = "parentForm";
//             window.open("termsOfUse.jsp","checkForm","width=500, height=800, resizable=no, scrollbars=yes");
//         }
//     </script>
// </head>
// <body>
// <h2>기본 정보 입력</h2>
// <form action="${cp }/join" method="post" name = "userInfo" onsubmit="return checkValue()">
//     아이디<input type="text" name="id" id="id" onkeyup="idcheck()">
//     <span id="idcheck"></span><br>
//     비밀번호<input type="password" name="pwd"><br>
//     비밀번호 확인<input type="password" name="pwdCheck"> <br>
//     이름<input type="text" name="name"><br>
//     생년월일<input type="date" name="bd"><br>
//     전화번호<input type="text" name="tel"><br>
//     주소<input type="text" name="addr"><br>
//     이메일<input type="email" name="email"><br>
//     약관동의 페이지<input type="button" value="약관동의 페이지 이동" name="terms" onclick=sendTermsOfUse()>
//      <c:choose>
//         <c:when test="${empty sessionScope.id }">
//            <input type="submit" value="회원가입"><br>
//         </c:when>
//         <c:otherwise>
//            <input type="submit" value="회원가입" ><br>
//         </c:otherwise>
//     </c:choose>
//                 form.name.focus();
//             }
//             if(!form.bd.value){
//                 alert("생년월일을 입력하세요.");
//                 form.bd.focus();
//             }
//             if(isNaN(form.tel.value)){
//                 alert("전화번호는 숫자만 입력가능 합니다.");
//                form.tel.focus();
//             }
//            if(!form.addr.value){
//                 alert("주소를 입력하세요.");
//                 form.addr.focus();
//             }
//             if(!form.email.value){
//                 alert("이메일을 입력하세요.");
//                 form.email.focus();
//             }  -->
        } -->
        <%-- //아이디 중복체크 창
        /* function dbcheckId(){
        	const form  = document.userInfo;
            const id= form.id.value;
            if(!form.id.value){
                alert("아이디를 입력하세요.");
                form.id.focus();
            }else{
               window.open("${cp }/checkId?id="+id,"idCheckForm","width=300, height=200, resizable=no, scrollbars=no");
            }
        } */
        
        function idCheck(){
        	window.open("/views/home/checkId.jsp","Form","width=300px height=100px");
        }
        //중복체크 필수
        function inputIdCheck(){
            const dbCheckId = document.getElementById("dbCheckId");
           // document.form.idDuplication.value="idUncheck";
            //dbCheckId.disabled=false;
            //dbCheckId.style.opacity=1;
            //dbCheckId.style.cursor="pointer";
        }
        
        function sendTermsOfUse(){
            window.open("${cp}/views/home/termsOfUse.jsp","checkForm","width=850, height=500, resizable=no, scrollbars=yes");
        }
    </script> --%>
</head>
<body>
<h2>기본 정보 입력</h2>
<form action="${cp }/join" method="post" name = "userInfo"> <!-- onsubmit="return checkValue()" -->
    아이디<input type="text" name="id"> <br>
  <!--  <input type="button" onclick="idCheck()" value="idCheck"><br> -->
    <!--아이디 중복체크 여부-->
    <!-- <input type="text" name="idDuplication" value="idUncheck" id="dbCheckId"> -->
    비밀번호<input type="password" name="pwd"><br>
   <!--  비밀번호 확인<input type="password" name="pwdCheck"> <br> -->
    이름<input type="text" name="name"><br>
    생년월일<input type="date" name="bd"><br>
    전화번호<input type="text" name="tel" placeholder = "01012345678"><br>
    주소<input type="text" name="addr"><br>
    이메일<input type="email" name="email"><br>
    <!-- 약관동의 페이지<input type="button" value="약관동의 페이지 이동" name="terms" onclick="sendTermsOfUse()"> -->
<%--      <c:choose>
        <c:when test="${empty sessionScope.id }">
           <input type="submit" value="회원가입"><br>
        </c:when>
        <c:otherwise> --%>
           <input type="submit" value="회원가입" ><br>
<%--         </c:otherwise>
    </c:choose> --%>
</form>
</body>
</html>