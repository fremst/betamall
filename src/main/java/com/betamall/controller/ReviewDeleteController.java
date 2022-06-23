package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.OrdItemDao;

@SuppressWarnings("serial")
@WebServlet("/board/reviewdelete")
public class ReviewDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int ordNo=Integer.parseInt(req.getParameter("ordNo"));
	
		OrdItemDao dao = OrdItemDao.getInstance();
		
		int n = dao.reviewdelete(ordNo);
		
		// 페이지 연결 후 아래부분 수정
		if(n>0) {
			req.setAttribute("code","success");
		}else {
			req.setAttribute("code","fail");
		}
		
		req.setAttribute("mainPageTitle", "Betamall - 리뷰 작성 결과");
		req.setAttribute("mainPage", "/views/board/result.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);	
	}
}
