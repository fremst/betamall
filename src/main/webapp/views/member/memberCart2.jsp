<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@page import="com.betamall.dto.ItemDto" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link rel="stylesheet" href="${cp}/resources/css/mbrCart.css">
</head>
<body>
<div id="cartWrap">
<h2>장바구니</h2>
<hr>
</div>
</body>
<script type="text/javascript" src="${cp}/resources/js/addCart.js"></script>
<script type="text/javascript">
	window.onlaod = getInfo()
	
	function getInfo(){
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = JSON.parse(xhr.responseText);
					
					let mbrNo = data.mbrNo;
					let ordBrList = data.ordBrList;
					let ordItemList = data.ordItemList;
					let ordCntList = data.ordCntList;
					let ordItemPerBr = data.ordItemPerBr;
					
					let totAmt = data.totAmt;
					let discAmt = data.discAmt;
					let delFee = data.delFee;
					
					let cartWrap = document.getElementById("cartWrap");
					
					cartWrap.innerHTML = "";
					if(ordBrList == null || ordBrList.length == 0){
						cartWrap.innerHTML +=
							"<div class='nothing'>"
						  + "<h3>주문 상품이 없습니다.</h3>"
				          + "</div>";
					}else{
						cartWrap.innerHTML += "<form method='post' action='${cp}/member/cart' id='orderForm'>"
						   "<div id='itemListArea'>";
						   
						let oipbInd = 0;
						for(let brInd = 0; brInd < ordBrList.length; brInd++){
							if(brInd == 0 || ordBrList[brInd].brNo != ordBrList[brInd-1].brNo){
								oipbInd++;
								cartWrap.innerHTML +=
									 "<fieldset class='itemInfoField'>"
									+"  <legend>"
									+"    <span class='legendText'>"+ordBrList[brInd].brName+"</span>"
									+"  </legend>"
									+"	<input type='hidden' value='"+ordBrList[brInd].brNo+"' name='brNo'>"
									+"  <table id='orderList"+brInd+"'>"
									+"    <tr>"
									+"      <th class='itemNo'>상품 번호</th>"
									+"      <th>상품 이미지</th>"
									+"    	<th>상품명</th>"
									+"    	<th>정 가</th>"
									+"    	<th>수 량</th>"
									+"    	<th>총 액</th>"
									+"    	<th></th>"
									+"    </tr>"
									+"  </table>"
								    +"</fieldset>";
									
									for(let itemInd = brInd; itemInd < brInd+ordItemPerBr[oipbInd-1]; itemInd++){
										
										let tbody = document.getElementById("orderList"+brInd);
										
										if(itemInd == brInd || ordBrList[itemInd].brNo == ordBrList[itemInd-1].brNo){
										
											let tr = document.createElement('tr');
											
											let tdItemNo = document.createElement('td');
											let tdItemtImg = document.createElement('td');
											let tdItemName = document.createElement('td');
											let tdItemPrice = document.createElement('td');
											let tdItemCnt = document.createElement('td');
											let tdTotAmt = document.createElement('td');
											let tdAddCart = document.createElement('td');

											tdItemNo.innerHTML +=
											   ordItemList[itemInd].itemNo
											   +"<input type='hidden' value='"+ordItemList[itemInd].itemNo+"' name='itemNofbr"+ordBrList[brInd].brNo+"'>";
											tdItemtImg.innerHTML =
											   "    <a href='${cp}/item/detail?itemNo="+ordItemList[itemInd].itemNo+"'>"
											   +"      <img src='${cp}/resources/uploads/admin/item/"+ordItemList[itemInd].detImg+"' class='itemThumbNail'>"
											   +"    </a>";
											tdItemName.innerHTML =
											   "    <a href='${cp}/item/detail?itemNo="+ordItemList[itemInd].itemNo+"'>"+ordItemList[itemInd].itemName+"</a><br><br>";
											tdItemPrice.innerHTML =		   
											        ordItemList[itemInd].price +"원";
											tdItemCnt.innerHTML =
											    "    <a href=\"javascript:addCart('${cp}',${not empty id},${IpOrd=='true'},"+ordBrList[itemInd].brNo+","+ordItemList[itemInd].itemNo+",-1,'cart');javascript:getInfo();\">"
											   +"  	   <b>－</b>"
											   +"  	 </a>"
											   +	 ordCntList[itemInd]+"개"
											   +"  <input type='hidden' value='${ordCntList[itemInd]}' name='cntfbr${ordBrList[brInd].brNo}'>"
											   +"    <a href=\"javascript:addCart('${cp}',${not empty id},${IpOrd=='true'},"+ordBrList[itemInd].brNo+","+ordItemList[itemInd].itemNo+",1,'cart');javascript:getInfo();\">"
											   +"  	   <b>＋</b>"
											   +"  	 </a>";
											tdTotAmt.innerHTML =
											     ordItemList[itemInd].price*ordCntList[itemInd]+"원";
											tdAddCart.innerHTML =
											   "    <a href=\"javascript:addCart('${cp}',${not empty id},${IpOrd=='true'},"+ordBrList[itemInd].brNo+","+ordItemList[itemInd].itemNo+",0,'cart');javascript:getInfo();\">"
											   +"Ⅹ</a>";
											   
											tr.appendChild(tdItemNo);
											tr.appendChild(tdItemtImg);
											tr.appendChild(tdItemName);
											tr.appendChild(tdItemPrice);
											tr.appendChild(tdItemCnt);
											tr.appendChild(tdTotAmt);
											tr.appendChild(tdAddCart);
											
											tbody.appendChild(tr);
										}
									}
							}
						}
						
					}
				
				let pmtdiv = document.createElement('div');
				pmtdiv.innerHTML +=
						"<div>"
						+" <fieldset class='pmtInfoField'>"
						+"		<legend>"
						+"			<span class='legendText'>결제 금액</span>"
						+"		</legend>"
						+"		총 주문 금액: "+totAmt+" 원<br>"
						+"		<input type='hidden' value='"+totAmt+"' name='totAmt'>"
						+"		할인 금액: "+discAmt+" 원<br>"
						+"		<input type='hidden' value='"+discAmt+"' name='discAmt'>"
						+"		배송비: "+delFee+" 원 x "
						+       ordItemPerBr.length
						+"		= " + delFee*ordItemPerBr.length+" 원"
						+"      <input type='hidden' value='"+delFee*ordItemPerBr.length+"'name='totDelFee'><br>"
						+"		<hr>"
						+"		결제 예정 금액: "+ (totAmt - discAmt + delFee*ordItemPerBr.length) + "원"
						+" </fieldset>"
						+"  <div class='btnArea'>"
						+"		<input type='button' value='계속 쇼핑하기' class='cancelBtn' onclick='history.back()'>"
						+"		<input type='submit' value='주문하기' class='orderBtn'>"
						+"	</div>";
				cartWrap.append(pmtdiv);
				}
			}
			
			xhr.open('post','${cp }/member/cart',true);
			xhr.send();
	}
</script>
</html>