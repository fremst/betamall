<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점 조회/수정/삭제</title>
	<link rel="stylesheet" href="${cp}/resources/css/brMgrList.css">
	<link rel="stylesheet" href="${cp}/resources/css/brList.css">
<script type="text/javascript">
	window.onload = function(){
		getInfo(1);
		if(${res=='success'}){
			alert('성공적으로 처리되었습니다.');
		}
	}

	function getInfo(pageNum){
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = xhr.responseText;
					let pageData = JSON.parse(data)[0];
					let brData = JSON.parse(data)[1];
					
					let startPageNum = pageData.startPageNum;
					let endPageNum = pageData.endPageNum;
					let pageCountPerPage = pageData.pageCountPerPage;
					let pageCount = pageData.pageCount;
					
					let tbody = document.getElementById('tbody');
					
					let brs = tbody.childNodes;
					for(let i = brs.length-1; i>1; i--){
						tbody.removeChild(brs.item(i));
					}
					for(let i=0; i<brData.length; i++){
						tbody.innerHTML+=
							 "<td class='brInfo'>"+brData[i].brNo+"</td>"
							+"<td class='brInfo'>"+brData[i].brName+"</td>"
							+"<td class='brInfo'>"+brData[i].brTel+"</td>"
							+"<td class='brInfo'>"+"<a href='tel:brData[i].brAddr'>"+brData[i].brAddr+"</td>"
							+"<td class='brInfo'>"+brData[i].brDate+"</td>"
							+"<td class='brInfo'><img src = '${cp}/resources/uploads/admin/branch/"+brData[i].brImg+"' style='width: 100px; height:100px'></td>"
							+"<td class='brInfo'><a href ='${cp}/admin/branch/update?brNo="+brData[i].brNo+"'>관리</a></td>"
					}
					
					let pageNav = document.getElementById("pageNav");
					let div = document.createElement("div");
					
					pageNav.innerHTML = "";
					div.innerHTML = "";
					if(startPageNum > pageCountPerPage){
						div.innerHTML += "<a href = 'javascript:getInfo(" + (startPageNum-1) + ")'>"+'이전'+"</a>&nbsp";
					}
					for(let i=startPageNum; i<=endPageNum; i++){
						div.innerHTML += "<a href = 'javascript:getInfo(" + i + ")'>"+i+"</a>&nbsp";
					}
					if(pageCount > endPageNum){
						div.innerHTML += "<a href = 'javascript:getInfo(" + (endPageNum+1) + ")'>"+'다음'+"</a>&nbsp";
					}
					pageNav.appendChild(div);
				}
			}
			xhr.open('post','${cp }/admin/branch/list?pageNum='+pageNum,true);
			xhr.send();
	}

</script>
</head>
<body>
	<h2 id="brListTitle">지점 관리</h2>
    <div id="mainWrap">
		<table class = "center">
			<tbody id = "tbody">
				<tr>
					<th class="brInfo_header">지점 번호</th>
					<th class="brInfo_header">지점명</th>
					<th class="brInfo_header">지점 전화번호</th>
					<th class="brInfo_header">지점 주소</th>
					<th class="brInfo_header">지점 개업일</th>
					<th class="brInfo_header">대표 사진</th>
					<th class="brInfo_header">&nbsp;&nbsp;관리&nbsp;&nbsp;</th>
				</tr>
				<c:forEach var="i" begin="1" end="3">
					<tr><c:forEach var="j" begin="1" end="9"><td></td></c:forEach></tr>
				</c:forEach>
			</tbody>
		</table>
	    <div id="pageNav">
		</div>
		<c:if test="${role == 'admin0'}">
			<div id = "newBtnArea">
				<input type="button" value = "등록하기" id="newBtn" onclick="location.href = '${cp}/admin/branch/insert'">
			</div>
		</c:if>
    </div>
</body>
</html>