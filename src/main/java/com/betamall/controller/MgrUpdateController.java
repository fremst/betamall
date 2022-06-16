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
import com.betamall.dao.ManagerInfoDao;
import com.betamall.dto.ManagerDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/admin/manager/update")
@SuppressWarnings("serial")
public class MgrUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String res = req.getParameter("res");
		if(res != null) {
			req.setAttribute("res", res);
		}
		
		ManagerDao mgrDao = ManagerDao.getInstance();
		HttpSession session = req.getSession();
		
		String loginId = (String) session.getAttribute("id");
		int selectedMgrNo = Integer.parseInt(req.getParameter("mgrNo"));
		
		ManagerDto loginMgrDto = mgrDao.selectById(loginId);
		
		if(loginMgrDto == null) {
			req.setAttribute("role", "member");
		}
		
		ManagerDto selectedMgrDto = mgrDao.select(selectedMgrNo);
		req.setAttribute("mgrInfoDto", ManagerInfoDao.getInstance().select(selectedMgrNo));
		if (loginMgrDto.getMgrNo() == selectedMgrDto.getMgrNo()) { // 본인
			req.setAttribute("relation", "self");
		}
		if (loginMgrDto.getMgrNo() == 0) { // 총관리자
			req.setAttribute("role", "master");
		}
		
		req.setAttribute("mainPageTitle", "Betamall - 점장 정보 수정");
		req.setAttribute("mainPage", "/views/admin/manager/mgrModForm.jsp?");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerDao mgrDao = ManagerDao.getInstance();
		int selectedMgrNo = Integer.parseInt(req.getParameter("mgrNo"));
		
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("id");
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/manager");
		
		MultipartRequest mr = new MultipartRequest(
				req,
				saveDir,
				1024*1024*5,
				"utf-8"
		);
		
		ManagerDto loginMgrDto = mgrDao.selectById(loginId);
		String systemFileName = mr.getFilesystemName("uploadFile");
		String saveFileName = loginMgrDto.getMgrImg();
		
		if(systemFileName!=null) { // 새로운 이미지 파일이 업로드 되면
			String fileExt = systemFileName.substring(systemFileName.lastIndexOf(".")+1);
			saveFileName = mr.getParameter("mgrId")+"."+fileExt;
			new File(saveDir, saveFileName).delete();
			new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		}else {
			saveFileName = loginMgrDto.getMgrImg();
		}
			
		int n = mgrDao.update(
				new ManagerDto(
								loginMgrDto.getMgrNo(),
								loginMgrDto.getBrNo(),
								loginMgrDto.getMgrId(),
								mr.getParameter("mgrPwd"),
								mr.getParameter("mgrName"),
								mr.getParameter("mgrTel"),
								mr.getParameter("mgrEmail"),
								saveFileName
							)
						);
		if(n>0) {
//			resp.sendRedirect(req.getContextPath() + "/admin/manager/list?&res=success");
			resp.sendRedirect(req.getContextPath() + "/admin/manager/update?mgrNo="+selectedMgrNo+"&res=success");
		}else{
			resp.sendRedirect(req.getContextPath() + "/admin/manager/update?mgrNo="+selectedMgrNo+"&res=fail");
		}
	}
}
