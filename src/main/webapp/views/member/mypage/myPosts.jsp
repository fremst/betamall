<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<h1>후기</h1>
			<c:choose>
				<c:when test="${not empty relist }">
					<c:forEach var="list" items="${relist }">
						<form action="${cp }/reviewdelete" name="deleteForm">
							<label>작성자</label>
							<input type="text" value="${list.mbrId }" readonly="readonly"><br>
							<label>작성일자</label>
							<input type="text" value="${list.revDate }" readonly="readonly"><br>
							<img src="${cp }/resources/uploads/admin/rate/${list.rate }.jpg"><br>
							<input type="text" value="${list.review }" readonly="readonly"><br>
							<input type="text" name="ordNo" value="${list.ordNo }" style="display: none">    
			                <input type="text" name="itemNo" value="${list.itemNo }" style="display: none"> 
			                <button id="del" onclick="del()">삭제</button>
						</form>
						--------------------------------------------------------------------------------------------------------------------------<br>
					</c:forEach>
				</c:when>
				<c:otherwise>
					등록된 후기가 없습니다.
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<h1>Q&A</h1>
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
						<a href="${cp }/member/myposts?pageNum=${i}&field=${field}&keyword=${keyword}">
						<span style="color:red">${i }</span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${cp}/member/myposts?pageNum=${i }&field=${field}&keyword=${keyword}">
						<span style="color:gray">${i }</span>
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div>
			<form method="post" action="${cp }/member/myposts">
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