<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>main</title>
</head>
<script type="text/javascript">
    function getCookie(name) {
        const cookie = document.cookie;
        if (document.cookie != "") {
            const cookie_array = cookie.split("; ");
            for (const index in cookie_array) {
                const cookie_name = cookie_array[index].split("=");

                if (cookie_name[0] == "popupYN") {
                    return cookie_name[1];
                }
            }
        }
        return;
    }

    function windowPopup(url) {
        url = '${cp}/views/common/popUp.jsp';
        const cookieCheck = getCookie("popupNY");
        if (cookieCheck != "N") {
            window.open(url, 'popup', 'width=600px, height=370px, history=no,resizable=no,status=no,scrollbars=no,menubar=no,location=no')
        }
    }

</script>
<body onload="windowPopup()">
<main>
    <!--이미지 슬라이드-->
    <div class="slider">
        <!--인디케이터-->
        <input type="radio" name="dot" id="btn1" checked="checked">
        <label for="btn1" id="in1" class="wide"></label>

        <input type="radio" name="dot" id="btn2">
        <label for="btn2" id="in2" class="wide"></label>

        <input type="radio" name="dot" id="btn3">
        <label for="btn3" id="in3" class="wide"></label>

        <input type="radio" name="dot" id="btn4">
        <label for="btn4" id="in4" class="wide"></label>
        <!--슬라이드이미지-->
        <ul class="slide">
            <li class="slide-img1 fade"><a href="${cp}/board/detail?brdNo=302">
                <img src="${cp}/resources/images/slider01.jpg"></a></li>
            <li class="slide-img2 fade"><a href="${cp}/board/detail?brdNo=303"><img
                    src="${cp}/resources/images/slider02.jpg"></a></li>
            <li class="slide-img3 fade"><a href="${cp}/board/detail?brdNo=304"><img
                    src="${cp}/resources/images/slider03.jpg"></a></li>
            <li class="slide-img4 fade"><a href="${cp}/board/detail?brdNo=305"><img
                    src="${cp}/resources/images/slider04.jpg"></a></li>
        </ul>
    </div>

    <div id="main_banner_img">
        <a href="${cp }/item/search?field=mcatName&keyword=수납">
            <img src="${cp }/resources/images/mainBanner.png">
        </a>
    </div>
    <!--배너-->
    <div class="banner">
        <div><img src="${cp }/resources/images/banner.png" id="banner_img"></div>
    </div>


    <!--신상품라인-->
    <div class="comment">
        <p class="commentTitle">Recent Arrival</p>
        <fieldset id="cardField">
            <legend class="commentTitle">새로 업데이트된 상품을 확인해 보세요.</legend>
            <div class="cardbox">
                <div id="cardlist">
                    <c:forEach var="itemDto" items="${itemDtos}">
                        <div class="card">
                            <div>
                                <a href="${cp}/item/detail?itemNo=${itemDto.itemNo}"><img
                                        src="${cp}/resources/uploads/admin/item/${itemDto.tImg}" id="new_item"
                                        width="200px" height="200px"></a>
                                <p><a href="${cp}/item/detail?itemNo=${itemDto.itemNo}">${itemDto.itemName}</a></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </fieldset>
    </div>
</main>
</body>
<script type="text/javascript">
    let obj = document.getElementsByName('dot');
    let i = 0;
    playSlider = setInterval(function () {
        obj[i].checked = true;
        i++;
        if (i == obj.length) {
            i = 0;
        }
    }, 2000);
</script>
</html>