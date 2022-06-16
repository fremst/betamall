<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
  <table>
        <tr>
			<td>상품대분류번호</td>
            <td><input name="mcatno"></td>
       	</tr>
        <tr>
            <td>상품소분류번호</td>
            <td>
            <select name = "scatno">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			</select>
			</td>
         </tr>
            <tr>
                <td>상품명</td>
                <td><input name="itemname"></td>
            </tr>
            <tr>
                <td>해쉬태그</td>
                <td><input name="price"></td>
            </tr>
            <tr>
                <td>가격</td>
                <td><input name="price"></td>
            </tr>
            <tr>
                <td>썸네일</td>
                <td><input type="file" name="timg"></td>
            </tr>
            <tr>
                <td>상세이미지</td>
                <td><input type="file" name="detimg"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="button" value="등록" onclick="itemInsert()">
               	</td>
            </tr>
</form>
</body>
</html>