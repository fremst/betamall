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
	<c:if test="${role == 'admin0' || role == 'admin' || role == 'member' }">
		<form method="post" action="${cp }/board/qnacmtinsert" name="insertForm">
			<textarea rows="5" cols="100" name="qnaCmtCon" id="qnaCmtCon" placeholder="댓글을 입력해주세요."></textarea>
			<input type="text" name="qnaNo" value="${dto.qnaNo }" style="display: none">
			<input type="button" value="등록" onclick="checkSpace()">
		</form>
	</c:if>
	<c:if test="${not empty list}">
		<c:forEach var="cmt" items="${list }">
		<c:if test="${cmt.qnaCmtDel == 'true' }">
			<p>삭제된 댓글입니다.</p>
			------------------------------------------------------------------------------<br>
		</c:if>
		<c:if test="${cmt.qnaCmtDel == 'false' }">
			<p>${cmt.qnaCmtCon }</p>
			작성일자 <p>${cmt.qnaCmtWdate }</p>
				<c:if test="${role == 'admin0' || role == 'admin' || dto.mbrNo == mbrNo }">
					<button onclick="location.href='${cp }/board/qnacmtdelete?qnaCmtNo=${cmt.qnaCmtNo}'">댓글삭제</button><br>
				</c:if>
			------------------------------------------------------------------------------<br>
		</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${role == 'admin0' || role == 'admin' || dto.mbrNo == mbrNo }">
		<br><button onclick="location.href='${cp }/board/qnaupdate?qnaNo=${dto.qnaNo}'">수정</button>
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
	
	function checkSpace() {
		let qnaCmtCon=document.getElementById("qnaCmtCon");
		if(qnaCmtCon.value=='' || qnaCmtCon.value==null) {
			alert("내용을 입력해주세요.")
			return false;
		}else {
			document.insertForm.submit();
		}
	}
</script>
</html>
