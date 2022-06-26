<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/boardDetail.css">
</head>
<body>
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
				<input type="text" value="${dto.brdTitle }" readonly="readonly" id="title">
				<input type="text" value="${dto.brdWdate }" readonly="readonly" id="date">
			</div>
			<div id=detailCon>
				<c:choose>
					<c:when test="${dto.brdImg==null }">
					</c:when>
					<c:otherwise>
						<img src="${cp}/resources/uploads/admin/board/${dto.brdImg }" id="img"><br>
					</c:otherwise>
				</c:choose>
				<c:if test="${not empty dto.brdSdate }">
					<input type="text" value="이벤트 기간 : ${dto.brdSdate } ~ ${dto.brdFdate }" readonly="readonly" id="event"><br>
				</c:if>
				<textarea rows="10" cols="120" id="con" readonly="readonly">${dto.brdCon }</textarea>
			</div>
			<hr style="margin-top: 5px;">
			<div id="nav">
				<a href="${cp }/board/list" style="margin-left: 15px;">목록으로</a>
				<c:if test="${dto.mgrNo==mgrNo || mgrNo==0 }">
					&nbsp;|&nbsp;<a href="${cp }/board/update?brdNo=${dto.brdNo}" >수정</a>&nbsp;|
					<a id="del" onclick="deleteCheck()" style="cursor: pointer;">삭제</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function deleteCheck() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			location.href='${cp }/board/delete?brdNo=${dto.brdNo}';
		}else {
			return false;
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