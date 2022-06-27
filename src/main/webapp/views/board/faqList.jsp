<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/board.css">
</head>
<body>
	<div id="boardWrap">
		<div id="formSide">
	        <h3>고객센터</h3>
	        <ul>
	            <li class="sidemenu"><a href="${cp }/board/list">- 공지사항/이벤트</a></li>
	            <li class="sidemenu"><a href="${cp }/board/faqlist">- FAQ</a></li>
	            <li class="sidemenu"><a href="${cp }/board/qnalist">- Q&A</a></li>
	        </ul>
	    </div>
	    <div id="boardMain">
	   		<h3 id="boardTitle">FAQ</h3>
	   		<div>
				<c:choose>
					<c:when test="${not empty list }">
						<table id="boardTable">
						<tr>
							<th class="boardInfo_header">글번호</th>
							<th class="boardInfo_header">카테고리</th>
							<th class="boardInfo_header">제목</th>
							<th class="boardInfo_header">작성자</th>
							<th class="boardInfo_header">작성일</th>
						</tr>
						<c:forEach var="dto" items="${list }">
							<tr>
								<td class="boardInfo">${dto.brdNo }</td>
								<td class="boardInfo">${dto.brdCat }</td>
								<td class="boardInfo"><a href="${cp}/board/faqdetail?brdNo=${dto.brdNo}">${dto.brdTitle }</a></td>
								<td class="boardInfo">${dto.mgrId }</td>
								<td class="boardInfo">${dto.brdWdate }</td>
							</tr>
						</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<div class = "nothing" style="width: 1010px;">
							<hr style="height: 2px; background-color: #707070">
							<h4 style="text-align: center; margin-top: 15px;">검색 항목이 없습니다.</h4>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="boardListNum">
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a href="${cp }/board/faqlist?pageNum=${i}&field=${field}&keyword=${keyword}">
							<span id="SrowNum">${i }</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${cp}/board/faqlist?pageNum=${i }&field=${field}&keyword=${keyword}">
							<span id="rowNum">${i }</span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div id="boardPageNav">
				<form method="post" action="${cp }/board/faqlist" id="searchBoard">
					<select name="field">
						<option value="brdCat" <c:if test="${field=='brdCat' }">selected</c:if> >카테고리</option>
						<option value="brdTitle" <c:if test="${field=='brdTitle' }">selected</c:if>>제목</option>
					</select>
					<input type="text" name="keyword" value="${keyword }" id="boardList_search">
					<input type="submit" value="검색" id="boardList_search_btn">
				</form>
				<c:if test="${role == 'admin0' || role == 'admin'}">
						<a href="${cp }/board/faqinsert">게시글 작성</a>&nbsp;|
				</c:if>
				<a href="${cp }/board/faqlist" id="idTag">전체글 보기</a>&nbsp;|
		        <a href="${cp }/home" id="pwdTag">Home</a>
			</div>
		</div>
	</div>
</body>
</html>