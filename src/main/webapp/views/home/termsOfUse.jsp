<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관동의</title>
<script>
form.addEventListener('submit', (e) => e.preventDefault());//새로고침 방지

</script>
</head>
<body>
<form action="${cp }/join" method="get" id= "termsForm">
<div name = "term1">
<input type ="checkbox" id = "termCheck" checked="isCheck()">이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택), 프로모션 정보 수신(선택)에 동의합니다.
</div>
<input type="button" value="취소" onclick="window.close()">
<input type="submit" value="확인">
</form>
</body>
</html>