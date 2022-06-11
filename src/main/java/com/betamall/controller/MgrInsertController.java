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

import com.betamall.dao.BranchDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dto.BranchDto;
import com.betamall.dto.ManagerDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/admin/manager/insert")
@SuppressWarnings("serial")
public class MgrInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* ----------------로그인 구현 전 임시------------------- */
		HttpSession session = req.getSession();
		session.setAttribute("id","admin0");
		/* ------------------------------------------------------ */
		
		ManagerDto loginMgrDto = ManagerDao.getInstance().selectById((String)req.getSession().getAttribute("id"));
		if(loginMgrDto == null || loginMgrDto.getMgrNo() != 0) { // 총관리자 (MgrNo == 0)가 아니면 list로 redirect
			resp.sendRedirect(req.getContextPath() + "/admin/manager/list");
			return;
		}
		
		BranchDao brDao = BranchDao.getInstance();
		ArrayList<BranchDto> brDtos = brDao.selectWoMgr();
		req.setAttribute("brDtos", brDtos);
		
		req.setAttribute("mainPage", "/views/admin/manager/mgrInsertForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/manager");
		
		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5,
			"utf-8"
		);
		
		String systemFileName = mr.getFilesystemName("uploadFile");
		String fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
		String saveFileName = mr.getParameter("mgrId")+"."+fileExt;
		new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		
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
			System.out.println(saveDir+"에 저장 성공");
			resp.sendRedirect(req.getContextPath() + "/admin/manager/list");
		}else {
			System.out.println("실패");
		}
	}
}
