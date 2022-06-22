<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/salesList.css">
</head>
<body>
<div id="salesListWrap">
    <h2 id="salesListTitle">매출 조회</h2>
    <hr>
    <fieldset id="serachCondition">
	   	<legend><h3 id = "title">검색 조건</h3></legend>
		<form action="${cp}/admin/sales/list" method="post">
		<div id="textArea">
			<div id="category">
		    	<input type="checkbox" id="catName" name="catNameChk">
				<label for="catName">분류명</label>
					<div>
						&emsp;<input type="radio" name="cat" value="mcat" checked="checked">
						<label for="mcatName">대분류</label>
						<select name="mcatName">
							<c:forEach var="mcat" items="${mcatList}">
								<option value="${mcat.mcatName}">${mcat.mcatName}</option>
							</c:forEach>
						</select>
						<br>
						&emsp;<input type="radio" name = "cat" value = "scat">
						<label for="scatName">소분류</label>
						<select name="scatName">
							<c:forEach var="scat" items="${scatList}">
								<option value="${scat.scatName}">${scat.scatName}</option>
							</c:forEach>
						</select>
					</div>
	    	</div>
	    	<div id = "branch">
				<input type="checkbox" id="brName" name="brNameChk">
				<label for="brName">지점명</label>
				<select name="brName">
					<c:forEach var="br" items="${branchList}">
						<option value="${br.brName}">${br.brName}</option>
					</c:forEach>
				</select>
			</div>
			<div id = "orderDate">
				<input type="checkbox" id="ordDate" name="ordDateChk">
				<label for="ordDate">주문일</label>
				<input type="date" name="ordStartDate" id="ordStartDate">
				~
				<input type="date" name="ordEndDate" id="ordEndDate">
			</div>
			<div id = "paymentDate">
				<input type="checkbox" id="pmtDate" name="pmtDateChk">
				<label for="pmtDate">결제일</label>
				<input type="date" name="pmtStartDate" id="pmtStartDate">
				~
				<input type="date" name="pmtEndDate" id="pmtEndDate">
			</div>
			<div id="item">
				<input type="checkbox" id="itemName" name="itemNameChk">
				<label for="itemName">상품명</label>
				<input type="text" name="itemName">
			</div>
		</div>
		<div id="btnArea">
			<input type="submit" value = "검색" name = "itemName" id="searchBtn">
			<input type="button" value = "전체 보기" name = "itemName" id="allBtn" onclick="location.href='${cp}/admin/sales/list'">
		</div>
		</form>
    </fieldset>
    <br>
    <h3>조회 결과</h3>
    <div id="salesList">
        <c:set var="cp" value="${pageContext.request.contextPath }"/>
        <c:set var="totPmtAmt" value="0"/>
        <table id="salesTable">
            <tr>
                <th class="salesInfo_header">주문번호</th>
                <th class="salesInfo_header">대분류</th>
                <th class="salesInfo_header">소분류</th>
                <th class="salesInfo_header">상품명</th>
                <th class="salesInfo_header">지점명</th>
                <th class="salesInfo_header">판매수량</th>
                <th class="salesInfo_header">판매금액</th>
                <th class="salesInfo_header">주문일</th>
                <th class="salesInfo_header">결제일</th>
            </tr>
            <c:forEach var="sales" items="${salesList}">
            <c:set var="totPmtAmt" value="${totPmtAmt+sales.pmtAmt}"/>
                <tr>
                    <td class="salesInfo">${sales.ordNo}</td>
                    <td class="salesInfo">${sales.mcatName}</td>
                    <td class="salesInfo">${sales.scatName}</td>
                    <td class="salesInfo">${sales.itemName}</td>
                    <td class="salesInfo">${sales.brName}</td>
                    <td class="salesInfo">${sales.ordCnt}</td>
                    <td class="salesInfo">
                    	<fmt:formatNumber value="${sales.pmtAmt}" type="number"/>
                    </td>
                    <td class="salesInfo">${sales.ordDate}</td>
                    <td class="salesInfo">${sales.pmtDate}</td>
                </tr>
            </c:forEach>
            	<tr>
            		<td class="salesInfo"><b>합계</b></td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">
                    	<b>
                    	    <fmt:formatNumber value="${totPmtAmt}" type="number"/>
                    	</b>
                    </td>
                    <td class="salesInfo">-</td>
                    <td class="salesInfo">-</td>
               </tr>
        </table>
    </div>
    <%--
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
                <input type="text" name="keyword" value="${keyword }">
                <input type="submit" value="검색">
            </form>
        </div>
        <a href="${cp }/admin/mbrlist" id="idTag">전체글 보기</a>&nbsp;|
        <a href="${cp }/home" id="pwdTag">Home</a>
    </div>
    --%>
</div>
</body>
<script type="text/javascript">

	let ordStartDate = document.getElementById('ordStartDate');
	let ordEndDate = document.getElementById('ordEndDate');
	let pmtStartDate = document.getElementById('pmtStartDate');
	let pmtEndDate = document.getElementById('pmtEndDate');
	
	ordStartDate.addEventListener('change', function() {
	    if (ordStartDate.value)
	    	ordEndDate.min = ordStartDate.value;
	}, false);
	pmtStartDate.addEventListener('change', function() {
	    if (pmtStartDate.value)
	    	pmtEndDate.min = pmtStartDate.value;
	}, false);
	pmtEndDate.addEventListener('change', function() {
	    if (pmtEndDate.value)
	    	pmtStartDate.max = pmtEndDate.value;
	}, false);
	ordEndDate.addEventListener('change', function() {
	    if (ordEndDate.value)
	    	ordStartDate.max = ordEndDate.value;
	}, false);
		
</script>
</html>