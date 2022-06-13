<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../../resources/css/layout.css">
<%--
	session.setAttribute("id", "user");
--%>
<meta charset="UTF-8">
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
				<form>
					<input type="search" placeholder="Search" id="search">
					<input type="submit" value="검색" id="search_btn">
				</form>
			</div>
			<div class="logo">
				<a href="#"><img src="${cp }/resources/images/betamall.png"></a>
			</div>
			<ul class="nav">
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
				<li><a href="#">마이페이지</a></li>
				<li><a href="#">고객센터</a></li>
			</ul>
		</div>
		<div class="cate">
			<ul class="nav2">
				<li><a href="#">수납</a></li>
				<li><a href="#">필기류</a></li>
				<li><a href="#">일반사무용품</a></li>
				<li><a href="#">화일/바인더</a></li>	
			</ul>
		</div>
	</header>
</body>
</html>