package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.QnaCmtDao;
import com.betamall.dto.QnaCmtDto;

@SuppressWarnings("serial")
@WebServlet("/board/qnacmtinsert")
public class QnaCmtInsertController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int qnaNo = Integer.parseInt(req.getParameter("qnaNo"));
		String qnaCmtCon = req.getParameter("qnaCmtCon");
	
		QnaCmtDto dto = new QnaCmtDto(0, qnaNo, qnaCmtCon, null, false);
		QnaCmtDao dao = QnaCmtDao.getInstance();
		
		int n = dao.insert(dto);
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}else {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}
			
	}
}
