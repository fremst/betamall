package com.betamall.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.NoticeDao;
import com.betamall.dto.NoticeDto;

@WebServlet("/board/update")
public class NoticeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int brdNo=Integer.parseInt(req.getParameter("brdNo"));
		NoticeDao dao=NoticeDao.getInstance();
		NoticeDto dto = dao.select(brdNo);
		req.setAttribute("dto", dto);
		req.setAttribute("mainPage", "/views/board/noticeModForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");		
		int brdNo=Integer.parseInt(req.getParameter("brdNo"));
		String brdCat=req.getParameter("brdCat");
		String brdTitle=req.getParameter("brdTitle");
		String brdCon=req.getParameter("brdCon");
		String brdImg=req.getParameter("brdImg");
		Date brdSdate = (brdCat.equals("이벤트")) ? Date.valueOf(req.getParameter("brdSdate")) : null;
		Date brdFdate = (brdCat.equals("이벤트")) ? Date.valueOf(req.getParameter("brdFdate")) : null;
		Boolean popUp = (req.getParameter("popUp")==null) ? false : true;

		NoticeDto dto=new NoticeDto(brdNo, 1, brdCat, brdTitle, brdCon, brdImg, null, null, brdSdate, brdFdate, popUp);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.update(dto);
		if(n>0) {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPage", "/views/board/noticeDetail.jsp?brdNo="+brdNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}else {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPage", "/views/board/noticeDetail.jsp?brdNo="+brdNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}
}
