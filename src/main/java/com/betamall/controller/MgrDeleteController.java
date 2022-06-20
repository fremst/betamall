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
import com.betamall.dto.ManagerDto;

@WebServlet("/admin/manager/delete")
@SuppressWarnings("serial")
public class MgrDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String res = req.getParameter("res");
		if(res != null) {
			req.setAttribute("res", res);
		}
		
		ManagerDao mgrDao = ManagerDao.getInstance();
		
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("id");
		int selectedMgrNo = Integer.parseInt(req.getParameter("mgrNo"));
		
		ManagerDto loginMgrDto = mgrDao.selectById(loginId);
		ManagerDto selectedMgrDto = mgrDao.select(selectedMgrNo);
		
		if (loginMgrDto.getMgrNo() == 0) {
			
			int n = mgrDao.delete(selectedMgrNo);
			if(n>0) {
				ServletContext application = req.getServletContext();
				String saveDir = application.getRealPath("/resources/uploads/admin/manager");
				new File(saveDir, selectedMgrDto.getMgrImg()).delete();
				resp.sendRedirect(req.getContextPath() + "/admin/manager/list?res=success");
			}else {
				resp.sendRedirect(req.getContextPath() + "/admin/manager/update?brNo="+selectedMgrNo+"&res=fail");
			}
		}else {
			req.setAttribute("status", "detail");
		}
	}
}
