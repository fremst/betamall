<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../../resources/css/layout.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<!--header-->
</head>
<body>
	<header>
	<!--header-->
		<div class="headers">
			<div class="search_area">
				<form action = "${cp}/item/search">
					<input type="search" name=itemName placeholder="상품명 입력" id="search">
					<input type="submit" value="검색" id="search_btn">
				</form>
			</div>
			<div class="logo">
				<a href="${cp }/home"><img src="${cp }/resources/images/betamall.png" ></a>
			</div>
			<ul class="nav">
				<c:if test="${not empty role}">
					<c:choose>
						<c:when test="${role == 'admin0'}">
							<li><span>안녕하세요! 총관리자님</span></li>
						</c:when>
						<c:when test="${role == 'admin'}">
							<li><span>안녕하세요! 점장님</span></li>
						</c:when>
						<c:when test="${role == 'member'}">
							<li><span>환영합니다! ${id }님</span></li>
							<li><a href="${cp }/member/mypage">마이페이지</a></li>
							<li><a href="#">고객센터</a></li>
						</c:when>
					</c:choose>
					<li><a href="${cp }/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${empty role}">
					<li><a href="${cp }/login">로그인</a></li>
					<li><a href="${cp }/join/termsofuse">회원가입</a></li>
				</c:if>
				<c:if test="${role == 'admin0' || role == 'admin'}">
					<li><a href="${cp }/admin/main">관리자페이지</a></li>
				</c:if>
			</ul>
		</div>
		<div class="cate">
			<ul class="nav2">
			<c:choose>
				<c:when test="${role == 'member' }">
					<li><a href="#">수납</a></li>
					<li><a href="#">필기류</a></li>
					<li><a href="#">일반사무용품</a></li>
					<li><a href="#">화일/바인더</a></li>
				</c:when>
				<c:when test="${role == 'admin0' }">
					<li><a href="${cp }/admin/item/list">상품 관리</a></li>
					<li><a href="#">매출 조회</a></li>
					<li><a href="#">고객 조회</a></li>
					<li><a href="#">지점 관리</a></li>
					<li><a href="#">점장 관리</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#">상품 관리</a></li>
					<li><a href="#">매출 조회</a></li>
					<li><a href="#">고객 조회</a></li>
					<li><a href="#">지점 관리</a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</header>
</body>
</html>