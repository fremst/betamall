package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addcart")
@SuppressWarnings("serial")
public class AddCartController extends HttpServlet{
	
	@Override
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// http://localhost:8080/betamall/addcart?itemNo=1001&ordCnt=3&brNo=1. doPost로 수정
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		int brNo = Integer.parseInt(req.getParameter("brNo"));
		int ordCnt = Integer.parseInt(req.getParameter("ordCnt"));
		
		HttpSession session = req.getSession();
		
		HashMap<ArrayList<Integer>, Integer> cart = null; // (아이템 번호, 지점 번호), 주문 수량
		HashMap<ArrayList<Integer>, Integer> sessionCart = (HashMap<ArrayList<Integer>, Integer>) session.getAttribute("cart");
		
		if(sessionCart == null) {
			cart = new HashMap<>();
		}else {
			cart = sessionCart;
		}
		
		ArrayList<Integer> ordKey = new ArrayList<>(Arrays.asList(itemNo, brNo));
		
		if(cart.get(ordKey) == null) {
			cart.put(ordKey, ordCnt);
		}else {
			cart.put(ordKey, cart.get(ordKey) + ordCnt);
		}
		System.out.println(cart.get(ordKey));
		session.setAttribute("cart", cart);
		
	}
}
