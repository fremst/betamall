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
	<h2>상품 등록</h2>
    <form id="form" name="form" method="post" enctype="multipart/form-data" action="${cp }/admin/item/insert" onsubmit="">
    	 <!-- 파일업로드를 위해 추가하는 타입 -->
        <table>
        	<tr>
			<td>상품대분류</td>
                <td>
	                <select name="mcatNo" id="mcatNo">
		        		<c:forEach var="m" items="${mDtos}">
		                	<option value = "${m.mcatNo}">${m.mcatName}</option>
		                </c:forEach>
	            	</select>
                </td>
            </tr>
            <tr>
            <td>상품소분류</td>
                <td>
	                <select name="scatNo" id="scatNo">
		        		<c:forEach var="s" items="${sDtos}">
		                	<option value = "${s.scatNo}">${s.scatName}</option>
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
</html>