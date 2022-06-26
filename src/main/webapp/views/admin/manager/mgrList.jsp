<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점장 관리</title>
	<link rel="stylesheet" href="${cp}/resources/css/brMgrList.css">
	<link rel="stylesheet" href="${cp}/resources/css/mgrList.css">
<script type="text/javascript">
	window.onlaod = getInfo(1)

	function getInfo(pageNum){
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = xhr.responseText;
					let pageData = JSON.parse(data)[0];
					let mgrData = JSON.parse(data)[1];
					
					let startPageNum = pageData.startPageNum;
					let endPageNum = pageData.endPageNum;
					let pageCountPerPage = pageData.pageCountPerPage;
					let pageCount = pageData.pageCount;
					
					let tbody = document.getElementById('tbody');
					
					let mgrs = tbody.childNodes;
					for(let i = mgrs.length-1; i>1; i--){
						tbody.removeChild(mgrs.item(i));
					}
					for(let i=0; i<mgrData.length; i++){
						tbody.innerHTML+=
							 "<td class='mgrInfo'>"+mgrData[i].mgrNo+"</td>"
							+"<td class='mgrInfo'><img src = ${cp}/resources/uploads/admin/manager/"+mgrData[i].mgrImg+" style='width: 100px; height:100px'></td>"
							+"<td class='mgrInfo'>"+mgrData[i].brName+"</td>"
							+"<td class='mgrInfo'>"+mgrData[i].brTel+"</td>"
							+"<td class='mgrInfo'>"+mgrData[i].brAddr+"</td>"
							+"<td class='mgrInfo'>"+mgrData[i].mgrName+"</td>"
							+"<td class='mgrInfo'>"+mgrData[i].mgrTel+"</td>"
							+"<td class='mgrInfo'>"+mgrData[i].mgrEmail+"</td>"
							+"<td class='mgrInfo'><a href ='${cp}/admin/manager/update?mgrNo="+mgrData[i].mgrNo+"''>관리</a></td>"
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
			xhr.open('post','${cp }/admin/manager/list?pageNum='+pageNum,true);
			xhr.send();
	}
</script>
</head>
<body>
	<h2 id="mgrListTitle">점장 관리</h2>
    <div id="mainWrap">
		<table id="mgrTable" align="center" style="text-align: center">
			<tbody id = "tbody">
				<tr>
					<th class="mgrInfo_header">점장 번호</th>
					<th class="mgrInfo_header">대표 사진</th>
					<th class="mgrInfo_header">지점명</th>
					<th class="mgrInfo_header">지점 전화번호</th>
					<th class="mgrInfo_header">지점 주소</th>
					<th class="mgrInfo_header">점장명</th>
					<th class="mgrInfo_header">점장 전화번호</th>
					<th class="mgrInfo_header">점장 이메일</th>
					<th class="mgrInfo_header">&nbsp;&nbsp;관리&nbsp;&nbsp;</th>
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
				<input type="button" value = "등록하기" id="newBtn" onclick="location.href = '${cp}/admin/manager/insert'">
			</div>
		</c:if>
    </div>
</body>
</html>