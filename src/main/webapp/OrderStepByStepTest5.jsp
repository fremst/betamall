<%@page import="java.sql.Date"%>
<%@page import="com.betamall.dao.PmtDao"%>
<%@page import="com.betamall.dto.PmtDto"%>
<%@page import="com.betamall.dao.StockDao"%>
<%@page import="com.betamall.dto.StockDto"%>
<%@page import="com.betamall.dto.OrderDto"%>
<%@page import="com.betamall.dao.OrderDao"%>
<%@page import="com.betamall.dao.OrdItemDao"%>
<%@page import="com.betamall.dto.OrdItemDto"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	OrderDao ordDao = OrderDao.getInstance();
	OrdItemDao ordItemDao = OrdItemDao.getInstance();
	PmtDao pmtDao = PmtDao.getInstance();
	StockDao stkDao = StockDao.getInstance();
	int ordNo = ordDao.getMaxOrdNo();

/* ------------- 5. 구매자가 '구매 확정' 버튼을 누르면 ------------- */
	OrderDto ordDto = ordDao.select(ordNo);

	/* ------- 주문 상태를 '구매확정'으로 바꾸기 ------ */
	System.out.println(ordDao.update(new OrderDto(ordDto.getOrdNo(), ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "구매확정", ordDto.getOrdArrd(), ordDto.getOrdTel())));

%>