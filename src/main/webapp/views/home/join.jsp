<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>기본 정보 입력</h2>
<form action="${cp }/join" method="post">
    아이디<input type="text" name="id">
    <!-- <input type="button" value="중복확인" name="idCheck"><br> -->
    비밀번호<input type="password" name="pwd"><br>
    <!-- 비밀번호 확인<input type="password" name="pwdCheck"><br>  //업데이트 예정-->
    이름<input type="text" name="name"><br>
    생년월일<input type="date" name="bd"><br>
    전화번호<input type="text" name="tel"><br>
    주소<input type="text" name="addr"><br>
    이메일<input type="email" name="email"><br>
    <input type="submit" value="회원가입"><br>
</form>
</body>
</html>