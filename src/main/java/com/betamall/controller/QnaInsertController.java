package com.betamall.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ItemDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.QnaDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.MemberDto;
import com.betamall.dto.QnaDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("serial")
@WebServlet("/board/qnainsert")
public class QnaInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemDao idao = ItemDao.getInstance();
		ArrayList<ItemDto> idto = idao.selectAll();
		req.setAttribute("idto", idto);
		
		HttpSession session = req.getSession();
		String role=(String)session.getAttribute("role");
		if(role.equals("admin0") || role.equals("admin")) {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}else {
			req.setAttribute("mainPageTitle", "Betamall - 게시글 작성");
			req.setAttribute("mainPage", "/views/board/qnaInsertForm.jsp");
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String Id = (String)session.getAttribute("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.selectById(Id);
		int mbrNo = mdto.getMbrNo();
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/board");
		
		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5,
			"utf-8",
			new DefaultFileRenamePolicy()
		);
		
		Integer itemNo = (mr.getParameter("itemNo") == null) ? null : Integer.parseInt(mr.getParameter("itemNo"));
		String qnaCat = mr.getParameter("qnaCat");
		String qnaTitle = mr.getParameter("qnaTitle");
		String qnaCon = mr.getParameter("qnaCon");
		Boolean secret = (mr.getParameter("secret") == null) ? false : true;
		
		String systemFileName=null;
		String fileExt=null;
		String saveFileName = null;
		if(mr.getFilesystemName("uploadFile")!=null) {
			systemFileName=mr.getFilesystemName("uploadFile");
			fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
			saveFileName= qnaCat + "+" + qnaTitle + fileExt;
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}
		QnaDto dto = new QnaDto(0, mbrNo, itemNo, qnaCat, qnaTitle, qnaCon, saveFileName, secret, null, null, false);
		QnaDao dao = QnaDao.getInstance();

		int n = dao.insert(dto);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}else {
			resp.sendRedirect(req.getContextPath() + "/board/qnalist");
		}	
	}
}
