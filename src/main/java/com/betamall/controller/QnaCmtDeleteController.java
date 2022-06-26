package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.QnaCmtDao;

@SuppressWarnings("serial")
@WebServlet("/board/qnacmtdelete")
public class QnaCmtDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaCmtNo=Integer.parseInt(req.getParameter("qnaCmtNo"));
		QnaCmtDao dao=QnaCmtDao.getInstance();
		int n = dao.delete(qnaCmtNo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}else {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}
	}
}
