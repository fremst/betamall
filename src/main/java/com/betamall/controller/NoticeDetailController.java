package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.BoardDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dto.BoardDto;
import com.betamall.dto.ManagerDto;

@SuppressWarnings("serial")
@WebServlet("/board/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int brdNo=Integer.parseInt(req.getParameter("brdNo"));
		BoardDao dao=BoardDao.getInstance();
		BoardDto dto = dao.select(brdNo);
		req.setAttribute("dto", dto);
		
		HttpSession session = req.getSession();
		String mgrId = (String)session.getAttribute("id");
		ManagerDao mdao = ManagerDao.getInstance();
		ManagerDto mdto = mdao.selectById(mgrId);
		int mgrNo = mdto.getMgrNo();
		req.setAttribute("mgrNo", mgrNo);
		
		req.setAttribute("mainPageTitle", "Betamall - 게시글 보기");
		req.setAttribute("mainPage", "/views/board/noticeDetail.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
