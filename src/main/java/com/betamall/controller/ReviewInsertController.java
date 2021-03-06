package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDao;
import com.betamall.dao.OrdItemDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.OrdItemDto;

@SuppressWarnings("serial")
@WebServlet("/reviewinsert")
public class ReviewInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemNo=Integer.parseInt(req.getParameter("itemNo"));
		ItemDao dao=ItemDao.getInstance();
		ItemDto dto=dao.select(itemNo);
		req.setAttribute("dto", dto);
		
		int ordNo=Integer.parseInt(req.getParameter("ordNo"));
		req.setAttribute("ordNo", ordNo);
		
		req.setAttribute("mainPageTitle", "Betamall - 리뷰 작성");
		req.setAttribute("mainPage", "/views/member/reviewInsertForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int ordNo=Integer.parseInt(req.getParameter("ordNo"));
		int itemNo=Integer.parseInt(req.getParameter("itemNo"));
		int rate=Integer.parseInt(req.getParameter("rate"));
		String review=req.getParameter("review");
	
		OrdItemDto dto = new OrdItemDto(ordNo, itemNo, 0, review, rate, null);
		OrdItemDao dao = OrdItemDao.getInstance();
		
		int n = dao.reviewinsert(dto);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/member/ordList");
		}else {
			resp.sendRedirect(req.getContextPath() + "/member/ordList");
		}	
	}
}
