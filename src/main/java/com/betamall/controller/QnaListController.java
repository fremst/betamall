package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.QnaDao;
import com.betamall.dto.QnaDto;

@WebServlet("/board/qnalist")
@SuppressWarnings("serial")
public class QnaListController extends HttpServlet{
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
		QnaDao dao=QnaDao.getInstance();
		ArrayList<QnaDto> list=dao.list(startRow, endRow, field, keyword);
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/10.0);		
		
		int startPage=(pageNum-1)/10*10+1;		
		int endPage=startPage+9;		
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		
		HttpSession session = req.getSession();
		String role = (String)session.getAttribute("role");
		String id = (String)session.getAttribute("id");
		req.setAttribute("role", role);
		req.setAttribute("id", id);

		req.setAttribute("mainPageTitle", "Betamall - 게시글 목록");
		req.setAttribute("mainPage", "/views/board/qnaList.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
