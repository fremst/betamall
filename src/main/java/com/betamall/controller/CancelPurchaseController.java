package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.MemberDao;
import com.betamall.dao.OrderDao;
import com.betamall.service.ChangeOrder;

@WebServlet("/cancelPurchase")
@SuppressWarnings("serial")
public class CancelPurchaseController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String mbrId = (String)session.getAttribute("id");
		
		OrderDao ordDao = OrderDao.getInstance();
		ArrayList<Integer> ordNos = ordDao.getIpOrdNos(MemberDao.getInstance().selectById(mbrId).getMbrNo());
		for(int ordNo:ordNos) {
			ChangeOrder.changeOrdSta(ordNo, "주문취소");
		}
		
		session.removeAttribute("IpOrd");
		if(req.getHeader("referer").endsWith("payment")) {
			resp.sendRedirect(req.getContextPath() + "/member/cart");
		}else{
			resp.sendRedirect(req.getContextPath() + "/member/ordList");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sordNo = req.getParameter("ordNo");
		
		if(sordNo != null) {
			int ordNo = Integer.parseInt(sordNo);
			ChangeOrder.changeOrdSta(ordNo, "결제취소");
		}
		resp.sendRedirect(req.getContextPath() + "/member/ordList");
	}
}
