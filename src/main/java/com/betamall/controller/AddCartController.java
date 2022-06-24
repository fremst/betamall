package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/member/addcart")
@SuppressWarnings("serial")
public class AddCartController extends HttpServlet{
	
	@Override
	@SuppressWarnings("unchecked")
	// doPost로 수정
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int brNo = Integer.parseInt(req.getParameter("brNo"));
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		int ordCnt = Integer.parseInt(req.getParameter("ordCnt"));
		
		HttpSession session = req.getSession();
		
		TreeMap<ArrayList<Integer>, Integer> cart = null; // (지점 번호, 아이템 번호), 주문 수량
		TreeMap<ArrayList<Integer>, Integer> sessionCart = (TreeMap<ArrayList<Integer>, Integer>) session.getAttribute("cart");
		
		if(sessionCart == null) {
			cart = new TreeMap<ArrayList<Integer>, Integer>(new Comparator<ArrayList<Integer>>() {
				@Override
				public int compare(ArrayList<Integer> ordKey1, ArrayList<Integer> ordKey2) {
					
					int brNo1 = ordKey1.get(0);
					int itemNo1 = ordKey1.get(1);
					int brNo2 = ordKey2.get(0);
					int itemNo2 = ordKey2.get(1);
					
					if(brNo1 != brNo2) {
						return brNo1 - brNo2; 
					}else {
						return itemNo1 - itemNo2;
					}
				}
			});
		}else {
			cart = sessionCart;
		}
		
		ArrayList<Integer> ordKey = new ArrayList<>(Arrays.asList(brNo, itemNo));
		if(cart.get(ordKey) == null) {
			cart.put(ordKey, ordCnt);
		}else {
			if(ordCnt == 0) {
				cart.put(ordKey, 0);
			}
			cart.put(ordKey, cart.get(ordKey) + ordCnt);
		}
		
		session.setAttribute("cart", cart);
		
		String status = req.getParameter("status");
		
		if(status == null || status.equals("pur")) {
			resp.sendRedirect(req.getContextPath() + "/member/cart");
			
		}else if (status.equals("search")){
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			
			pw.print(new JSONObject().put("msg", "장바구니에 성공적으로 담겼습니다."));
			pw.close();

		}else {
			resp.sendRedirect(req.getContextPath() + "/member/cart");
		}
	}
}