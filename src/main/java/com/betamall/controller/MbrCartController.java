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
import com.betamall.dto.BranchDto;
import com.betamall.dto.ItemDto;

@WebServlet("/member/cart")
@SuppressWarnings("serial")
public class MbrCartController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	HttpSession session = req.getSession();
    	
    	@SuppressWarnings("unchecked")
    	TreeMap<ArrayList<Integer>, Integer> cart = (TreeMap<ArrayList<Integer>, Integer>) session.getAttribute("cart");
    	
    	MemberDao mbrDao = MemberDao.getInstance();
    	ItemDao itemDao = ItemDao.getInstance();
    	BranchDao brDao = BranchDao.getInstance();
    	
    	ArrayList<ItemDto> ordItemList = new ArrayList<ItemDto>();
    	ArrayList<BranchDto> ordBrList = new ArrayList<BranchDto>();
    	ArrayList<Integer> ordCntList = new ArrayList<Integer>();
    	ArrayList<Integer> ordItemPerBr = new ArrayList<Integer>();
    	
    	if(cart == null) {
    		System.out.println("담긴 상품 없음");
    	}else {
    		req.setAttribute("mbrNo", mbrDao.select((String)session.getAttribute("id")).getMbrNo());
    		cart.forEach((k, v) -> {
    			ordBrList.add(brDao.select(k.get(0)));
    			ordItemList.add(itemDao.select(k.get(1)));
    			ordCntList.add(v);
    			req.setAttribute("ordItemList", ordItemList);
    			req.setAttribute("ordBrList", ordBrList);
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	
    }
}
