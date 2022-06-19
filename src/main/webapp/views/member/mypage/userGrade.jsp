<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>등급/쿠폰조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/myPage.css">
</head>
<body>
<div id="gradePageWrap">
    <div id="formSide">
        <h3>마이 페이지</h3>
        <ul>
            <li><a href="${cp }/member/update">나의 정보 수정</a></li>
            <li><a href=#>장바구니</a></li>
            <li><a href=#>지점 즐겨찾기</a></li>
            <li><a href=#>주문/배송 조회</a></li>
            <li><a href=#>내글보기</a></li>
            <li><a href="${cp }/member/userGrade">등급/쿠폰 조회</a></li>
        </ul>
    </div>
    <div id="formMain">
        <fieldset id="gradeFormArea">
            <legend> 회원등급/쿠폰조회</legend>
            <div>
                <p>${id}님의 등급</p>
                <p id="userGrade">${mbrDto.mbrGrade}</p>
                <p>결제 누적 금액</p>
                <p>${mbrDto.totAmt}</p>
            </div>
            <div>
                <ul>
                    <li class="gradeInfo">등급기준</li>
                    <li class="gradeInfo">0원 ~ 100,000원 BRONZE</li>
                    <li class="gradeInfo">100,001원 ~ 500,000원 SILVER</li>
                    <li class="gradeInfo">500,001원 ~ GOLD</li>
                </ul>
            </div>
            <div>
                <p>보유쿠폰</p>
                <div id="errBox">${errMsg}</div>
                <c:forEach var="m" items="${mbrCouponInfoDtos}">
                <table>
                    <tr>
                        <th>쿠폰이름</th>
                        <th>보유수량</th>
                        <th>사용기한</th>
                        <th>결제최소금액</th>
                    </tr>
                    <tr>
                        <td>${m.cond}</td>
                        <td>${m.mbrCpnCnt}</td>
                        <td>${m.period} ~ ${m.expDate}</td>
                        <td>${m.minord}</td>
                    </tr>
                </table>
                </c:forEach>

        </fieldset>
    </div>
</div>
</body>
<script type="text/javascript">
    function selectId() {
        let coupon = document.createElement()

    }

</script>
</html>
