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
							<li><span>환영합니다! ${id}님</span></li>
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
				<c:choose>
					<c:when test="${(role == 'admin0' || role == 'admin') && empty adminPage}">
						<li><a href="${cp}/admin/item/list">관리자페이지</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${cp}/home">홈으로</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="cate">
			<ul class="nav2">
				<c:choose>
					<c:when test="${role == 'member' || empty adminPage}">
						<li><a href="${cp}/item/search?field=mcatName&keyword=수납">수납</a></li>
						<li><a href="${cp}/item/search?field=mcatName&keyword=필기류">필기류</a></li>
						<li><a href="${cp}/item/search?field=mcatName&keyword=일반사무용품">일반사무용품</a></li>
						<li><a href="${cp}/item/search?field=mcatName&keyword=파일/바인더">파일/바인더</a></li>	
					</c:when>
					<c:when test="${role == 'admin0'}">
						<li><a href="${cp}/admin/item/list">상품 관리</a></li>
						<li><a href="${cp}/admin/sales/list">매출 조회</a></li>
						<li><a href="${cp}/admin/board/list">공지/FAQ/이벤트</a></li>
						<li><a href="${cp}/admin/qna/list">Q&A</a></li>
						<li><a href="${cp}/admin/admin/mbrList">고객 조회</a></li>
						<li><a href="${cp}/admin/branch/list">지점 관리</a></li>
						<li><a href="${cp}/admin/manager/list">점장 관리</a></li>
					</c:when>
					<c:when test="${role == 'admin'}">
						<li><a href="${cp}/admin/item/list">등록 상품 조회</a></li>
						<li><a href="${cp}/admin/stock/list">재고 관리</a></li>
						<li><a href="${cp}/admin/sales/list">매출 조회</a></li>
						<li><a href="${cp}/admin/board/list">공지/FAQ/이벤트</a></li>
						<li><a href="${cp}/admin/qna/list">Q&A</a></li>
						<li><a href="${cp}/admin/admin/mbrList">고객 조회</a></li>
						<li><a href="${cp}/admin/branch/list">지점 조회</a></li>
						<li><a href="${cp}/admin/manager/list">점장 조회</a></li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</header>
</body>
</html>