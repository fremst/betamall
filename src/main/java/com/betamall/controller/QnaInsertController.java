package com.betamall.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ManagerDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.QnaDao;
import com.betamall.dao.BoardDao;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.MemberDto;
import com.betamall.dto.QnaDto;
import com.betamall.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("serial")
@WebServlet("/board/qnainsert")
public class QnaInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPageTitle", "Betamall - 게시글 작성");
		req.setAttribute("mainPage", "/views/board/qnaInsertForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String Id = (String)session.getAttribute("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.select(Id);
		int mbrNo = mdto.getMbrNo();
		// 접속자가 회원인 경우 예외 처리
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/board");
		
		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5,
			"utf-8",
			new DefaultFileRenamePolicy()
		);
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
		QnaDto dto = new QnaDto(0, mbrNo, 1001, qnaCat, qnaTitle, qnaCon, saveFileName, secret, null, null, false);
		QnaDao dao = QnaDao.getInstance();
		
		int n = dao.insert(dto);
		
		if(n>0) {
			req.setAttribute("code","success");
		}else {
			req.setAttribute("code","fail");
		}
		
		req.setAttribute("mainPageTitle", "Betamall - 게시글 작성 결과");
		req.setAttribute("mainPage", "/views/board/result.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);	
	}
}
