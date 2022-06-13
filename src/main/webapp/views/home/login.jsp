<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
</head>
<body>
<h1>회원로그인</h1>
<form action="${cp}/login" method="post">
    아이디<input type="text" name="id"><br>
        <div style="color: red; font-size: 12px;">${errMsg}</div>
    비밀번호<input type="password" name="pwd"><br>
    <input type="submit" value="로그인">
</form>
</body>
</html>
