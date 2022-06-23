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
            <li class="sidemenu"><a href=#>- 내글보기</a></li>
            <li class="sidemenu"><a href="${cp }/member/userGrade">- 등급/쿠폰 조회</a></li>
        </ul>
    </div>
    <div id="formMain">
        <fieldset id="formArea">
            <legend><h3>회원등급/쿠폰조회</h3></legend>
            <div id="gradeArea">
                <div id="gradeArea1">
                    <p>${id}님의 등급</p>
                    <p id="userGrade">${mbrDto.mbrGrade}</p>
                    <p>결제 누적 금액</p>
                    <p><fmt:formatNumber value="${mbrDto.totAmt}" type="number"/> 원</p>
                </div>
                <div id="gradeArea2">
                    <ul>
                        <li class="gradeInfo">등급기준</li>
                        <li class="gradeInfo">0원 ~ 100,000원 BRONZE</li>
                        <li class="gradeInfo">100,001원 ~ 500,000원 SILVER</li>
                        <li class="gradeInfo">500,001원 ~ GOLD</li>
                    </ul>
                </div>
            </div>
            <div id="couponArea">
                <fieldset id="couponBox">
                    <legend><h3>보유쿠폰</h3></legend>
                    <c:choose>
                        <c:when test="${errMsg != null}">
                            <div id="errBox">${errMsg}</div>
                        </c:when>
                        <c:otherwise>
                            <table id="couponTable">
                                <tr>
                                    <th class="couponTitle">쿠폰이름</th>
                                    <th class="couponTitle">보유수량</th>
                                    <th class="couponTitle">사용기한</th>
                                    <th class="couponTitle">결제최소금액</th>
                                </tr>
                                <c:forEach var="m" items="${mbrCouponInfoDtos}">
                                    <tr>
                                        <td>${m.cond}</td>
                                        <td>${m.mbrCpnCnt}</td>
                                        <td>${m.period} ~ ${m.expDate}</td>
                                        <td>${m.minord}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </fieldset>
            </div>
        </fieldset>
    </div>
</div>
</body>
</html>