<%@page import="com.betamall.dao.ScatDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ScatDao scatDao = ScatDao.getInstance();
	System.out.println(scatDao.selectByName("서류함"));
%>