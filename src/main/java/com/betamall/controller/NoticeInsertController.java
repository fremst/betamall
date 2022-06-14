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
@WebServlet("/board/insert")
public class NoticeInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("mainPage", "/views/board/noticeForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String brdCat=req.getParameter("brdCat");
		String brdTitle=req.getParameter("brdTitle");
		String brdContent=req.getParameter("brdContent");
		String brdImg=req.getParameter("brdImg");
		Boolean popUp=Boolean.parseBoolean(req.getParameter("popUp"));  
		
//		if(brdCat.equals("이벤트")) {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
//			LocalDate brdSdate = LocalDate.parse(req.getParameter("brdSdate"), formatter);
//			LocalDate brdFdate = LocalDate.parse(req.getParameter("brdFdate"), formatter);
//			
//		}
		
		NoticeDto dto=new NoticeDto(0, 1, brdCat, brdTitle, brdContent, brdImg, null, null, null, null, popUp);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insert(dto);
		if(n>0) {
			req.setAttribute("code","success");
		}else {
			req.setAttribute("code","fail");
		}
		req.setAttribute("mainPage", "/views/board/result.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
