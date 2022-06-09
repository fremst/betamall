<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	session.setAttribute("id", "user");
--%>
<div>
	<ul>
	<c:choose>
		<c:when test="${empty sessionScope.id}">
			<li><a href="${cp}/home/login">로그인</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${cp}/home/logout">로그아웃</a></li>
		</c:otherwise>
	</c:choose>
	<li><a href="${cp}/home/join">회원가입</a></li>
	<c:choose>
		<c:when test="${sessionScope.id == 'user'}">
			<li><a href="${cp}/user/mypage">나의 쇼핑 정보</a></li>
		</c:when>
		<c:when test="${sessionScope.id == 'admin'}">
			<li><a href="${cp}/admin/mypage">매장 관리</a></li>
		</c:when>
	</c:choose>
	</ul>
</div>