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
	<input type="text" value="${dto.brdTitle }" readonly="readonly"><br>
	<label>내용</label><br>
	<textarea rows="10" cols="100" name="brdContent" readonly="readonly">${dto.brdCon }</textarea><br>
	<c:choose>
		<c:when test="${dto.brdImg==null }">
		</c:when>
		<c:otherwise>
			<img src="${cp}/resources/uploads/admin/board/${dto.brdImg }" style='width: 500px; height:500px'><br>
		</c:otherwise>
	</c:choose>
	<button onclick="location.href='${cp }/board/update?brdNo=${dto.brdNo}'">수정</button>
	<button id="del" onclick="deleteCheck()">삭제</button>
	<button onclick="location.href='${cp }/board/list'">목록으로</button>
</body>
<script type="text/javascript">
	function deleteCheck() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			location.href='${cp }/board/delete?brdNo=${dto.brdNo}';
		}else {
			return false;
		}
	}
</script>
</html>