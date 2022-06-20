<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${requestScope.code=='success' }">
		<h1>제품의 판매가 중단되었습니다.</h1>
		<a href="${cp }/admin/item/list">상품목록으로 돌아가기</a>
	</c:when>
	<c:otherwise>
		<h1>제품 판매 중단에 실패했습니다.</h1>
		<a href="${cp }/admin/item/list">상품목록으로 돌아가기</a>
	</c:otherwise>
</c:choose>
</body>
</html>