<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="side">
        <h2>고객센터</h2>
        <ul>
            <li><a href="${cp }/board/list">ㆍ공지사항/이벤트</a></li>
            <li><a href="${cp }/board/faqlist">ㆍFAQ</a></li>
            <li><a href="${cp }/board/qnalist">ㆍQ&A</a></li>
        </ul>
    </div>
    <c:choose>
		<c:when test="${not empty list }">
			<table border="1" width="750">
			<tr>
				<th>글번호</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${dto.qnaNo}</td>
				<c:choose>
					<c:when test="${dto.qnaDel=='true' || fn:contains(dto.mbrId, '-')=='true' }">
						<td style="color: #979897;">-</td>
						<td style="color: #979897;">삭제된 게시글입니다.</td>
						<td style="color: #979897;">-</td>
						<td style="color: #979897;">-</td>
					</c:when>
					<c:otherwise>
						<td>${dto.qnaCat }</td>
						<c:choose>
							<c:when test="${dto.secret=='true' }">
								<c:choose>
									<c:when test="${dto.mbrId == id || role == 'admin' || role == 'admin0'}">
										<td><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }" style="color: #979897 ;">🔒비밀글로 설정되었습니다.</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="" style="color: #979897 ;" onclick="alert('본인글만 확일할 수 있습니다.')">🔒비밀글로 설정되었습니다.</a></td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<td><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }">${dto.qnaTitle }</a></td>
							</c:otherwise>
						</c:choose>
						<td>${dto.mbrId }</td>
						<td>${dto.qnaWdate }</td>							
					</c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
		</table>
		<div>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${i==pageNum }">
						<a href="${cp }/board/qnalist?pageNum=${i}&field=${field}&keyword=${keyword}">
						<span style="color:red">${i }</span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${cp}/board/qnalist?pageNum=${i }&field=${field}&keyword=${keyword}">
						<span style="color:gray">${i }</span>
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div>
			<form method="post" action="${cp }/board/qnalist">
				<select name="field">
					<option value="qnaCat" <c:if test="${field=='qnaCat' }">selected</c:if> >카테고리</option>
					<option value="qnaTitle" <c:if test="${field=='qnaTitle' }">selected</c:if>>제목</option>
					<option value="mbrId" <c:if test="${field=='mbrId' }">selected</c:if>>작성자</option>
				</select>
				<input type="text" name="keyword" value="${keyword }">
				<input type="submit" value="검색">
			</form>
		</div>
		</c:when>
		<c:otherwise>
			<div class = "nothing">
				<h3>검색 항목이 없습니다.</h3>
			</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty role}">
		<button onclick="location.href='${cp }/board/qnainsert'">게시글 작성</button>
	</c:if>
	<button onclick="location.href='${cp }/board/qnalist'">전체글 보기</button>
	<button onclick="location.href='${cp }/home'">Home</button>
</body>
</html>
