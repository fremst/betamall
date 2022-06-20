<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <link rel="stylesheet" href="${cp}/resources/css/loginForm.css">
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<body>
<h2 id=title>로그인</h2>
<hr>
<form action="${cp}/login" method="post">
    <fieldset id="loginField">
        <legend>로그인 양식</legend>
        아이디&nbsp;<input type="text" name="id" id="id"><br><br>
        비밀번호&nbsp;<input type="password" name="pwd" id="pwd"><br><br>
        <div id="errBox">${errMsg}</div>
        <input type="submit" value="로그인" id=loginBtn class="btn">
    </fieldset>
</form>
<div id=searchBox>
    <a href="${cp }/login/searchId" id="idTag">아이디 찾기</a>&nbsp;|
    <a href="${cp }/login/searchPwd" id="pwdTag">비밀번호 찾기</a>
</div>
</body>
</html>
