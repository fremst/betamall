<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>result</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.result=='success'}">
        <h1>요청작업에 성공하였습니다..</h1>
    </c:when>
    <c:otherwise>
        <h1>요청작업에 실패하였습니다..</h1>
    </c:otherwise>
</c:choose>
<a href="main.jsp">main</a>
</body>
</html>