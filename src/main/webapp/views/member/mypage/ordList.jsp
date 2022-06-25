<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>등급/쿠폰조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/myPage.css">
</head>
<body>
<div id="myPageWrap">
    <div id="formSide">
        <h3>마이 페이지</h3>
        <ul>
            <li class="sidemenu"><a href="${cp }/member/update">- 나의 정보 수정</a></li>
            <li class="sidemenu"><a href="${cp}/member/ordList">- 주문/배송 조회</a></li>
            <li class="sidemenu"><a href="${cp}/member/myposts">- 내글보기</a></li>
            <li class="sidemenu"><a href="${cp }/member/userGrade">- 등급/쿠폰 조회</a></li>
        </ul>
    </div>
    <div id="formMain">
        <h3>최근 주문 내역</h3>
        <hr>
        <div id=ordFormArea>
            <c:set var="i" value="0"/>
            <c:set var="j" value="0"/>
            <c:set var="k" value="0"/>
            <c:forEach var="ord" items="${mbrOrderList}">
                <c:if test="${ord.ordSta == '배송중'}">
                    <c:set var="i" value="${i=i+1}"/>
                </c:if>
                <c:if test="${ord.ordSta == '배송완료'}">
                    <c:set var="j" value="${j=j+1}"/>
                </c:if>
                <c:if test="${ord.ordSta == '결제취소'}">
                    <c:set var="k" value="${k=k+1}"/>
                </c:if>
            </c:forEach>
            <fieldset id="ordListSearch">
                <legend>주문 현황</legend>
                <form action="${cp}/member/ordList" method="post">
                    <table id="deliArea">
                        <tr>
                            <th class="deltitle">배송중</th>
                            <th class="deltitle">배송완료</th>
                            <th class="deltitle">결제취소</th>
                        </tr>
                        <tr>
                            <td class="delInfo"><img src="${cp}/resources/images/delivery.png" class="delImg">
                                <h2>${i}</h2></td>
                            <td class="delInfo"><img src="${cp}/resources/images/deliveryfinish.png" class="delImg">
                                <h2>${j}</h2>
                            </td>
                            <td class="delInfo"><img src="${cp}/resources/images/cancelpurchase.png" class="delImg">
                                <h2>${k}</h2>
                            </td>
                        </tr>
                    </table>
                    주문일 검색 &nbsp;
                    <input type="date" name="ordStartDate">
                    ~
                    <input type="date" name="ordEndDate">
                    <input type="submit" value="검색" name="itemName" class="odrBtn">
                    <input type="button" value="전체 보기" name="itemName" class="odrBtn"
                           onclick="location.href='${cp}/member/ordList'">
                </form>
            </fieldset>
            <hr>
            <c:set var="cp" value="${pageContext.request.contextPath }"/>
            <c:choose>
                <c:when test="${errMsg != null}">
                    <div id="errBox">${errMsg}</div>
                </c:when>
                <c:otherwise>
            
           <table id="ordListTable">
                        <c:forEach var="ord" items="${mbrOrderList}" varStatus="status">
                            <tr>
                                <th class="adrInfo"><h3>주문번호</h3></th>
                                <th class="adrInfo"></th>
                                <th class="adrInfo"><h3>상품정보</h3></th>
                                <th class="adrInfo"><h3>진행상태</h3></th>
                                <th class="adrInfo"></th>
                            </tr>
                            <form method="post">
                                <tr>
                                    <td colspan="5" id="adrInfoStart"></td>
                                </tr>
                                <tr>
                                    <td>${ord.ordNo}
                                        <input type="hidden" value="${ord.ordNo}" name="ordNo"></td>
                                    <td><a href = "${cp}/item/detail?itemNo=${ord.itemNo}"><img src="${cp }/resources/uploads/admin/item/${ord.tImg}" id="ordImg"></a></td>
                                    <td><a href="${cp}/item/detail?itemNo=${ord.itemNo}" id="itemTitle">${ord.itemName}</a><br><fmt:formatNumber value="${ord.price}" type="number"/>원 / ${ord.ordCnt}개<br>${ord.ordDate}</td>
                                    <td>${ord.ordSta}</td>
                                    <c:choose>
                                        <c:when test="${ord.ordSta =='결제대기'}">
                                            <td><input type="button" value="결제하기" class="odrBtn"
                                                       onclick="location.href='${cp}/member/payment'"><br>
                                                <input type="hidden" value="주문취소" name="ordSta">
                                                <input type="button" value="주문취소" class="odrBtn"
                                                	   onclick="location.href='${cp}/cancelPurchase'"><br>
                                            </td>
                                        </c:when>
                                        <c:when test="${ord.ordSta =='결제완료'}">
                                            <td><input type="submit" value="결제취소" class="odrBtn"
                                                       formaction="${cp}/cancelPurchase">
                                                <input type="hidden" value="결제취소" name="ordSta">
                                            </td>
                                        </c:when>
                                        <c:when test="${ord.ordSta =='발송전'}">
                                            <td><input type="submit" value="구매확정" class="odrBtn"
                                                       formaction="${cp}/confirmPurchase"
                                                       onclick="if(!confirm('구매확정시 주문취소가 불가능 합니다. 구매확정 하시겠습니까?')) {return false;}">
                                                <input type="hidden" value="구매확정" name="ordSta">
                                            </td>
                                        </c:when>
                                        <c:when test="${ord.ordSta =='배송완료'}">
                                            <td><input type="submit" value="구매확정" class="odrBtn"
                                                       formaction="${cp}/confirmPurchase"
                                                       onclick="if(!confirm('구매확정시 주문취소가 불가능 합니다. 구매확정 하시겠습니까?')) {return false;}">
                                                <input type="hidden" value="구매확정" name="ordSta">
                                            </td>
                                        </c:when>
                                        <c:when test="${ord.ordSta =='구매확정'}">
                                        <td>
                                    	<c:choose>
                                    		<c:when test="${revDate[status.index] == null}">
	                                    		<input type="button" value="후기작성" class="odrBtn" onclick='location.href="${cp}/reviewinsert?ordNo=${ord.ordNo }&itemNo=${ord.itemNo }"'>
	                                    	</c:when>
	                                    	<c:otherwise>
	                                    		후기작성 완료
	                                    	</c:otherwise>
                                    	</c:choose>    
                                    </td>
                                </c:when>
                                    </c:choose>
                                </tr>
                                <tr class="adrInfo">
                                    <th colspan="5" id="adrInfoTitle"><h3>배송지정보</h3></th>
                                </tr>
                                <tr class="adrInfo">
                                    <td></td>
                                    <td>수령인</td>
                                    <td class="adrInfos">${ord.ordName}</td>
                                </tr>
                                <tr class="adrInfo">
                                    <td></td>
                                    <td>배송지</td>
                                    <td colspan="2" class="adrInfos">${ord.ordAdr}
                                        <input type="hidden" value="${ord.ordAdr}" name="ordAdr"></td>
                                </tr>
                                <tr class="adrInfo">
                                    <td></td>
                                    <td>연락처</td>
                                    <td class="adrInfos">${ord.ordTel}</td>
                                </tr>
                                <tr>
                                    <th colspan="5" id="adrInfoEnd"></th>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>