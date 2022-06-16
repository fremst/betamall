<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <link rel="stylesheet" href="${cp}/resources/css/loginForm.css">
	<link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<body>
<h1 id=title>회원로그인</h1><hr>
<form action="${cp}/login" method="post">
<fieldset id = "loginField">
<legend>로그인</legend>
	아이디&nbsp;<input type="text" name="id" id="id"><br><br>  
    비밀번호&nbsp;<input type="password" name="pwd" id="pwd"><br><br>
    <div id="errBox" >${errMsg}</div>
    <input type="button" value="아이디찾기" onclick="location.href='${cp }/login/searchId'" id="idBtn" class="btn">&nbsp;&nbsp;
    <input type="button" value="비밀번호찾기" onclick="location.href='${cp }/login/searchPwd'" id="pwdBtn" class="btn">&nbsp;&nbsp;
    <input type="submit" value="로그인" id=loginBtn class="btn">
    
</fieldset>
</form>
</body>
</html>
