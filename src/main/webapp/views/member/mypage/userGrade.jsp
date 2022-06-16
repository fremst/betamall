<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>등급/쿠폰조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <%-- <link rel="stylesheet" href="${cp}/resources/css/myPage.css"> --%>
</head>
<body>
<div id="wrap">
    <div id="side">
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
    <div id="center">
        <input type="hidden" name="mbrNo" value="${mbrDto.mbrNo}"><br>
        <fieldset>
            <legend> 회원등급/쿠폰조회</legend>
            <div>
                <p>${mbrDto.mbrId}님의 등급</p>
                <p>${mbrDto.mbrGrade}</p>
            </div>
            <div>
                <ul>
                    <li>등급기준</li>
                    <li>0원 ~ 100,000원 BRONZE</li>
                    <li>100,001원 ~ 500,000원 SILVER</li>
                    <li>500,001원 ~ GOLD</li>
                </ul>
            </div>
            <div>
            <div style="color: red; font-size: 12px;">${errMsg}</div>
              보유쿠폰<input type="text" value="${mbrCouponDto.cond }" disabled=disabled style="color:blue;"><br>	
            </div>
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
