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
	// doPost로 수정
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		OrderDao ordDao = OrderDao.getInstance();
		
		String mbrId = (String)session.getAttribute("id");
		ArrayList<Integer> ordNos = ordDao.getIpOrdNos(MemberDao.getInstance().selectById(mbrId).getMbrNo());
		
		for(int ordNo:ordNos) {
			ChangeOrder.changeOrdSta(ordNo, "결제취소");
		}
		session.removeAttribute("IpOrd");
		resp.sendRedirect(req.getContextPath() + "/item/search");
	}
}
