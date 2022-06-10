<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점장 조회/수정/삭제</title>
<style type="text/css">
	table, tr, td {
		border: solid 1px
	}
	#pageNav{
		width: 100px;
		margin: auto;
	}
</style>
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
							 "<td>"+mgrData[i].mgrNo+"</td>"
							+"<td><img src = ${cp}/resources/uploads/admin/manager/"+mgrData[i].mgrImg+" style='width: 100px; height:100px'></td>"
							+"<td>"+mgrData[i].brName+"</td>"
							+"<td>"+mgrData[i].brTel+"</td>"
							+"<td>"+mgrData[i].brAddr+"</td>"
							+"<td>"+mgrData[i].mgrName+"</td>"
							+"<td>"+mgrData[i].mgrTel+"</td>"
							+"<td>"+mgrData[i].mgrEmail+"</td>"
							+"<td><a href ='${cp}/mgrlist'>수정</a></td>"
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
			xhr.open('post','${cp }/admin/mgrlist?pageNum='+pageNum,true);
			xhr.send();
	}

</script>
</head>
<body>
    <h1>점장 조회/수정/삭제</h1>
    <div>
		<table align="center" style="text-align: center">
			<tbody id = "tbody">
				<tr>
					<td>점장 번호</td>
					<td>대표 사진</td>
					<td>지점명</td>
					<td>지점 전화번호</td>
					<td>지점 주소</td>
					<td>점장명</td>
					<td>점장 전화번호</td>
					<td>점장 이메일</td>
					<td>관리</td>
				</tr>
			</tbody>
		</table>
    </div>
    <div id="pageNav">
	</div>
</body>
</html>