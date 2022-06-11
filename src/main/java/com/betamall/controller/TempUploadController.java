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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload")
@SuppressWarnings("serial")
public class TempUploadController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext application = req.getServletContext();
		
		String path = req.getParameter("path");
		System.out.println(path);
		
		if(path==null) {path = "";}
		else if(path.equals("manager")) { path = "/admin/manager";}
		
		String saveDir = application.getRealPath("/resources/uploads"+path);
		System.out.println("업로드 경로" + saveDir);
		
		MultipartRequest mr = new MultipartRequest(
			req, // request 객체
			saveDir, // 저장 디렉토리
			1024*1024*5, // 최대 업로드 가능 크기 (바이트 단위)
			"utf-8", // 인코딩 방식
			new DefaultFileRenamePolicy() // 동일한 파일명이 존재할 때 이를 처리할 객체
		);

		String saveFileName = mr.getFilesystemName("uploadedFile"); // 저장된 파일명
//		System.out.println(saveFileName);
		
//		HttpSession session = req.getSession();
		System.out.println(mr.getParameter("mgrName"));
		
//		session.setAttribute("mgrName", mr.getParameter("mgrName"));
//		session.setAttribute("mgrTel", mr.getParameter("mgrTel"));
//		session.setAttribute("mgrEmail", mr.getParameter("mgrEmail"));
//		session.setAttribute("mgrPwd", mr.getParameter("mgrPwd"));
		resp.sendRedirect(req.getContextPath()+"/mgrinsert");
		
//		req.setAttribute("mgrName", mr.getParameter("mgrName"));
//		req.setAttribute("mgrTel", mr.getParameter("mgrTel"));
//		req.setAttribute("mgrEmail", mr.getParameter("mgrEmail"));
//		req.setAttribute("mgrPwd", mr.getParameter("mgrPwd"));
//		req.getRequestDispatcher(req.getContextPath()+"/mgrinsert").forward(req, resp);
		
	}
}
