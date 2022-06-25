package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.OrdItemDao;

@SuppressWarnings("serial")
@WebServlet("/reviewdelete")
public class ReviewDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ordNo=Integer.parseInt(req.getParameter("ordNo"));
		int itemNo=Integer.parseInt(req.getParameter("itemNo"));
		OrdItemDao dao = OrdItemDao.getInstance();
		int n = dao.reviewdelete(ordNo, itemNo);
		
		
		HttpSession session = req.getSession();
		String role=(String)session.getAttribute("role");
		if(n>0) {
			if(role.equals("admin0") || role.equals("admin")) {
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				resp.sendRedirect(req.getContextPath() + "/member/ordList");
			}
		}else {
			if(role.equals("admin0") || role.equals("admin")) {
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				resp.sendRedirect(req.getContextPath() + "/member/ordList");
			}
		}	
	}
}
