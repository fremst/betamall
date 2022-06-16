<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <link rel="stylesheet" href="${cp}/resources/css/loginForm.css">
	<link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<body>
<h1 id=title>비밀번호 찾기</h1><hr>
<form action="${cp}/login/searchPwd" method="post">
<fieldset id = "loginField">
<legend>비밀번호 찾기 양식</legend>
	아이디&nbsp;<input type="text" name="id" id="id"><br><br>  
	이메일&nbsp;<input type="text" name="email" id="email"><br><br>  
    전화번호&nbsp;<input type="text" name="tel" id="pwd"><br><br>
    <div id="errBox" >${pwd}</div>
    <input type="button" value="로그인" onclick="location.href='${cp }/login'" id="idBtn" class="btn">&nbsp;&nbsp;
    <input type="button" value="아이디찾기" onclick="location.href='${cp }/login/searchId'" id="pwdBtn" class="btn">&nbsp;&nbsp;
    <input type="submit" value="비밀번호찾기" id=loginBtn class="btn">  
</fieldset>
</form>
</body>
</html>