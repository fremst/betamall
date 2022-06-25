<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
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
	            <li class="sidemenu"><a href="${cp }/board/list">ㆍ공지사항/이벤트</a></li>
	            <li class="sidemenu"><a href="${cp }/board/faqlist">ㆍFAQ</a></li>
	            <li class="sidemenu"><a href="${cp }/board/qnalist">ㆍQ&A</a></li>
	        </ul>
	    </div>
	    <div id="boardMain">
	    	<h3 id="boardTitle">Q&A</h3>
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
							<td class="boardInfo">${dto.qnaNo}</td>
						<c:choose>
							<c:when test="${dto.qnaDel=='true' || fn:contains(dto.mbrId, '-')=='true' }">
								<td class="boardInfo" style="color: #979897;">-</td>
								<td class="boardInfo" style="color: #979897;">삭제된 게시글입니다.</td>
								<td class="boardInfo" style="color: #979897;">-</td>
								<td class="boardInfo" style="color: #979897;">-</td>
							</c:when>
							<c:otherwise>
								<td class="boardInfo">${dto.qnaCat }</td>
								<c:choose>
									<c:when test="${dto.secret=='true' }">
										<c:choose>
											<c:when test="${dto.mbrId == id || role == 'admin' || role == 'admin0'}">
												<td class="boardInfo"><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }" style="color: #979897 ;">🔒비밀글로 설정되었습니다.</a></td>
											</c:when>
											<c:otherwise>
												<td class="boardInfo"><a href="" style="color: #979897 ;" onclick="alert('본인글만 확일할 수 있습니다.')">🔒비밀글로 설정되었습니다.</a></td>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<td class="boardInfo"><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }">${dto.qnaTitle }</a></td>
									</c:otherwise>
								</c:choose>
								<td class="boardInfo">${dto.mbrId }</td>
								<td class="boardInfo">${dto.qnaWdate }</td>							
							</c:otherwise>
						</c:choose>
						</tr>
					</c:forEach>
					</table>
				</c:when>
			<c:otherwise>
				<div class = "nothing">
					<h3>검색 항목이 없습니다.</h3>
				</div>
			</c:otherwise>
			</c:choose>
			</div>
			<div id="boardListNum">
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${i==pageNum }">
							<a href="${cp }/board/qnalist?pageNum=${i}&field=${field}&keyword=${keyword}">
								<span id="SrowNum">${i }</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${cp}/board/qnalist?pageNum=${i }&field=${field}&keyword=${keyword}">
								<span id="rowNum">${i }</span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div id="boardPageNav">
				<form method="post" action="${cp }/board/qnalist" id="searchBoard">
					<select name="field">
						<option value="qnaCat" <c:if test="${field=='qnaCat' }">selected</c:if> >카테고리</option>
						<option value="qnaTitle" <c:if test="${field=='qnaTitle' }">selected</c:if>>제목</option>
						<option value="mbrId" <c:if test="${field=='mbrId' }">selected</c:if>>작성자</option>
					</select>
					<input type="text" name="keyword" value="${keyword }" id="boardList_search">
					<input type="submit" value="검색" id="boardList_search_btn">
				</form>
				<c:if test="${not empty role}">
					<a href="${cp }/board/qnainsert">게시글 작성</a>&nbsp;|
				</c:if>
				<a href="${cp }/board/qnalist" id="idTag">전체글 보기</a>&nbsp;|
		        <a href="${cp }/home" id="pwdTag">Home</a>
			</div>
		</div>
	</div>
</body>
</html>
