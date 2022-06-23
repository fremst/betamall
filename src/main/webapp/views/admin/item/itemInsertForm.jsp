<%@page import="com.betamall.dto.ScatDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="${cp}/resources/css/itemForm.css">
</head>
<body>

<%--
	@SuppressWarnings("unchecked")
	ArrayList<ScatDto> sDtos = (ArrayList<ScatDto>)request.getAttribute("sDtos");
	out.println(sDtos);
	ArrayList<String> scatNames = null;
	for(int i = 0; i < sDtos.size(); i++){
		scatNames.add(sDtos.get(i).getMcatNo() + sDtos.get(i).getScatName());
	}
	System.out.println(scatNames);
--%>
<c:choose>
	<c:when test="${role == 'admin0' }">
	<h2>상품 등록</h2>
    <form id="form" name="form" method="post" enctype="multipart/form-data" action="${cp }/admin/item/insert">
    	 <!-- 파일업로드를 위해 추가하는 타입 -->
        <table>
        	<tr>
			<td>상품분류</td>
				<td>
            	<select name="cats">
					<c:forEach var="m" items="${mDtos}">
						<optgroup label="${m.mcatName}">
							<c:forEach var = "s" items = "${sDtos}">
								<c:if test="${m.mcatNo == s.mcatNo}">
									<option value = "${s.scatName}">${s.scatName}</option>
								</c:if>
							</c:forEach>
						</optgroup>
					</c:forEach>
				</select>
				</td>    
            </tr>
            <tr>
                <td>상품명</td>
                <td><input name="itemName" required></td>
            </tr>
            <tr>
                <td>해쉬태그</td>
                <td><input name="hash"></td>
            </tr>
            <tr>
                <td>가격</td>
                <td><input name="price" required></td>
            </tr>
            <tr>
                <td>썸네일</td>
                <td><input type="file" id="tImg" name="tImg" accept="image/*" onchange="setThumbnail(event)" required></td>
            	<td><div id= "thumbNailImg"></div></td>
            </tr>
            
            <tr>
                <td>상세이미지</td>
                <td><input type="file" name="detImg" required></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                <input type="submit" value="등록">
                   <!-- <input type="button" value="목록"
                    onclick="location.href='${path}/admin/item/regItemList.jsp'"> --> 
                </td>
            </tr>
        </table>
    </form>
   </c:when>
   <c:otherwise>
   		<button onclick="location.href='${cp }/home'">홈으로</button>
   </c:otherwise>
</c:choose>

</body>
<script type="text/javascript">

	function setThumbnail(event) {
	    let reader = new FileReader();
	
	    reader.onload = function(event) {
	        let img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	        img.setAttribute("id", "tImg");
	        document.querySelector("div#thumbNailImg").innerHTML = "<p class = 'thumbNailMsg'>[미리 보기]</p>";
	        document.querySelector("div#thumbNailImg").appendChild(img);
	    };
	    reader.readAsDataURL(event.target.files[0]);
	}
</script>
</html>