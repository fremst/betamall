package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dto.BranchDto;
import com.betamall.dto.ManagerDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/mgrinsert")
@SuppressWarnings("serial")
public class MgrInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BranchDao brDao = BranchDao.getInstance();
		ArrayList<BranchDto> brDtos = brDao.selectWoMgr();
		req.setAttribute("brDtos", brDtos);
		req.getRequestDispatcher("/views/admin/manager/mgrInsertForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/manager");
		
		MultipartRequest mr = new MultipartRequest(
			req, // request 객체
			saveDir, // 저장 디렉토리
			1024*1024*5, // 최대 업로드 가능 크기 (바이트 단위)
			"utf-8", // 인코딩 방식
			new DefaultFileRenamePolicy() // 동일한 파일명이 존재할 때 이를 처리할 객체
		);

		String saveFileName = mr.getFilesystemName("uploadedFile"); // 저장된 파일명
		ManagerDao mgrDao = ManagerDao.getInstance();
		BranchDao brDao = BranchDao.getInstance();
		
		int n = mgrDao.insert(
				new ManagerDto(
								0,
								brDao.selectByBrName(mr.getParameter("brName")).getBrNo(),
								mr.getParameter("mgrId"),
								mr.getParameter("mgrPwd"),
								mr.getParameter("mgrName"),
								mr.getParameter("mgrTel"),
								mr.getParameter("mgrEmail"),
								saveFileName
							)
						);
		if(n>0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
}
