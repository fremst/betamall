<%@ page import="com.betamall.dao.BoardDao" %>
<%@ page import="com.betamall.dto.BoardDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>popUp</title>
</head>
<script type="text/javascript">
    function setCookie(name, value, expiredays) {
        const date = new Date();
        date.setDate(date.getDate() + expiredays);
        document.cookie = name + "=" + escape(value) + "; expires=" + date.toUTCString() + "; path=/";
    }

    function closePopup() {
        if (document.getElementById("check").value) {
            setCookie("popupYN", "N", 1);
            self.close();
        }
    }
</script>
<%
    BoardDao boardDao = BoardDao.getInstance();
    ArrayList<BoardDto> boardDtos = boardDao.selectAll();
    request.setAttribute("boardDtos", boardDtos);
%>
<body>
<c:forEach var="brd" items="${boardDtos}">
    <div>
        <c:if test="${brd.popUp}">
            <img src="${cp}/resources/uploads/admin/board/${brd.brdImg}" style="width: 650px; height: 370px;"><br>
            <map name="popup" id="popup">
            </map>
        </c:if>
    </div>
</c:forEach>
<div align="right">
    <input type="checkbox" value="닫기" id="check" onclick="closePopup();">
    <label for="chkbox" style="text-decoration: underline">[ 하루종일 안보기 ]</label>&nbsp;&nbsp;
    <input type="checkbox" value="닫기" id="chkbox" onclick="self.close();">
    <label for="chkbox">[ 닫기 ]</label>
</div>
</body>
</html>