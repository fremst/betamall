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

/* ------------- 2. 구매자가 '결제하기' 버튼을 누르면 ------------- */
	OrderDto ordDto = ordDao.select(ordNo);

	/* ------- (주문 총액 계산) ------ */
	System.out.println(ordItemDao.getTotPmt(ordNo));
	
	/* ------- 결제 금액을 받아 와서 결제 테이블에 삽입 ------- */
	System.out.println(pmtDao.insert(new PmtDto(ordDto.getOrdNo(), ordItemDao.getTotPmt(ordDto.getOrdNo()), "카드", null)));
	ordDto.setOrdDate(ordDao.select(ordNo).getOrdDate());

	/* ------- 주문 상태를 결제 완료로 변경 ------- */
	System.out.println(ordDao.update(new OrderDto(ordDto.getOrdNo(), ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "결제완료", ordDto.getOrdArrd(), ordDto.getOrdTel())));
	
%>