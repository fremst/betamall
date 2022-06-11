<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점장 등록</title>
</head>
<body>
    <h1>점장 등록</h1>
    <div>
        <!-- url 수정-->
        <form method="post" action="${cp }/mgrinsert" enctype="multipart/form-data">
            <label for = "mgrName">성명:</label><br>
            <input type= "text" name = "mgrName"><br>
            <label for = "mgrTel">전화번호: </label><br>
            <input type= "text" name = "mgrTel"><br>
            <label for = "mgrEmail">이메일: </label><br>
            <input type= "text" name= "mgrEmail"><br>
            <label for = "mgrId">아이디: </label><br>
            <input type= "text" name= "mgrId"><br>
            <label for = "mgrPwd">초기비밀번호: </label><br>
            <input type= "text" name = "mgrPwd"><br>
            <select name = 'brName'>
            	<c:forEach var="b" items="${brDtos}">
            		<option>${b.brName }</option>
            	</c:forEach>
            </select>
            <br>
            <input type = "file" name = "uploadedFile"><br>
            <input type="submit" value="등록">
        </form>
    </div>
</body>
</html>