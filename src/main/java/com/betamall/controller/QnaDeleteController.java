package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.QnaDao;

@SuppressWarnings("serial")
@WebServlet("/board/qnadelete")
public class QnaDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNo=Integer.parseInt(req.getParameter("qnaNo"));
		QnaDao dao=QnaDao.getInstance();
		int n = dao.delete(qnaNo);
		if(n>0) {
			req.setAttribute("code","success");
		}else {
			req.setAttribute("code","fail");
		}
		req.setAttribute("mainPageTitle", "Betamall - 게시글 삭제");
		req.setAttribute("mainPage", "/views/board/result.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	

}
