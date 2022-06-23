package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ItemDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.OrdItemDao;
import com.betamall.dao.OrderDao;
import com.betamall.dao.StockDao;
import com.betamall.dto.BranchDto;
import com.betamall.dto.ItemDto;
import com.betamall.dto.OrdItemDto;
import com.betamall.dto.OrderDto;

@WebServlet("/member/cart")
@SuppressWarnings("serial")
public class MbrCartController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	HttpSession session = req.getSession();
    	
    	MemberDao mbrDao = MemberDao.getInstance();
    	ItemDao itemDao = ItemDao.getInstance();
    	BranchDao brDao = BranchDao.getInstance();
    	
    	int mbrNo = mbrDao.selectById((String)session.getAttribute("id")).getMbrNo();
    	if(session.getAttribute("IpOrd") != null) {
    		resp.sendRedirect(req.getContextPath() + "/member/payment");
    	}else{
    		
	    	@SuppressWarnings("unchecked")
	    	TreeMap<ArrayList<Integer>, Integer> cart = (TreeMap<ArrayList<Integer>, Integer>) session.getAttribute("cart");
	    	
	    	ArrayList<ItemDto> ordItemList = new ArrayList<ItemDto>();
	    	ArrayList<BranchDto> ordBrList = new ArrayList<BranchDto>();
	    	ArrayList<Integer> ordCntList = new ArrayList<Integer>();
	    	ArrayList<Integer> ordItemPerBr = new ArrayList<Integer>();
	    	
	    	StockDao stkDao = StockDao.getInstance();
	    	
	    	if(cart != null) {
	    		
	    		req.setAttribute("mbrNo", mbrNo);
	    		cart.forEach((brNoNitemNo, ordCnt) -> {
	    			
	    			int brNo = brNoNitemNo.get(0);
    				int itemNo = brNoNitemNo.get(1);
    				int stkCnt = stkDao.select(itemNo, brNo).getStkCnt();
    				
    				if(stkCnt < ordCnt) {
    					ordCnt = stkCnt;
    					cart.replace(brNoNitemNo, ordCnt);
    				}
    				
	    			if(ordCnt > 0) {
	    				BranchDto brDto = brDao.select(brNo);
	    				ItemDto itemDto = itemDao.select(itemNo);
	    				
	    				ordBrList.add(brDto);
	    				ordItemList.add(itemDto);
	    				ordCntList.add(ordCnt);
	    			}
	    			req.setAttribute("ordBrList", ordBrList);
	    			req.setAttribute("ordItemList", ordItemList);
	    			req.setAttribute("ordCntList", ordCntList);
	    		});
	    		
	    		for(int i = 0; i < ordBrList.size(); i++) {
	    			if((ordItemPerBr.size() == 0) || !(ordBrList.get(i-1).equals(ordBrList.get(i)))) {
	    				ordItemPerBr.add(1);
	    			}else {
	    				ordItemPerBr.set(ordItemPerBr.size()-1, ordItemPerBr.get(ordItemPerBr.size()-1)+1);
	    			}
	    			req.setAttribute("ordItemPerBr", ordItemPerBr);
	    		}

	    		int totAmt = 0;
	    		int discAmt = 0;
				int delFee = 2500;
	    		for(int i = 0; i < ordItemList.size(); i++) {
	    			totAmt += ordItemList.get(i).getPrice() * ordCntList.get(i);
	    		}
	    		
	    		req.setAttribute("totAmt", totAmt);
	    		req.setAttribute("discAmt", discAmt);
	    		req.setAttribute("delFee", delFee);
	    		
	    	}
			
	        req.setAttribute("mainPage", "/views/member/memberCart.jsp");
			req.setAttribute("mainPageTitle", "Betamall - 장바구니");
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
			
    	}
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	int mbrNo = Integer.parseInt(req.getParameter("mbrNo"));
    	String[] sbrNos = req.getParameterValues("brNo");
    	for(int i = 0; i< sbrNos.length; i++) {
    		int brNo = Integer.parseInt(sbrNos[i]);
    		
    		/* -------- 지점별 주문 내역 삽입 ------- */
        	
        	OrderDao ordDao = OrderDao.getInstance();
        	int ordNo = ordDao.getOrdNo();
            OrderDto ordDto = new OrderDto(ordNo, mbrNo, brNo, null, "결제대기", null, null, null);
            int n1 = ordDao.insert(ordDto);
            
            if(n1 < 0) {
            	System.out.println("에러 발생");
            }
            
        	String[] itemNos = req.getParameterValues("itemNofbr"+brNo);
        	String[] cntNos = req.getParameterValues("cntfbr"+brNo);
        	
        	for(int j = 0; j<itemNos.length; j++) {
        		
        		int itemNo = Integer.parseInt(itemNos[j]);
        		int ordCnt = Integer.parseInt(cntNos[j]);
        		
        		OrdItemDao ordItemDao = OrdItemDao.getInstance();
            	OrdItemDto ordItemDto = new OrdItemDto(ordNo, itemNo, ordCnt, null, 0, null);
            	
            	int n2 = ordItemDao.insert(ordItemDto);
            	int n3 = StockDao.getInstance().changeStock(ordNo, -1);

            	if(n2 < 0 || n3 < 0) {
                	System.out.println("에러 발생");
                }
        	}
    	}
    	//req.getSession().removeAttribute("cart");
    	req.getSession().setAttribute("IpOrd", "true");
    	resp.sendRedirect(req.getContextPath() + "/member/payment");
    }
}
