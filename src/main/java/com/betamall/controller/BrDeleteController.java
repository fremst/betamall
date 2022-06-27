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
import com.betamall.dto.BranchDto;

@WebServlet("/admin/branch/delete")
@SuppressWarnings("serial")
public class BrDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String res = req.getParameter("res");
		if(res != null) {
			req.setAttribute("res", res);
		}
		
		BranchDao brDao = BranchDao.getInstance();
		int selectedBrNo = Integer.parseInt(req.getParameter("brNo"));
		
		BranchDto selectedBrDto = brDao.select(selectedBrNo);
		
		int n = brDao.delete(selectedBrNo);
		
		if(n>0) {
			ServletContext application = req.getServletContext();
			String saveDir = application.getRealPath("/resources/uploads/admin/branch");
			new File(saveDir, selectedBrDto.getBrImg()).delete();
			resp.sendRedirect(req.getContextPath() + "/admin/branch/list?res=success");
		}else {
			resp.sendRedirect(req.getContextPath() + "/admin/branch/delete?brNo="+selectedBrNo+"&res=fail");
		}
		
	}
}
