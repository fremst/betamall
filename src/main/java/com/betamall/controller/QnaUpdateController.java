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

import com.betamall.dao.ItemDao;
import com.betamall.dao.QnaDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.QnaDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("serial")
@WebServlet("/board/qnaupdate")
public class QnaUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNo=Integer.parseInt(req.getParameter("qnaNo"));
		QnaDao dao=QnaDao.getInstance();
		QnaDto dto = dao.select(qnaNo);
		ItemDao idao = ItemDao.getInstance();
		ArrayList<ItemDto> idto = idao.selectAll();
		
		req.setAttribute("dto", dto);
		req.setAttribute("idto", idto);
		req.setAttribute("mainPageTitle", "Betamall - 게시글 수정");
		req.setAttribute("mainPage", "/views/board/qnaModForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/board");

		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5,
			"utf-8",
			new DefaultFileRenamePolicy()
		);
		
		int qnaNo = Integer.parseInt(mr.getParameter("qnaNo"));
		int itemNo = (mr.getParameter("itemNo") == null) ? null : Integer.parseInt(mr.getParameter("itemNo"));
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
			saveFileName= qnaCat + "+" + qnaTitle + "."  + fileExt;
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}
		
		QnaDto dto = new QnaDto(qnaNo, 0, itemNo, qnaCat, qnaTitle, qnaCon, saveFileName, secret, null, null, false);
		QnaDao dao = QnaDao.getInstance();
		int n=dao.update(dto);
		
		if(n>0) {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPageTitle", "Betamall - 게시글 상세 보기");
			req.setAttribute("mainPage", "/views/board/qnaDetail.jsp?qnaNo="+qnaNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}else {
			req.setAttribute("dto", dto);
			req.setAttribute("mainPageTitle", "Betamall - 게시글 상세 보기");
			req.setAttribute("mainPage", "/views/board/qnaDetail.jsp?qnaNo="+qnaNo);
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}
}
