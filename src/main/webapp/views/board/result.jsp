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
	<c:when test="${requestScope.code=='faqinsert' }">
		<h1>FAQ 작성 완료</h1>
		<a href="${cp }/board/faqlist">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='noticeinsert' }">
		<h1> 공지사항/이벤트 작성 완료</h1>
		<a href="${cp }/board/list">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='qnainsert' }">
		<h1> Q&A 작성 완료</h1>
		<a href="${cp }/board/qnalist">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='qnacmtinsert' }">
		<h1> 댓글 작성 완료</h1>
		<a href="${cp }/board/qnalist">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='faqdelete' }">
		<h1>FAQ 삭제 완료</h1>
		<a href="${cp }/board/faqlist">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='noticedelete' }">
		<h1>공지사항/이벤트 삭제 완료</h1>
		<a href="${cp }/board/list">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='qnadelete' }">
		<h1>Q&A 삭제 완료</h1>
		<a href="${cp }/board/qnalist">목록으로</a>
	</c:when>
	
	<c:when test="${requestScope.code=='qnacmtdelete' }">
		<h1>댓글 삭제 완료</h1>
		<a href="${cp }/board/qnalist">목록으로</a>
	</c:when>
	
	<c:otherwise>
		<h1>요청작업 실패</h1>
		<a href="${cp }/board/list">목록으로</a>
	</c:otherwise>
</c:choose>

</body>
</html>