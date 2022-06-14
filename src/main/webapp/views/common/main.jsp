<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
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
				<li class="slide-img1 fade"></li>
				<li class="slide-img2 fade"></li>
				<li class="slide-img3 fade"></li>
				<li class="slide-img4 fade"></li>
			</ul>
		</div>
		
		<!--배너-->
		<div class="banner">
			<div><img src="${cp }/resources/images/banner.png" id="banner_img"></div>
		</div>
		
		<!--신상품라인-->
		<div class="comment">
			<h4>Recent Arrival <br> 새로 업데이트된 상품을 확인해 보세요.</h4>
		</div>
		<div class="cardbox">
			<div id="cardlist">
				<div class="card">
					<div><img src="${cp }/resources/images/highlighter05.jpg" id="new_item" width="200px" height="200px"></div>
						<p>[스테들러] 텍스트서퍼 364</p>
					</div>
				<div class="card">
					<div><img src="${cp }/resources/images/pen04.jpg" id="new_item"  width="200px" height="200px"></div>
						<p>[케이피아이] 유성펜 스톱펜S</p>
					</div>
				<div class="card">
					<div><img src="${cp }/resources/images/pencil04.jpg" id="new_item" width="200px" height="200px"></div>
						<p>[파버카스텔] 파버톡 연필세트</p>
				</div>
				<div class="card">
					<div><img src="${cp }/resources/images/Lfile01.jpg" id="new_item" width="200px" height="200px"></div>
						<p>[알파] 클리어 L홀더 A4</p>
				</div>
				<div class="card">
					<div><img src="${cp }/resources/images/tape05.jpg" id="new_item" width="200px" height="200px"></div>
						<p>[3M] 포장용테이프 3655</p>
				</div>
			</div>
		</div>
	</main>
</body>
</html>