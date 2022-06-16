package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.NoticeDao;
import com.betamall.dto.NoticeDto;

@SuppressWarnings("serial")
@WebServlet("/board/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int brdNo=Integer.parseInt(req.getParameter("brdNo"));
		NoticeDao dao=NoticeDao.getInstance();
		NoticeDto dto = dao.select(brdNo);
		req.setAttribute("dto", dto);
		
		req.setAttribute("mainPageTitle", "Betamall - 게시글 보기");
		req.setAttribute("mainPage", "/views/board/noticeDetail.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
