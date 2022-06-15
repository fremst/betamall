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
                <td><input name="itemName"></td>
            </tr>
            <tr>
                <td>해쉬태그</td>
                <td><input name="hash"></td>
            </tr>
            <tr>
                <td>가격</td>
                <td><input name="price"></td>
            </tr>
            <tr>
                <td>썸네일</td>
                <td><input type="file" name="tImg"></td>
            </tr>
            
            <tr>
                <td>상세이미지</td>
                <td><input type="file" name="detImg"></td>
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
</body>
<script type="text/javascript">
	function checkChange(n) {
		console.log(n);
		console.log(22);
		//if(document.getElementById('scatNo1').value != n){
			document.getElementById('scatNo'+n).setAttribute("hidden", "false");
		//}
	}
</script>
</html>