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
import com.betamall.dao.BoardDao;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class NoticeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int brdNo=Integer.parseInt(req.getParameter("brdNo"));
		BoardDao dao=BoardDao.getInstance();
		BoardDto dto = dao.select(brdNo);
		req.setAttribute("dto", dto);
		req.setAttribute("mainPageTitle", "Betamall - 게시글 수정");
		req.setAttribute("mainPage", "/views/board/noticeModForm.jsp");
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
		
		int brdNo=Integer.parseInt(mr.getParameter("brdNo"));
		String brdCat=mr.getParameter("brdCat");
		String brdTitle=mr.getParameter("brdTitle");
		String brdCon=mr.getParameter("brdCon");
		Date brdSdate = (brdCat.equals("이벤트")) ? Date.valueOf(mr.getParameter("brdSdate")) : null;
		Date brdFdate = (brdCat.equals("이벤트")) ? Date.valueOf(mr.getParameter("brdFdate")) : null;
		Boolean popUp = (mr.getParameter("popUp")==null) ? false : true;
		
		String systemFileName=null;
		String fileExt=null;
		String saveFileName = null;
		if(mr.getFilesystemName("uploadFile")!=null) {
			systemFileName=mr.getFilesystemName("uploadFile");
			fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
			saveFileName= brdCat + "+" + brdTitle + "+"  + fileExt;
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}

		BoardDto dto=new BoardDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, saveFileName, null, null, brdSdate, brdFdate, popUp);
		BoardDao dao=BoardDao.getInstance();
		int n=dao.update(dto);
		if(n>0) {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPageTitle", "Betamall - 게시글 상세 보기");
			req.setAttribute("mainPage", "/views/board/noticeDetail.jsp?brdNo="+brdNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}else {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPageTitle", "Betamall - 게시글 상세 보기");
			req.setAttribute("mainPage", "/views/board/noticeDetail.jsp?brdNo="+brdNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}
}
