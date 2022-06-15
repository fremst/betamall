<%@page import="com.betamall.dto.ItemDto"%>
<%@page import="com.betamall.dao.ItemDao"%>
<%@page import="com.betamall.dto.ManagerDto"%>
<%@page import="com.betamall.dao.ManagerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemDao itemDao = ItemDao.getInstance();
	int n = itemDao.insert(new ItemDto(0, 1, 1,"상품명","t.png","d.png",null,1000,false));
	System.out.println(n);
	
// System.out.println(itemDao.insert(new ItemDto(0, 1, 1,"상품명","t.png","d.png",null,1000,false)));

%>