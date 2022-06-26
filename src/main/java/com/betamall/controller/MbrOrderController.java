package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.OrdItemDao;
import com.betamall.dao.OrderDao;
import com.betamall.dao.StockDao;
import com.betamall.dto.OrdItemDto;
import com.betamall.dto.OrderDto;

@WebServlet("/member/order")
@SuppressWarnings("serial")
public class MbrOrderController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // '주문하기' 버튼을 클릭했을 때

	    	int mbrNo = Integer.parseInt(req.getParameter("mbrNo"));
	    	String[] _brNos = req.getParameterValues("brNo");
	    	for(int i = 0; i< _brNos.length; i++) {
	    		int brNo = Integer.parseInt(_brNos[i]);
	    		
	        	OrderDao ordDao = OrderDao.getInstance();
	        	int ordNo = ordDao.getOrdNo();
	            OrderDto ordDto = new OrderDto(ordNo, mbrNo, brNo, null, "결제대기", null, null, null);
	            int n1 = ordDao.insert(ordDto);
	            
	            if(n1 < 0) {
	            	System.out.println("에러 발생");
	            }
	            
	        	String[] _itemNos = req.getParameterValues("itemNofbr"+brNo);
	        	String[] _cntNos = req.getParameterValues("cntfbr"+brNo);
	        	
	        	for(int j = 0; j<_itemNos.length; j++) {
	        		
	        		int itemNo = Integer.parseInt(_itemNos[j]);
	        		int ordCnt = Integer.parseInt(_cntNos[j]);
	        		
	        		OrdItemDao ordItemDao = OrdItemDao.getInstance();
	            	OrdItemDto ordItemDto = new OrdItemDto(ordNo, itemNo, ordCnt, null, 0, null);
	            	
	            	int n2 = ordItemDao.insert(ordItemDto);
	            	int n3 = StockDao.getInstance().changeStock(ordNo, -1);

	            	if(!(n2*n3>0)) {
	                	System.out.println("에러 발생");
	                }
	        	}
	    	}

	    	req.getSession().setAttribute("IpOrd", "true");
	    	resp.sendRedirect(req.getContextPath() + "/member/payment");
	    }
}
