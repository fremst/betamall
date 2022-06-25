<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/mbrList.css">
</head>
<body>
<div id="mbrListWrap">
    <h2 id="mbrListTitle">회원 조회</h2>
    <div id="mbrList">
        <c:set var="cp" value="${pageContext.request.contextPath }"/>
        <table id="mbrTable">
            <tr>
                <th class="mbrInfo_header">아이디</th>
                <th class="mbrInfo_header">이름</th>
                <th class="mbrInfo_header">전화번호</th>
                <th class="mbrInfo_header">이메일</th>
                <th class="mbrInfo_header">주소</th>
                <th class="mbrInfo_header">생년월일</th>
                <th class="mbrInfo_header">등급</th>
                <th class="mbrInfo_header">가입일</th>
            </tr>
            <c:forEach var="m" items="${mbrList }" varStatus="status">
                <tr>
                    <td class="mbrInfo">${m.mbrId }</td>
                    <td class="mbrInfo">${m.mbrName }</td>
                    <td class="mbrInfo">${m.mbrTel }</td>
                    <td class="mbrInfo">${m.mbrEmail }</td>
                    <td class="mbrInfo">${m.mbrAdr }</td>
                    <td class="mbrInfo">${m.mbrBd }</td>
                    <td class="mbrInfo">${gradeList[status.index] }</td>
                    <td class="mbrInfo">${m.mbrRegdate }</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="mbrListNum">
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:choose>
                <c:when test="${i==pageNum}">
                    <a href="${cp }/admin/mbrlist?pageNum=${i}&field=${field}&keyword=${keyword}">
                        <span id="SrowNum">${i }</span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${cp}/admin/mbrlist?pageNum=${i }&field=${field}&keyword=${keyword}">
                        <span id="rowNum">${i }</span>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <div id="mbrPageNav">
        <div>
            <form method="post" action="${cp }/admin/mbrlist" id="searchMbr">
                <select name="field">
                    <option value="mbrId" <c:if test="${field=='mbrId' }">selected</c:if>>회원아이디</option>
                    <option value="mbrName" <c:if test="${field=='mbrName' }">selected</c:if>>회원이름</option>
                </select>
                <input type="text" name="keyword" value="${keyword }" id="mbrList_search">
                <input type="submit" value="검색" id="mbrList_search_btn">
            </form>
        </div>
        <a href="${cp }/admin/mbrlist" id="idTag">전체글 보기</a>&nbsp;|
        <a href="${cp }/home" id="pwdTag">Home</a>
    </div>
</div>
</body>
</html>