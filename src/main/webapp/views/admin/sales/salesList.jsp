<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<% request.setCharacterEncoding("utf-8");%>
<% response.setContentType("text/html; charset=utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 조회</title>
    <link rel="stylesheet" href="${cp}/resources/css/layout.css">
    <link rel="stylesheet" href="${cp}/resources/css/salesList.css">
</head>
<style type="text/css">
	#columnchart_material{
		width: 600px;
		height: 300px;
		margin: auto;
		font-size: 10pt;
	}
</style>
<body>
<div id="salesListWrap">
    <h2 id="salesListTitle">매출 조회</h2>
    <hr>
    <fieldset id="serachCondition">
        <legend><h3 id="title">검색 조건</h3></legend>
        <form action="${cp}/admin/sales/list" method="get">
            <div id="textArea">
                <div id="category">
                    <input type="checkbox" id="catNameChk" name="catNameChk">
                    <label for="catName">분류명</label>
                    <div>
                        &emsp;<input type="radio" name="cat" id="cat" value="mcat" checked="checked">
                        <label for="mcatName">대분류</label>
                        <select name="mcatName" id="mcatName">
                            <c:forEach var="mcat" items="${mcatList}">
                                <option value="${mcat.mcatName}">${mcat.mcatName}</option>
                            </c:forEach>
                        </select>
                        <br>
                        &emsp;<input type="radio" name="cat" value="scat">
                        <label for="scatName">소분류</label>
                        <select name="scatName" id="scatName">
                            <c:forEach var="scat" items="${scatList}">
                                <option value="${scat.scatName}">${scat.scatName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div id="branch">
                    <input type="checkbox" id="brNameChk" name="brNameChk">
                    <label for="brName">지점명</label>
                    <select name="brName" id="brName">
                        <c:forEach var="br" items="${branchList}">
                            <option value="${br.brName}">${br.brName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="orderDate">
                    <input type="checkbox" id="ordDateChk" name="ordDateChk">
                    <label for="ordDate">주문일</label>
                    <input type="date" name="ordStartDate" id="ordStartDate">
                    ~
                    <input type="date" name="ordEndDate" id="ordEndDate">
                </div>
                <div id="paymentDate">
                    <input type="checkbox" id="pmtDateChk" name="pmtDateChk">
                    <label for="pmtDate">결제일</label>
                    <input type="date" name="pmtStartDate" id="pmtStartDate">
                    ~
                    <input type="date" name="pmtEndDate" id="pmtEndDate">
                </div>
                <div id="item">
                    <input type="checkbox" id="itemNameChk" name="itemNameChk">
                    <label for="itemName">상품명</label>
                    <input type="text" name="itemName" id="itemName">
                </div>
            </div>
            <div id="btnArea">
                <input type="button" onclick="javascript:getInfo()" value="검색" name="itemName" id="searchBtn">
                <input type="button" value="전체 보기" name="allBtn" id="allBtn"
                       onclick="location.href='${cp}/admin/sales/list'">
            </div>
        </form>
    </fieldset>
    <div id="columnchart_material"></div>
    <br>
    <h3>조회 결과</h3>

    <div id="salesList">
        <c:set var="totPmtAmt" value="0"/>
        <table id="salesTable">
        	<tbody id="tbody">
	            <tr>
	                <th class="salesInfo_header">주문번호</th>
	                <th class="salesInfo_header">대분류</th>
	                <th class="salesInfo_header">소분류</th>
	                <th class="salesInfo_header">상품명</th>
	                <th class="salesInfo_header">지점명</th>
	                <th class="salesInfo_header">판매수량</th>
	                <th class="salesInfo_header">판매금액</th>
	                <th class="salesInfo_header">주문일</th>
	                <th class="salesInfo_header">결제일</th>
	            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

    google.charts.load('current', {'packages': ['bar']});
    google.charts.setOnLoadCallback(getInfo);

    function getInfo() {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let data = JSON.parse(xhr.responseText);

                let salesList = data.salesList;
                let mcatList = data.mcatList;
                let scatList = data.scatList;
                let branchList = data.branchList;

            	// 차트 그리기
            	 
                let catArr = [];
                let dataArr = [];
                
            	for (let i = 0; i < mcatList.length; i++) {
                    catArr.push(mcatList[i].mcatName);
                } 
            	catArr.unshift('');
            	
            	let salesListDiffLength = salesList.length;
            
                for (let i=0; i<salesList.length; i++){
                	if(i==0 || salesList[i].ordDate.substr(0,7) != salesList[i-1].ordDate.substr(0,7)){
                		tempDataArr = [];
                    	for (let i = 0; i < mcatList.length; i++) {
                            tempDataArr.push(0);
                        }
                	}
                		
    	                for (let j = 0; j < mcatList.length; j++) {
    	                        if (mcatList[j].mcatName == salesList[i].mcatName) {
    	                        	tempDataArr[j] = parseInt(tempDataArr[j]) + parseInt(salesList[i].pmtAmt);
    	                        }
    	                }
    	                
    	                if(i==salesList.length-1 || salesList[i+1].ordDate.substr(0,7) != salesList[i].ordDate.substr(0,7)){
	    	                tempDataArr.unshift(salesList[i].ordDate.substr(0,7));
	    	                dataArr.push(tempDataArr);
    	                }
                }
                
                drawChart(catArr, dataArr);
                
                // 조회 결과 추가
                
        		let tbody = document.getElementById('tbody');
        		
        		let sales = tbody.childNodes;
        		for(let i = sales.length-1; i>1; i--){
        			tbody.removeChild(sales.item(i));
        		}
        		
        		let totPmtAmt = 0;
        		
        		for(let i=0; i<salesList.length; i++){
        			tbody.innerHTML+=
        				 "<td class='salesInfo'>"+salesList[i].ordNo+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].mcatName+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].scatName+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].itemName+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].brName+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].ordCnt.toLocaleString('en-US')+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].pmtAmt.toLocaleString('en-US')+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].ordDate+"</td>"
        				+"<td class='salesInfo'>"+salesList[i].pmtDate+"</td>";
        				
        				totPmtAmt += salesList[i].pmtAmt;
        		}
        		
        		tbody.innerHTML+=
        			 "<td class='salesInfo'><b>합계</b></td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>"+totPmtAmt.toLocaleString('en-US')+"</td>"
	                +"<td class='salesInfo'>-</td>"
	                +"<td class='salesInfo'>-</td>";
            }
        }
        
        let cat=document.getElementById('cat').value
        let mcatName=document.getElementById('mcatName').value
        let scatName=document.getElementById('scatName').value
        let brName=document.getElementById('brName').value
		let ordStartDate=document.getElementById('ordStartDate').value
		let ordEndDate=document.getElementById('ordEndDate').value
		let pmtStartDate=document.getElementById('pmtStartDate').value
		let pmtEndDate=document.getElementById('pmtEndDate').value
		let itemName=document.getElementById('itemName').value
		
		let catNameChk=document.getElementById('catNameChk').checked
		let brNameChk=document.getElementById('brNameChk').checked
		let ordDateChk=document.getElementById('ordDateChk').checked
		let pmtDateChk=document.getElementById('pmtDateChk').checked
		let itemNameChk=document.getElementById('itemNameChk').checked
        
        xhr.open('post', '${cp}/admin/sales/list?catNameChk='+catNameChk
        		+'&cat='+cat
        		+'&mcatName='+mcatName
        		+'&scatName='+scatName
        		+'&brNameChk='+brNameChk
        		+'&brName='+brName
        		+'&ordDateChk='+ordDateChk
        		+'&ordStartDate='+ordStartDate
        		+'&ordEndDate='+ordEndDate
        		+'&pmtDateChk='+pmtDateChk
        		+'&pmtStartDate='+pmtStartDate
        		+'&pmtEndDate='+pmtEndDate
        		+'&itemNameChk='+itemNameChk
        		+'&itemName='+itemName, true);
        xhr.send();
    }
    
    function drawChart(catArr, dataArr) {
        
          var data = new google.visualization.DataTable();
          
		    data.addColumn('string', catArr[0]);
		    for (let i=1; i<catArr.length; i++){
			    data.addColumn('number', catArr[i]);
		    }
		    for (let i=dataArr.length-1; i>=0; i--){
		    	data.addRow(dataArr[i]);
		    }
		 
		 const options = {
		            chart: {
		                title: '베타몰 매출현황',
		                subtitle: '매출조회',
		            }
		        };

        const chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        chart.draw(data, google.charts.Bar.convertOptions(options));

    }

</script>
<script type="text/javascript">
    let ordStartDate = document.getElementById('ordStartDate');
    let ordEndDate = document.getElementById('ordEndDate');
    let pmtStartDate = document.getElementById('pmtStartDate');
    let pmtEndDate = document.getElementById('pmtEndDate');

    ordStartDate.addEventListener('change', function () {
        if (ordStartDate.value)
            ordEndDate.min = ordStartDate.value;
    }, false);
    pmtStartDate.addEventListener('change', function () {
        if (pmtStartDate.value)
            pmtEndDate.min = pmtStartDate.value;
    }, false);
    pmtEndDate.addEventListener('change', function () {
        if (pmtEndDate.value)
            pmtStartDate.max = pmtEndDate.value;
    }, false);
    ordEndDate.addEventListener('change', function () {
        if (ordEndDate.value)
            ordStartDate.max = ordEndDate.value;
    }, false);
</script>
</html>