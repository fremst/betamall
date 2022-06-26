<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
    <link rel="stylesheet" href="${cp}/resources/css/loginForm.css">
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
</head>
<body>
<h2 id=title>비밀번호 찾기</h2>
<hr>
<div id="loginWrap">
    <form action="${cp}/login/searchPwd" method="post">
        <fieldset id="loginField">
            <legend>비밀번호 찾기 양식</legend>
            아&nbsp;&nbsp;이&nbsp;&nbsp;디&nbsp;&nbsp;<input type="text" name="id" id="id"><br><br>
            이&nbsp;&nbsp;메&nbsp;&nbsp;일&nbsp;&nbsp;<input type="text" name="email" id="email"><br><br>
            전화번호&nbsp;&nbsp;<input type="text" name="tel" id="pwd"><br><br>
            <div id="errBox">${pwd}</div>
            <input type="submit" value="비밀번호찾기" id=loginBtn class="btn">
        </fieldset>
    </form>
    <div id=searchBox>
        <a href="${cp }/login" id="idTag">로그인</a>&nbsp;|
        <a href="${cp }/login/searchId" id="pwdTag">아이디 찾기</a>
    </div>
</div>
</body>
</html>