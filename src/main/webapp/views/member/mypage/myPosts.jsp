<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/myPosts.css">
</head>
<body>
	<div id="myPostsWrap">
		<div id="formSide">
	        <h3>마이 페이지</h3>
	        <ul>
	            <li class="sidemenu"><a href="${cp }/member/update">- 나의 정보 수정</a></li>
	            <li class="sidemenu"><a href="${cp}/member/ordList">- 주문/배송 조회</a></li>
	            <li class="sidemenu"><a href="${cp}/member/myposts">- 내글보기</a></li>
	            <li class="sidemenu"><a href="${cp }/member/userGrade">- 등급/쿠폰 조회</a></li>
	        </ul>
	    </div>
		<div id="reviewMain">
			<hr color="#707070">
			<h3 id="reviewTitle">후기</h3>
			<hr color="#707070">
			<c:choose>
				<c:when test="${not empty relist }">
					<c:forEach var="list" items="${relist }">
						<fieldset id="reviewArea">
							<form action="${cp }/reviewdelete" name="deleteForm">
								<img src="${cp }/resources/uploads/admin/rate/${list.rate }.jpg">
								<input type="text" value="아이템 번호 : ${list.itemNo }" readonly="readonly" id="writer" class="info" >
								<input type="text" value="작성자 : ${list.mbrId }" readonly="readonly" id="writer" class="info" >
								<input type="text" value="작성일 : ${list.revDate }" readonly="readonly" class="info"><br>
								<div>
									${list.review }
								</div>
								<input type="text" name="ordNo" value="${list.ordNo }" style="display: none">    
				                <input type="text" name="itemNo" value="${list.itemNo }" style="display: none"> 
				                <br><button id="del" onclick="del()">삭제</button>
							</form>
						</fieldset>
					</c:forEach>
					<hr color="#707070" style="margin-bottom: 30px;">
				</c:when>
				<c:otherwise>
					<p style="margin: 15px 15px;">등록된 후기가 없습니다.</p>
					<hr color="#707070" style="margin-bottom: 30px;">
				</c:otherwise>
			</c:choose>
		</div>
		<div id="boardWrap">
			<h3 id="boardTitle" style="margin-left: 15px;">Q&A</h3>
			<div id="boardMain">
				<c:choose>
					<c:when test="${not empty list }">
						<table id="bodarList">
							<tr >
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
														<td class="boardInfo"><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }" style="color: #979897 ;">🔒${dto.qnaTitle }<span style="color: #2E64FE; font-size: x-small;">[${dto.cmtCount }]</span></a></td>
													</c:when>
													<c:otherwise>
														<td class="boardInfo"><a href="" style="color: #979897 ;" onclick="alert('본인글만 확일할 수 있습니다.')">🔒비밀글로 설정되었습니다.</a></td>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<td class="boardInfo"><a href="${cp}/board/qnadetail?qnaNo=${dto.qnaNo}&itemNo=${dto.itemNo }">${dto.qnaTitle }<span style="color: #2E64FE; font-size: x-small;">[${dto.cmtCount }]</span></a></td>
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
							<a href="${cp }/member/myposts?pageNum=${i}&field=${field}&keyword=${keyword}">
								<span id="SrowNum">${i }</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${cp}/member/myposts?pageNum=${i }&field=${field}&keyword=${keyword}">
								<span id="rowNum">	${i }</span>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div id="boardPageNav">
				<form method="post" action="${cp }/member/myposts" id="searchBoard">
					<select name="field">
						<option value="qnaCat" <c:if test="${field=='qnaCat' }">selected</c:if> >카테고리</option>
						<option value="qnaTitle" <c:if test="${field=='qnaTitle' }">selected</c:if>>제목</option>
						<option value="mbrId" <c:if test="${field=='mbrId' }">selected</c:if>>작성자</option>
					</select>
					<input type="text" name="keyword" value="${keyword }" id="boardList_search">
					<input type="submit" value="검색" id="boardList_search_btn">
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function del() {
		if(confirm("정말 삭제하시겠습니까?")==true) {
			document.deleteForm.submit();
		}else {
			return false;
		}
	}
</script>
</html>