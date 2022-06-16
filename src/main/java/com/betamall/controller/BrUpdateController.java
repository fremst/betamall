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

import com.betamall.dao.BranchDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dto.BranchDto;
import com.betamall.dto.ManagerDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/admin/branch/update")
@SuppressWarnings("serial")
public class BrUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ManagerDao mgrDao = ManagerDao.getInstance();
		HttpSession session = req.getSession();
		
		/* ----------------로그인 구현 전 임시------------------- */
		session.setAttribute("id","admin0");
		/* ------------------------------------------------------ */
		
		String loginId = (String) session.getAttribute("id");
		int selectedbrNo = Integer.parseInt(req.getParameter("brNo"));
		
		ManagerDto loginMgrDto = mgrDao.selectById(loginId);
		
		// filter에서 처리하는 걸로 수정
		if (loginMgrDto.getMgrNo() == 0) { // 총관리자
			req.setAttribute("role", "master");
		}
		
		req.setAttribute("brDto", BranchDao.getInstance().select(selectedbrNo));
		
		req.setAttribute("mainPage", "/views/admin/branch/brModForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BranchDao brDao = BranchDao.getInstance();
		int selectedbrNo = Integer.parseInt(req.getParameter("brNo"));
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/branch");
		
		MultipartRequest mr = new MultipartRequest(
				req,
				saveDir,
				1024*1024*5,
				"utf-8"
		);
		
		BranchDto selectedbrDto = brDao.select(selectedbrNo);
		String systemFileName = mr.getFilesystemName("uploadFile");
		String saveFileName = selectedbrDto.getBrImg();
		
		if(systemFileName!=null) { // 새로운 이미지 파일이 업로드 되면
			String fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
			saveFileName = mr.getParameter("brName")+"."+fileExt;
			new File(saveDir, saveFileName).delete();
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}else {
			saveFileName = selectedbrDto.getBrImg();
		}
		
		int n = brDao.update(
				new BranchDto(
								Integer.parseInt(mr.getParameter("brNo")),
								mr.getParameter("brName"),
								mr.getParameter("brAddr"),
								mr.getParameter("brTel"),
								selectedbrDto.getBrDate(),
								saveFileName
							)
						);
		if(n>0) {
			System.out.println(saveDir+"에 저장 성공");
			resp.sendRedirect(req.getContextPath() + "/admin/branch/list");
		}else {
			System.out.println("실패");
			resp.sendRedirect(req.getContextPath() + "/admin/branch/list");
		}
	}
}
