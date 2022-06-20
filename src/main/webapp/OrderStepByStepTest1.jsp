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
	int ordNo = 0;
	
/* ------------- 0. 구매 전 프로세스 (미완성) ------------- */
	
	/* ------- (첫 재고 추가하기) ------- */
	// System.out.println(stkDao.insert(new StockDto(1005, 3, 1)));

	/* ------- (재고 수량 업데이트하기) ------- */
	
	
	/* ------- (재고 수량 확인 & 구매 유효성 체크) ------- */ 
	// System.out.println(stkDao.select(1001, 1));
	
/* ------------- 1. 구매자가'구매하기' 버튼을 누르면 ------------- */
	
    /* ------- 주문 테이블 생성 ------- */
    int mbrNo = 1;
	int brNo = 1;
    OrderDto ordDto = new OrderDto(0, mbrNo, brNo, null, "결제대기", "서울", "0101234657");
	System.out.println(ordDao.insert(ordDto));
	ordNo = ordDao.getMaxOrdNo();
	System.out.println(ordNo);
	ordDto.setOrdNo(ordNo);
	
	/* ------- (주문 테이블 생성 확인) ------- */
	System.out.println(ordDao.select(ordNo));
	
	/* -------- 상품별 주문 내역 삽입 ------- */
	int itemNo = 1001;
	int ordCnt = 7;
	OrdItemDto ordItemDto = new OrdItemDto(ordDto.getOrdNo(), itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));
    
	itemNo = 1002;
	ordCnt = 2;
	ordItemDto = new OrdItemDto(ordDto.getOrdNo(), itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));
    
	itemNo = 1004;
	ordCnt = 4;
	ordItemDto = new OrdItemDto(ordDto.getOrdNo(), itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));
    
%>