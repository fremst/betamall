<%@page import="java.sql.Connection"%>
<%@page import="com.betamall.util.JdbcUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection con = JdbcUtil.getCon();
	out.print(con);
	con.close();
%>
</body>
</html>