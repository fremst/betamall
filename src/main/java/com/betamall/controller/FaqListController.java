package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.BoardDao;
import com.betamall.dto.BoardDto;

@WebServlet("/board/faqlist")
@SuppressWarnings("serial")
public class FaqListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String spageNum=req.getParameter("pageNum");	
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}		
		int startRow=(pageNum-1)*10+1;		
		int endRow=startRow+9;					
		BoardDao dao=BoardDao.getInstance();
		ArrayList<BoardDto> list=dao.faqList(startRow, endRow, field, keyword);
		int pageCount=(int)Math.ceil(dao.faqgetCount(field,keyword)/10.0);		
		
		int startPage=(pageNum-1)/10*10+1;		
		int endPage=startPage+9;		
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
		HttpSession session = req.getSession();
		String role = (String)session.getAttribute("role");
		req.setAttribute("role", role);
		
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);

		req.setAttribute("mainPageTitle", "Betamall - 게시글 목록");
		req.setAttribute("mainPage", "/views/board/faqList.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
