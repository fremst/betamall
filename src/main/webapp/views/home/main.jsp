<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../resource/css/layout.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.slider{
		position: relative;
		height: 500px;
	}
	.slider ul li{
		height: 400px;
		background-repeat: no-repeat;
		background-size: cover;
		position: absolute;
		top: 100px;left: calc(50% - 300px);
		width: 600px;
	}
	.slider input,
	.slider label{
		position: relative;	
		top: 420px;
	}
	/*인디케이터-레이블 디자인*/
	.slider label{
		width: 20px;
		height: 20px;
		background-color: #ddd;
		background: rgba(0,0,0,0.5);
		display: inline-block;
		border-radius: 10px;
		cursor: pointer;
	}
	/*input태그 날리기*/
	.slider input[type="radio"]{
		display: none;
	}
	/*모든 슬라이드 이미지 숨기기*/
	.slider ul li{display: none;}
	/*인디케이터 선택시 이미지 보이기*/
	#btn1:checked ~ .slide li:nth-child(1){display: block;}
	#btn2:checked ~ .slide li:nth-child(2){display: block;}
	#btn3:checked ~ .slide li:nth-child(3){display: block;}
	#btn4:checked ~ .slide li:nth-child(4){display: block;}
	/*선택된 인디케이터 스타일링*/
	#btn1:checked ~ #in1 {background-color: white;}
	#btn2:checked ~ #in2 {background-color: white;}
	#btn3:checked ~ #in3 {background-color: white;}
	#btn4:checked ~ #in4 {background-color: white;}
	/*인디케이터 템플릿 (.wide)*/
	#btn1:checked ~ #in1.wide{width:40px;}
	#btn2:checked ~ #in2.wide{width:40px;}
	#btn3:checked ~ #in3.wide{width:40px;}
	#btn4:checked ~ #in4.wide{width:40px;}
	/*모션*/
	.slider label{transition: all 0.6s;}

	#btn1 ~ ul li.fade,
	#btn2 ~ ul li.fade
	#btn3 ~ ul li.fade
	#btn4 ~ ul li.fade{
		display: block;
		opacity: 0;
		visibility: hidden;
		transition: all 0.6s;
	}
	#btn1:checked ~ ul li.fade:nth-child(1),
	#btn2:checked ~ ul li.fade:nth-child(2),
	#btn3:checked ~ ul li.fade:nth-child(3),
	#btn4:checked ~ ul li.fade:nth-child(4){
		opacity: 1;
		visibility: visible;
	}
	.slide-img1{
		background-image: url(../images/slider01.jpg);
	}
	.slide-img2{
		background-image: url(../images/slider02.jpg);
	}
	.slide-img3{
		background-image: url(../images/slider03.jpg);
	}
	.slide-img4{
		background-image: url(../images/slider04.jpg);
	}
	.slider label{
		top: 470px;z-index: 99990;
		left: calc(50% - 50px);
	}
	.banner{
		height: 500px;
	}
	#banner_img{
		display: block;margin-left: auto; margin-right: auto; padding-top: 10%;
	}
	.cardbox{
		display: flex;
		height: 600px;
	}
	.card{
		padding-top: 10%;
		margin-left: 50px;
	}
	h4{
		text-align: center;
	}
	#cardlist{
		display:flex; margin-left: auto; margin-right: auto;
	}
	#new_item{
		border: 1px solid black;
	}
	.card > p{
		text-align: center;
	}
</style>
</head>
	<body>
		<div class="wrap">
			<div class="header">
				<!--header-->
				<div class="search_area">
					<form>
						<input type="serach" placeholder="Search" id="search">
						<input type="submit" value="검색" id="search_btn">
					</form>
				</div>
				<div class="logo">
					<a href="main.html"><img src="../images/betamall.png"></a>
				</div>
				<ul class="nav">
					<li><a href="#">로그인</a></li>
					<li><a href="#">회원가입</a></li>
					<li><a href="#">마이페이지</a></li>
					<li><a href="#">고객센터</a></li>
				</ul>
			</div>
			<div class="cate">
				<ul class="nav2">
					<li><a href="#">수납</a></li>
					<li><a href="#">필기류</a></li>
					<li><a href="#">일반사무용품</a></li>
					<li><a href="#">화일/바인더</a></li>	
				</ul>
			</div>

			<!--이미지 슬라이드-->
			<div class="slider">
				<!--인디케이터-->
				<input type="radio" name="dot" id="btn1">
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
				<div><img src="../images/banner.png" id="banner_img"></div>
			</div>

			<!--신상품라인-->
			<div class="comment">
				<h4>Recent Arrival <br> 새로 업데이트된 상품을 확인해 보세요.</h4>
			</div>
			<div class="cardbox">
				<div id="cardlist">
				<div class="card">
				   <div><img src="../images/highlighter05.jpg" id="new_item" width="200px" height="200px"></div>
					<p>[스테들러] 텍스트서퍼 364</p>
				</div>
				<div class="card">
					<div><img src="../images/pen04.jpg" id="new_item"  width="200px" height="200px"></div>
					 <p>[케이피아이] 유성펜 스톱펜S</p>
				 </div>
			
				 <div class="card">
					<div><img src="../images/pencil04.jpg" id="new_item" width="200px" height="200px"></div>
					 <p>[파버카스텔] 파버톡 연필세트</p>
				 </div>
			
				 <div class="card">
					<div><img src="../images/Lfile01.jpg" id="new_item" width="200px" height="200px"></div>
					 <p>[알파] 클리어 L홀더 A4</p>
				 </div>
			
				 <div class="card">
					<div><img src="../images/tape05.jpg" id="new_item" width="200px" height="200px"></div>
					 <p>[3M] 포장용테이프 3655</p>
				 </div>
				</div>
			</div>

			<!--footer-->
			<div class="footer">
				<div><img src="../images/betalogo.png" id="under_logo"></div>
				<div class="copyright">
					<p style="color: white; font-size: 20px;">
					(주) 베타 문구<br>
					자주묻는질문    개인정보처리방침    이메일무단수집거부    인재채용    인트라넷    오시는길<br>
					</p>
					<p style="font-size: 12px; color:white">
					주소 : 우편번호 (06297) 서울특별시 강남구 남부순환로 2748 한웰빌딩 베타문구  이메일 : betamall0000@gmail.com
					개인정보관리책임자 : 정웅식  TEL : 00-111-2222  <br>FAX : 00-111-2222  고객만족실 : 00-111-2222 멤버십 문의 : 00-111-2222 
					<br>
					<br>	
					Copyright (c) 2020 ASUNGDAISO. All Rights Reserved.
					</p>
				</div>
				<div class="customer_center">
					<p style="color: black;">
						고객센터 바로가기 ><br>
					</p>
					<p style="color: white; font-size:12px;">						
						운영시간 오전 10시 ~ 오후 5시 (주말, 공휴일 휴무)<br>
						점심시간 오후 12시 ~ 1시
					</p>
				</div>
				<!-- <div class="social">
					<img src="../images/social.png" id="socials">
				</div> -->
			</div>
		</div>
	</body>
</html>