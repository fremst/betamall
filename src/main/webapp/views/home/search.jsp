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
		width: 1000px;
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
		width: 1050px;
		text-align: center;
		margin: auto;
	}
	.itemNo{
		width: 80px;
	}
	.itemImg{
		width: 200px;
	}
	.itemName{
		width: 600px;
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
    .pcBtns{
    	width: 80px;
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
	        <input type="text" name = "keyword" value = "${param.keyword}" class = "srchText">
	        <input type="submit" value="검색" class = "srchBtn">
        </form>
    </div>
    <hr>
        <c:choose>
	        <c:when test = "${not empty iDtos}">
	        <table style = "text-align: center">
		           	<thead>
		           		<tr style = "height: 40px">
			           		<td><strong>상품 번호</strong></td>
			           		<td><strong>상품 이미지</strong></td>
			           		<td><strong>상품명</strong></td>
			           		<td><strong>가  격</strong></td>
			           		<td><strong>구  매</strong></td>
			           		<td></td>
		           		</tr>   
		           	</thead>
		           	<c:forEach begin = "0" end = "${iDtos.size()-1}" varStatus="status">
		           		<c:if test="${status.first or (iDtos[status.index].itemNo != iDtos[status.index-1].itemNo)}">
		           			<tr>
			                <td class = "itemNo">
			                    ${iDtos[status.index].itemNo}
			                </td>
			                <td class = "itemImg">
			                    <a href = "${cp}/item/detail?itemNo=${iDtos[status.index].itemNo}"><img src = "${cp }/resources/uploads/admin/item/${iDtos[status.index].tImg}" class = "itemThumbNails"></a>
			                </td>
			                <td class = "itemName">
			                	<span class = "itemNames">
			                		<a href = "${cp}/item/detail?itemNo=${iDtos[status.index].itemNo}">${iDtos[status.index].itemName}</a><br><br>
			                	</span>
			                	<span class = 'itemStocks'>매장 재고:
			                	<c:forEach begin = "0" end = "${iDtos.size()}" varStatus="status2">
			                		<c:if test="${iDtos[status.index].itemNo == iDtos[status2.index].itemNo}">
						                <c:choose>
						               		<c:when test="${not empty iDtos[status2.index].brName}">
						                		${iDtos[status2.index].brName} ${iDtos[status2.index].stkCnt}개
						                		<c:if test="${iDtos[status2.index+1].itemNo == iDtos[status2.index].itemNo}">/</c:if>
						               		</c:when>
						               		<c:otherwise>
						               			없음<br>
						               		</c:otherwise>
						                </c:choose>
				               		</c:if>
			               		</c:forEach>
		        				</span><br>
		                   		${iDtos[status.index].hash}
		                		</td>
			                <td class = "itemPrice">
			                	<fmt:formatNumber value="${iDtos[status.index].price}" type="number"/> 원
			                </td>
			                <td>
			                	<a href = "#" class = "pcBtns">장바구니</a><br><br>
			                	<a href = "#" class = "pcBtns">바로구매</a>
			                </td>
			            	</tr>
		            	</c:if>
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
</html>