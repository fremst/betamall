package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDao;

@WebServlet("/admin/item/delete")
@SuppressWarnings("serial")
public class ItemDeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		ItemDao dao=ItemDao.getInstance();
		int n = dao.disableItem(itemNo, true);
		
		String code = "";
		
		if(n>0) {
			code="success";
		}else {
			code="fail";
		}
		
		req.setAttribute("code", code);
		req.setAttribute("mainPage", "/views/admin/item/result.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 제품 판매 중단 요청 결과");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
