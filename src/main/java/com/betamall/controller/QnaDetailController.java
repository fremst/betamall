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
import com.betamall.dao.MemberDao;
import com.betamall.dao.QnaDao;
import com.betamall.dto.BoardDto;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.MemberDto;
import com.betamall.dto.QnaDto;

@SuppressWarnings("serial")
@WebServlet("/board/qnadetail")
public class QnaDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNo=Integer.parseInt(req.getParameter("qnaNo"));
		QnaDao dao=QnaDao.getInstance();
		QnaDto dto = dao.select(qnaNo);
		req.setAttribute("dto", dto);
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id")!=null) {
			String Id = (String)session.getAttribute("id");
			ManagerDao mdao = ManagerDao.getInstance();
			ManagerDto mdto = mdao.selectById(Id);
			MemberDao memberdao = MemberDao.getInstance();
			MemberDto memberdto = memberdao.select(Id);
			int mgrNo = mdto.getMgrNo();
			int mbrNo =  memberdto.getMbrNo();
			req.setAttribute("mgrNo", mgrNo);
			req.setAttribute("mbrNo", mbrNo);
		}
		
		req.setAttribute("mainPageTitle", "Betamall - 게시글 보기");
		req.setAttribute("mainPage", "/views/board/qnaDetail.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
