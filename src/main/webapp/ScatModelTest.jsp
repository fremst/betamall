<%@page import="com.betamall.dao.ScatDao"%>
<%@page import="com.betamall.dto.ScatDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.betamall.dto.McatDto"%>
<%@page import="com.betamall.dao.McatDao"%>
<%@page import="com.betamall.dto.ItemDto"%>
<%@page import="com.betamall.dao.ItemDao"%>
<%@page import="com.betamall.dto.ManagerDto"%>
<%@page import="com.betamall.dao.ManagerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	McatDao mcatDao = McatDao.getInstance();
	ScatDao scatDao = ScatDao.getInstance();
	
	ArrayList<McatDto> mcatDtos = mcatDao.selectAll();
	ArrayList<ScatDto> scatDtos = scatDao.selectNDel();
			
//	System.out.println(mcatDtos);
	
//	for(McatDto m:mcatDtos) {
//		System.out.println(m.getMcatName());
//	}
	
//	for(int i=0; i<mcatDtos.size(); i++){
//		System.out.println(mcatDtos.get(i));
//	}

	for(ScatDto s:scatDtos) {
		if(s.getMcatNo()==2){
			System.out.println(s);
		}
	}
%>