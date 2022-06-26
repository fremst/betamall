package com.betamall.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ManagerDao;
import com.betamall.dao.BoardDao;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("serial")
@WebServlet("/board/faqinsert")
public class FaqInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPageTitle", "Betamall - 게시글 작성");
		req.setAttribute("mainPage", "/views/board/faqInsertForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String mgrId = (String)session.getAttribute("id");
		ManagerDao mdao = ManagerDao.getInstance();
		ManagerDto mdto = mdao.selectById(mgrId);
		int mgrNo = mdto.getMgrNo();
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/board");
		
		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5,
			"utf-8",
			new DefaultFileRenamePolicy()
		);
		String brdCat = mr.getParameter("brdCat");
		String brdTitle = mr.getParameter("brdTitle");
		String brdCon = mr.getParameter("brdCon");
		
		String systemFileName=null;
		String fileExt=null;
		String saveFileName = null;
		if(mr.getFilesystemName("uploadFile")!=null) {
			systemFileName=mr.getFilesystemName("uploadFile");
			fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
			saveFileName= brdCat + "+" + brdTitle + fileExt;
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}
		BoardDto dto = new BoardDto(0, mgrNo, brdCat, brdTitle, brdCon, saveFileName, null, null, null, null, false);
		BoardDao dao = BoardDao.getInstance();
		
		int n = dao.insert(dto);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/board/faqlist");
		}else {
			resp.sendRedirect(req.getContextPath() + "/board/faqlist");;
		}
	}
}
