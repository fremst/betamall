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
	int ordNo = 1;
	
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
	ordNo = ordDao.getOrdNo();
	System.out.println(ordNo);
	
	/* ------- (주문 테이블 생성 확인) ------- */
	System.out.println(ordDao.select(ordNo));
	
	/* -------- 상품별 주문 내역 삽입 ------- */
	int itemNo = 1001;
	int ordCnt = 7;
	OrdItemDto ordItemDto = new OrdItemDto(ordNo, itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));
    
	itemNo = 1002;
	ordCnt = 2;
	ordItemDto = new OrdItemDto(ordNo, itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));
    
	itemNo = 1004;
	ordCnt = 4;
	ordItemDto = new OrdItemDto(ordNo, itemNo, ordCnt, null, 0, null);
    System.out.println(ordItemDao.insert(ordItemDto));

/* ------------- 2. 구매자가 '결제하기' 버튼을 누르면 ------------- */

	/* ------- (주문 총액 계산) ------ */
	System.out.println(ordItemDao.getTotPmt(ordNo));
	
	/* ------- 결제 금액을 받아 와서 결제 테이블에 삽입 ------- */
	System.out.println(pmtDao.insert(new PmtDto(ordNo, ordItemDao.getTotPmt(ordDto.getOrdNo()), "카드", null)));
	ordDto.setOrdDate(ordDao.select(ordNo).getOrdDate());

	/* ------- 주문 상태를 결제 완료로 변경 ------- */
	System.out.println(ordDao.update(new OrderDto(ordNo, ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "결제완료", ordDto.getOrdArrd(), ordDto.getOrdTel())));
	
/* ------------- 3. 점장이 '주문 확인' 버튼을 누르면 ------------- */

	/* ------- 주문 상태를 '발송전'으로 변경 ------ */
	System.out.println(ordDao.update(new OrderDto(ordNo, ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "발송전", ordDto.getOrdArrd(), ordDto.getOrdTel())));
	
/* ------------- 4. 점장이 '발송 완료' 버튼을 누르면 ------------- */

	/* ------- 주문 상태를 '배송중'으로 변경 ------ */
	System.out.println(ordDao.update(new OrderDto(ordNo, ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "배송중", ordDto.getOrdArrd(), ordDto.getOrdTel())));

	/* ------- 재고 변경하기 ------ */
	System.out.println(stkDao.changeStock(ordNo, -1));

/* ------------- 5. 구매자가 '구매 확정' 버튼을 누르면 ------------- */

	/* ------- 주문 상태를 '구매확정'으로 변경 ------ */
	System.out.println(ordDao.update(new OrderDto(ordNo, ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "구매확정", ordDto.getOrdArrd(), ordDto.getOrdTel())));

%>