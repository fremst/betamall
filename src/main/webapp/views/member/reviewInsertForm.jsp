<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${cp}/resources/css/boardInsert.css">
</head>
<body>
	<div>
		<div id="formSide">
	        <h3>마이 페이지</h3>
	        <ul>
	            <li class="sidemenu"><a href="${cp }/member/update">- 나의 정보 수정</a></li>
	            <li class="sidemenu"><a href="${cp}/member/ordList">- 주문/배송 조회</a></li>
	            <li class="sidemenu"><a href="${cp}/member/myposts">- 내글보기</a></li>
	            <li class="sidemenu"><a href="${cp }/member/userGrade">- 등급/쿠폰 조회</a></li>
	        </ul>
	    </div>
		<div id="insertMain">
			<form method="post" action="${cp }/reviewinsert" name="insertForm">
				<input type="text" name="ordNo" value="${ordNo }" style="display: none">
				<input type="text" name="itemNo" value="${dto.itemNo }" style="display: none">
				<div id="insertHeader">
					<input type="text" value="평점" style="margin-left: 15px; border: none; width: 25px;">
					 <select name="rate" id="category" class="headerInfo">
						<option value="5" selected="selected">5점</option>
						<option value="45">4.5점</option>
						<option value="4">4점</option>
						<option value="35">3.5점</option>
						<option value="3">3점</option>
						<option value="25">2.5점</option>
						<option value="2">2점</option>
						<option value="15">1.5점</option>
						<option value="1">1점</option>
						<option value="50">0.5점</option>
					</select>
					<input type="text" value="${dto.itemName }" id="brdTitle" class="headerInfo" style="margin-left: 15px; border: none;">
				</div>
				<div>
					<textarea cols="138" name="review" id="brdCon" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="내용"></textarea><br>
				</div>
				<hr style="margin-top: 15px;">
				<div id="nav">
					<a onclick="window.history.back()" style="margin-left: 15px; cursor: pointer;">취소</a>&nbsp;|
					<a onclick="checkSpace()" style="cursor: pointer;">등록</a>
				</div>	
			</form>
		</div>
	</div>
</body>
	<script type="text/javascript">
		function checkSpace() {
			let review=document.getElementById("brdCon");
			if(review.value=='' || review.value==null) {
				alert("후기내용을 입력해주세요.")
				return false;
			}else {
				document.insertForm.submit();
			}
		}
		
		function setThumbnail(event) {
	        var reader = new FileReader();

	        reader.onload = function(event) {
	          var img = document.createElement("img");
	          img.setAttribute("src", event.target.result);
	          img.setAttribute("id", "img");
	          document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	          document.querySelector("div#thumbNailImg").appendChild(img);
	        };
	        reader.readAsDataURL(event.target.files[0]);
	      }
		
		function resize(obj) {
			  obj.style.height = "200px";
			  obj.style.height = (obj.scrollHeight)+"px";
			}
	</script>
</html>
