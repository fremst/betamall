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
	<h1>상세글보기</h1>
	<label>제목</label><br>
	<input type="text" value="${dto.qnaTitle }" readonly="readonly"><br>
	<label>내용</label><br>
	<textarea rows="10" cols="100" name="qnaCon" readonly="readonly">${dto.qnaCon }</textarea><br>
	<c:choose>
		<c:when test="${idto.itemName==null }">
		</c:when>
		<c:otherwise>
			<h2>${idto.itemName }</h2>
			<img src="${cp}/resources/uploads/admin/item/${idto.tImg }" style='width: 500px; height:500px'><br>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${dto.qnaFile==null }">
		</c:when>
		<c:otherwise>
			<img src="${cp}/resources/uploads/admin/board/${dto.qnaFile }" style='width: 500px; height:500px'><br>
		</c:otherwise>
	</c:choose>
	<c:if test="${role == 'admin0' || role == 'admin' || dto.mbrNo == mbrNo }">
		<button onclick="location.href='${cp }/board/qnaupdate?qnaNo=${dto.qnaNo}'">수정</button>
		<button id="del" onclick="deleteCheck()">삭제</button>
	</c:if>
	<button onclick="location.href='${cp }/board/qnalist'">목록으로</button>
</body>
<script type="text/javascript">
	function deleteCheck() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			location.href='${cp }/board/qnadelete?qnaNo=${dto.qnaNo}';
		}else {
			return false;
		}
	}
</script>
</html>