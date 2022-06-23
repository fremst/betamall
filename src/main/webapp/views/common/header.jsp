<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${cp}/resources/css/layout.css">
<!DOCTYPE html>
<header>
<!--header-->
	<div class="headers">
		<div class="search_area">
			<form action = "${cp}/item/search">
				<input type="hidden" name=field value="itemName">
				<input type="search" name=keyword placeholder="상품명 입력" id="search">
				<input type="submit" value="검색" id="search_btn">
			</form>
		</div>
		<div class="logo">
			<a href="${cp }/home"><img src="${cp }/resources/images/betamall.png" ></a>
		</div>
		<div class = "navBox">
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
							<li><a href="${cp}/board/list">고객센터</a></li>
						</c:when>
					</c:choose>
					<li><a href="${cp }/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${empty role}">
					<li><a href="${cp }/login">로그인</a></li>
					<li><a href="${cp }/join/termsofuse">회원가입</a></li>
				</c:if>
			</ul>
		</div>
	</div>
		<div class ="quickMenu">
		<c:choose>
			<c:when test="${role == 'admin0' || role == 'admin' }">
				<a href = "${cp}/admin/item/list"><img src = "${cp}/resources/images/adminpage.png" width = "30px"></a>
			</c:when>
			<c:otherwise>
				<a href = "${cp}/member/mypage"><img src = "${cp}/resources/images/mypage.png" width = "30px"></a>
				<a href = "${cp}/member/cart"><img src = "${cp}/resources/images/cart.png" width = "30px"></a>
			</c:otherwise>
		</c:choose>
		</div>
	<div class="cate">
		<ul class="nav2">
			<c:choose>
				<c:when test="${role == 'member' || empty adminPage}">
						<li><a href="${cp}/item/search">전체</a></li>
					<c:forEach var="i" begin="0" end="${mcatNames.size()-1}">
						<li><a href="${cp}/item/search?field=mcatName&keyword=${mcatNames[i]}">${mcatNames[i]}</a></li>
					</c:forEach>
				</c:when>
				<c:when test="${role == 'admin0'}">
					<li><a href="${cp}/admin/item/list">상품 관리</a></li>
					<li><a href="${cp}/admin/sales/list">매출 조회</a></li>
					<li><a href="${cp}/board/list">공지/FAQ/이벤트</a></li>
					<li><a href="${cp}/board/qnalist">Q&A</a></li>
					<li><a href="${cp}/admin/mbrlist">고객 조회</a></li>
					<li><a href="${cp}/admin/branch/list">지점 관리</a></li>
					<li><a href="${cp}/admin/manager/list">점장 관리</a></li>
				</c:when>
				<c:when test="${role == 'admin'}">
					<li><a href="${cp}/admin/item/list">등록 상품 조회</a></li>
					<li><a href="${cp}/admin/order/list">주문 관리</a></li>
					<li><a href="${cp}/admin/sales/list">매출 조회</a></li>
					<li><a href="${cp}/board/list">공지/FAQ/이벤트</a></li>
					<li><a href="${cp}/board/qnalist">Q&A</a></li>
					<li><a href="${cp}/admin/mbrlist">고객 조회</a></li>
					<li><a href="${cp}/admin/branch/list">지점 조회</a></li>
					<li><a href="${cp}/admin/manager/list">점장 조회</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>
</header>