<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.main{
		width: 1024px;
		margin: auto;
		text-align: auto;
	}
	td{
		border-bottom: solid 1px;
	}
	.searchItem{
		width: 1024px;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		margin-top: 20px;
		margin-bottom: 15px;
	}
	table{
		margin-bottom: 100px;
	}
	.itemList{
		width: 1024px;
		text-align: center;
		margin: auto;
	}
	.itemNo{
		width: 70px;
	}
	.mcatName{
		width: 100px;
	}
	.itemImg{
		width: 180px;
	}
	.itemName{
		width: 500px;
		text-align: left;
	}
	.itemPrice{
		width: 100px;
	}
	.purchase{
		width: 100px;
	}
    .itemThumbNails{
        width: 150px;
        height: 150px;
    }
    .itemNames{
        font-size: 18px;
    }
    .itemStocks{
        font-size: 16px;
    }
    .hashTags{
        font-size: 14px;
    }
    .srchText{
    	width: 400px;
    	height: 25px;
    	font-size: 16px
    }
    
    .srchBtn{
    	width: 50px;
    	height: 30px;
    	font-size: 16px;
    }
    .allBtn{
    	width: 80px;
    	height: 30px;
    	font-size: 16px;
    }
    
    .cartBtn {
		width: 80px;
		height: 30px;
		margin: 10px;
		color: aliceblue;
		font-size: 15px;
		background-color: #2C8FED;
		border: 1px;
		border-radius: 5px;
	}

	.pcBtn {
		width: 80px;
		height: 30px;
		margin: 10px;
		color: black;
		font-size: 15px;
		background-color: #4B484B3A;
		border: 1px;
		border-radius: 5px;
	}
    .apcBtns{
    	width: 100px;
    	height: 25px;
    	padding: 3px;
    	border: solid 0.5px gray;
    }
    .nothing{
    	height: 400px;
    	vertical-align: center;
    	padding-top: 200px;
    }
</style>
<body>
   <h2>상품 검색</h2>
    <div class = "itemList">
    	<div class = "searchItem">
    	<form action="/betamall/item/search">
	        <select name = "field" style = "font-size: 16px">
	            <option value = "itemName" <c:if test="${param.field=='itemName'}">selected</c:if>>상품명</option>
	            <option value = "brName" <c:if test="${param.field=='brName'}">selected</c:if>>지점명</option>
	            <option value = "mcatName" <c:if test="${param.field=='mcatName'}">selected</c:if>>대분류</option>
	        </select>
	        <input type="search" name = "keyword" value = "${param.keyword}" class = "srchText">
	        <input type="submit" value="검색" class = "srchBtn">
	        <input type="button" value="전체 보기" onclick = "location.href='${cp}/item/search'" class = "allBtn">
        </form>
    </div>
    <hr>
        <c:choose>
	        <c:when test = "${not empty iDtos}">
	        <table style = "text-align: center">
		           	<thead>
		           		<tr style = "height: 40px">
			           		<td><strong>상품 번호</strong></td>
			           		<td><strong>대분류</strong></td>
			           		<td><strong>상품 이미지</strong></td>
			           		<td><strong>상품명</strong></td>
			           		<td><strong>가  격</strong></td>
			           		<td><strong>구  매</strong></td>
			           		<td></td>
		           		</tr>
		           	</thead>
		           	<c:forEach var="iDto" items="${iDtos}">
	           			<tr>
			                <td class = "itemNo">
			                    ${iDto.itemNo}
			                </td>
			                 <td class = "mcatName">
			                    ${iDto.mcatName}
			                </td>
			                <td class = "itemImg">
			                    <a href = "${cp}/item/detail?itemNo=${iDto.itemNo}"><img src = "${cp }/resources/uploads/admin/item/${iDto.tImg}" class = "itemThumbNails"></a>
			                </td>
			                <td class = "itemName">
			                	<span class = "itemNames">
			                		<a href = "${cp}/item/detail?itemNo=${iDto.itemNo}">${iDto.itemName}</a><br><br>
			                	</span>
			                	<span class = 'itemStocks'>매장 재고:
			                		<c:choose>
					               		<c:when test="${not empty iDto.brName}">
					                		${iDto.brName} ${iDto.stkCnt}개
					               		</c:when>
					               		<c:otherwise>
					               			(입고전)<br>
					               		</c:otherwise>
				               		</c:choose>
		        				</span><br>
		                   		${iDto.hash}
		                	</td>
			                <td class = "itemPrice">
			                	<fmt:formatNumber value="${iDto.price}" type="number"/> 원
			                </td>
			                <c:choose>
				                <c:when test="${iDto.stkCnt>0}">
					                <td>
					                	<input type = "button" value="장바구니"
					                	class="cartBtn" onclick="addCart('${cp}',${not empty id},${IpOrd=='true'},${iDto.brNo},${iDto.itemNo},1,'search')">
					                	<input type = "button" value="바로구매"
					                	class="pcBtn" onclick="purchase('${cp}',${not empty id},${IpOrd=='true'},${iDto.brNo},${iDto.itemNo},1)">
					                </td>
				                </c:when>
				                <c:otherwise>
				                	<td>
				                		재고 없음
				                	</td>
			                	</c:otherwise>
			                </c:choose>
	            		</tr>
	            	</c:forEach>
	        </table>
	        </c:when>
	        <c:otherwise>
	        	<div class = "nothing">
					<h3>검색 결과가 없습니다.</h3>
				</div>
	        </c:otherwise>
       </c:choose>
    </div>
</body>
<script type="text/javascript" src="${cp}/resources/js/addCart.js"></script>
<script type="text/javascript" src="${cp}/resources/js/purchase.js"></script>
</html>