package com.betamall.controller;

import java.io.File;
import java.io.IOException;

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

@WebServlet("/admin/branch/update")
@SuppressWarnings("serial")
public class BrUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String res = req.getParameter("res");
		if(res != null) {
			req.setAttribute("res", res);
		}
		
		ManagerDto loginMgrDto = ManagerDao.getInstance().selectById((String)req.getSession().getAttribute("id"));
		if(loginMgrDto == null || loginMgrDto.getMgrNo() != 0) {
			resp.sendRedirect(req.getContextPath() + "/admin/branch/list");
			return;
		}
		
		int selectedBrNo = Integer.parseInt(req.getParameter("brNo"));

		req.setAttribute("brDto", BranchDao.getInstance().select(selectedBrNo));
		req.setAttribute("mainPage", "/views/admin/branch/brModForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BranchDao brDao = BranchDao.getInstance();
		int selectedBrNo = Integer.parseInt(req.getParameter("brNo"));
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/branch");
		
		MultipartRequest mr = new MultipartRequest(
				req,
				saveDir,
				1024*1024*5,
				"utf-8"
		);
		
		BranchDto selectedbrDto = brDao.select(selectedBrNo);
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
			resp.sendRedirect(req.getContextPath() + "/admin/branch/update?brNo="+selectedBrNo+"&res=success");
		}else{
			resp.sendRedirect(req.getContextPath() + "/admin/branch/update?brNo="+selectedBrNo+"&res=fail");
		}
	}
}
