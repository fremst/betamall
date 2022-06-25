package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ItemDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.QnaCmtDao;
import com.betamall.dao.QnaDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.MemberDto;
import com.betamall.dto.QnaCmtDto;
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
		
		MemberDao mbdao=MemberDao.getInstance();
		String mbrId=mbdao.select(dto.getMbrNo()).getMbrId();
		req.setAttribute("mbrId", mbrId);
		
		int itemNo=Integer.parseInt(req.getParameter("itemNo"));
		ItemDao iDao=ItemDao.getInstance();
		ItemDto iDto=iDao.select(itemNo);
		req.setAttribute("idto", iDto);
		
		QnaCmtDao qdao=QnaCmtDao.getInstance();
		ArrayList<QnaCmtDto> qdto=qdao.list(qnaNo);
		req.setAttribute("list", qdto);
		
		HttpSession session = req.getSession();
		if(session.getAttribute("role")!=null) {
			if(session.getAttribute("role") == "member") {
				String Id = (String)session.getAttribute("id");
				MemberDao mdao = MemberDao.getInstance();
				MemberDto mdto = mdao.selectById(Id);
				int mbrNo =  mdto.getMbrNo();
				req.setAttribute("mbrNo", mbrNo);
			}
			String role = (String)session.getAttribute("role");
			req.setAttribute("role", role);
		}
		
		req.setAttribute("mainPageTitle", "Betamall - 게시글 보기");
		req.setAttribute("mainPage", "/views/board/qnaDetail.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}
