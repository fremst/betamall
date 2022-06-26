<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <link rel="stylesheet" href="${cp}/resources/css/loginForm.css">
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<body>
<h2 id=title>아이디찾기</h2>
<hr>
<div id="loginWrap">
    <form action="${cp}/login/searchId" method="post">
        <fieldset id="loginField">
            <legend>아이디 찾기 양식</legend>
            이&nbsp;&nbsp;메&nbsp;&nbsp;일&nbsp;&nbsp;<input type="text" name="email" id="id"><br><br>
            전화번호&nbsp;&nbsp;<input type="text" name="tel" id="pwd"><br><br>
            <div id="errBox">${id}</div>
            <input type="submit" value="아이디찾기" id=loginBtn class="btn">
        </fieldset>
    </form>
    <div id=searchBox>
        <a href="${cp }/login" id="idTag">로그인</a>&nbsp;|
        <a href="${cp }/login/searchPwd" id="pwdTag">비밀번호 찾기</a>
    </div>
</div>
</body>
</html>