<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>main.jsp</title>
</head>
<body>
<ul>
    <li><a href="${cp }/join">회원가입</a></li>
    <!-- 로그인하면 home페이지로 이동되고 로그아웃메뉴가 보여짐
        로그아웃을 클릭하면 home페이지로 이동되고 로그인메뉴가 보여짐
    -->
    <c:choose>
        <c:when test="${empty sessionScope.id }">
            <li><a href="${cp }/login">회원로그인</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${cp }/logout">로그아웃</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</body>
</html>