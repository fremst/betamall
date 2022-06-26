<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/qnaDetail.css">
</head>
<body>
	<div>
		<div id="detailWrap">
			<div id="formSide">
			    <h3>고객센터</h3>
			    <ul>
			        <li class="sidemenu"><a href="${cp }/board/list">- 공지사항/이벤트</a></li>
			        <li class="sidemenu"><a href="${cp }/board/faqlist">- FAQ</a></li>
			        <li class="sidemenu"><a href="${cp }/board/qnalist">- Q&A</a></li>
				</ul>
			</div>
			<div id="detailMain">
				<div id="detailHeader">
					<input type="text" value="${dto.qnaTitle }" readonly="readonly" id="title">
					<input type="text" value="${dto.qnaWdate }" readonly="readonly" id="date">
					<input type="text" value="${mbrId }" readonly="readonly" id="date">
				</div>
				<div id=detailCon>
					<c:choose>
						<c:when test="${idto.itemName==null }">
						</c:when>
						<c:otherwise>
							<p style="margin-top: 10px; margin-left: 15px;">${idto.itemName }</p>
							<img src="${cp}/resources/uploads/admin/item/${idto.tImg }" id="iimg"><br>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${dto.qnaFile==null }">
						</c:when>
						<c:otherwise>
							<img src="${cp}/resources/uploads/admin/board/${dto.qnaFile }" id="img"><br>
						</c:otherwise>
					</c:choose>
					<textarea rows="10" cols="120" id="con" readonly="readonly">${dto.qnaCon }</textarea>
				</div>
				<hr style="margin-top: 5px;">
				<div id="nav">
					<a href="${cp }/board/qnalist" style="margin-left: 15px;">목록으로</a>
					<c:if test="${role == 'admin0' || role == 'admin' || dto.mbrNo == mbrNo }">
						&nbsp;|&nbsp;<a href="${cp }/board/qnaupdate?qnaNo=${dto.qnaNo}" >수정</a>&nbsp;|
						<a id="del" onclick="deleteCheck()" style="cursor: pointer;">삭제</a>
					</c:if>
				</div>
			</div>
			<div id="cmt">
				<c:if test="${role == 'admin0' || role == 'admin' || role == 'member' }">
					<form method="post" action="${cp }/board/qnacmtinsert" name="insertForm">
						<textarea rows="5" cols="134" name="qnaCmtCon" id="qnaCmtCon" placeholder="댓글을 입력해주세요."></textarea>
						<input type="text" name="qnaNo" value="${dto.qnaNo }" style="display: none">
						<button onclick="checkSpace()">등록</button>
					</form>
					<hr style="margin-top: 25px; margin-left: -21px; width: 103%;">
				</c:if>
			</div>
			<div id="reviewMain">
				<c:if test="${not empty list }">
					<c:forEach var="cmt" items="${list }">
						<fieldset id="reviewArea">
							<c:if test="${cmt.qnaCmtDel == 'true' }">
								<p>삭제된 댓글입니다.</p>
							</c:if>
							<c:if test="${cmt.qnaCmtDel == 'false' }">
								<input type="text" value="작성일 : ${cmt.qnaCmtWdate }" readonly="readonly" id="cmtdate" class="info" ><br>
								<div>
									${cmt.qnaCmtCon }
								</div>
								<c:if test="${role == 'admin0' || role == 'admin' || dto.mbrNo == mbrNo }">
									<button id="delbtn" onclick="location.href='${cp }/board/qnacmtdelete?qnaCmtNo=${cmt.qnaCmtNo}'">댓글삭제</button><br>
								</c:if>
							</c:if>
						</fieldset>
					</c:forEach>
				</c:if>	
			</div>
		</div>
	</div>
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
	
	function resize() {
        let textarea = document.getElementById("con");
 
        textarea.style.height = "0px";
 
        let scrollHeight = textarea.scrollHeight;
        let style = window.getComputedStyle(textarea);
        let borderTop = parseInt(style.borderTop);
        let borderBottom = parseInt(style.borderBottom);
 
        textarea.style.height = (scrollHeight + borderTop + borderBottom)+"px";
    }

    window.onload = resize;
    window.onresize = resize;
</script>
</html>
