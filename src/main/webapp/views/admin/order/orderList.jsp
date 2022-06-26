<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("utf-8");%>
<% response.setContentType("text/html; charset=utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 관리</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/orderList.css">
</head>
<body>
<div id="orderListWrap">
    <h2 id="orderListTitle">주문 관리</h2>
	<h3>검색 조건</h3>
	<form action="${cp}/admin/order/list" method="get">
		<input type="checkbox" id="ordStaChk" name="ordStaChk">
		<label for="ordSta">주문상태</label>
			<select name="ordSta" id="ordSta">
				<option value="결제완료" >결제완료</option>
				<option value="발송전"<c:if test="${param.ordSta=='발송전'}">selected</c:if>>발송전</option>
				<option value="배송중"<c:if test="${param.ordSta=='배송중'}">selected</c:if>>배송중</option>
				<option value="결제대기"<c:if test="${param.ordSta=='결제대기'}">selected</c:if>>결제대기</option>
				<option value="배송완료"<c:if test="${param.ordSta=='배송완료'}">selected</c:if>>배송완료</option>
				<option value="구매확정"<c:if test="${param.ordSta=='구매확정'}">selected</c:if>>구매확정</option>
				<option value="결제취소"<c:if test="${param.ordSta=='결제취소'}">selected</c:if>>결제취소</option>
			</select>
		<input type="button" onclick="javascript:getInfo()" value = "검색">
		<input type="button" onclick = "location.href='${cp}/admin/order/list'" value = "전체 보기">
	</form>
    <br>
    <h3>${brName} 주문 목록</h3>
    <div id="orderListArea">
    </div>
</div>
</body>
<script type="text/javascript">

window.onload = getInfo();

function getInfo() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    	
        if (xhr.readyState == 4 && xhr.status == 200) {
            let data = JSON.parse(xhr.responseText);
            let orderList = data.orderList;
            console.log(data);
            console.log(orderList);
            
            let orderListArea = document.getElementById('orderListArea');
            
            if(orderList == null || orderList.length == 0){
            	orderListArea.innerHTML = "<div class = 'nothing'>"
						            	 +"<h3>검색 결과가 없습니다.</h3>"
										 +"</div>";
            }else{
            	
            	orderListArea.innerHTML = `<table id='orderTable'><tr><th class='orderInfo_header'>주문번호</th>
             			   <th class='orderInfo_header'>상품번호</th><th class='orderInfo_header'>상품명</th>
			               <th class='orderInfo_header'>수량</th>
			               <th class='orderInfo_header'>총액</th>
			               <th class='orderInfo_header'>주문일자</th>
			               <th class='orderInfo_header'>주문상태</th>
			               <th class='orderInfo_header'>관리</th>
			               <th class='orderInfo_header'>아이디</th>
			               <th class='orderInfo_header'>수신인</th>
			               <th class='orderInfo_header'>연락처</th>
			               <th class='orderInfo_header'>배송지</th></tr></table>`;

        		let tbody = document.querySelector('tbody');
        		let orders = tbody.childNodes;
        		
        		for(let i = orders.length-1; i>1; i--){
        			tbody.removeChild(orders.item(i));
        		}
        		
        		for(let i=0; i<orderList.length; i++){
	    			let HTML=
	    				 "<td class='orderInfo'>"+orderList[i].ordNo+"</td>"
	    				 +" <td class='orderInfo'>"+orderList[i].itemNo+"</td>"
	    				 +" <td class='orderInfo'>"+orderList[i].itemName+"</td>"
	    			  	 +" <td class='orderInfo'>"+orderList[i].ordCnt+"</td>"
	    			  	 +" <td class='orderInfo'>"+(orderList[i].price*orderList[i].ordCnt).toLocaleString('en-US')+"</td>"
	    			  	 +" <td class='orderInfo'>"+orderList[i].ordDate+"</td>"
	    				 +" <td class='orderInfo'>"+orderList[i].ordSta+"</td><td class='orderInfo'>";
 	    			if(orderList[i].ordSta=='결제대기'){
	    				HTML +="<input type='button' value = '주문취소' id='btn"+orderList[i].ordNo
        				+"' onclick=\"javascript:setOrdSta("+orderList[i].ordNo+",'결제취소');javascript:getInfo()\">";
	    			}else if(orderList[i].ordSta=='결제완료'){
	    				HTML +="<input type='button' value = '주문확인' id='btn"+orderList[i].ordNo
        				+"' onclick=\"javascript:setOrdSta("+orderList[i].ordNo+",'발송전');javascript:getInfo()\">";
	    			}else if(orderList[i].ordSta=='발송전'){
	    				HTML +="<input type='button' value = '발송완료' id='btn"+orderList[i].ordNo
        				+"' onclick=\"javascript:setOrdSta("+orderList[i].ordNo+",'배송중');javascript:getInfo()\">";
	    			}else if(orderList[i].ordSta=='배송중'){
	    				HTML +="<input type='button' value = '배송완료' id='btn"+orderList[i].ordNo
        				+"' onclick=\"javascript:setOrdSta("+orderList[i].ordNo+",'배송완료');javascript:getInfo()\">";
		    		}
	    			HTML +=
	    				"</td><td class='orderInfo'>"+orderList[i].mbrId+"</td>"
	                    +"<td class='orderInfo'>"+orderList[i].ordName+"</td>"
	                    +"<td class='orderInfo'>"+orderList[i].ordTel+"</td>"
	                    +"<td class='orderInfo'>"+orderList[i].ordAdr+"</td>";
	    			tbody.innerHTML += HTML;
	    		}
            }
        }
    }
    
    let ordStaChk=document.getElementById('ordStaChk').checked;
    let ordSta=document.getElementById('ordSta').value;
    
    xhr.open('post', '${cp}/admin/order/list?ordStaChk='+ordStaChk+'&ordSta='+ordSta, true);
    xhr.send();
}

function setOrdSta(ordNo, ordSta) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    	
        if (xhr.readyState == 4 && xhr.status == 200) {
            let data = JSON.parse(xhr.responseText);
            let orderList = data.orderList;
            
            console.log(data);
        }
    }
    
    xhr.open('post', '${cp}/ChangeOrdSta?ordNo='+ordNo+'&ordSta='+ordSta, true);
    xhr.send();
}

</script>
</html>