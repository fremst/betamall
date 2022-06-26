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
				<div id="con">
					${dto.brdCon }
				</div>
			</div>
			<hr style="margin-top: 5px;">
			<div id="nav">
				<a href="${cp }/board/faqlist" style="margin-left: 15px;">목록으로</a>
				<c:if test="${dto.mgrNo==mgrNo || mgrNo==0 }">
					&nbsp;|&nbsp;<a href="${cp }/board/faqupdate?brdNo=${dto.brdNo}" >수정</a>&nbsp;|
					<a id="del" onclick="deleteCheck()" style="cursor: pointer;">삭제</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function deleteCheck() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			location.href='${cp }/board/faqdelete?brdNo=${dto.brdNo}';
		}else {
			return false;
		}
	}
</script>
</html>