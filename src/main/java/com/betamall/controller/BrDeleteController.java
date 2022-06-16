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
import com.betamall.dto.BranchDto;

@WebServlet("/admin/branch/delete")
@SuppressWarnings("serial")
public class BrDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BranchDao brDao = BranchDao.getInstance();
		
		HttpSession session = req.getSession();
		//임시. 아이디 admin0로 시작하는지 확인 필요 (회원/미가입)
		/* ------------------------------------------------------ */
		session.setAttribute("id","admin0");
		/* ------------------------------------------------------ */
		// String loginId = (String) session.getAttribute("id");
		int selectedBrNo = Integer.parseInt(req.getParameter("brNo"));
		
		BranchDto selectedBrDto = brDao.select(selectedBrNo);
		
		int n = brDao.delete(selectedBrNo);
		if(n>0) {
			// 삭제 성공 시 메시지 추가
			System.out.println(n);
			ServletContext application = req.getServletContext();
			String saveDir = application.getRealPath("/resources/uploads/admin/branch");
			new File(saveDir, selectedBrDto.getBrImg()).delete();
		}else {
			// 삭제 실패 시 에러 및 이유 추가
			System.out.println(n);
		}
		
		resp.sendRedirect(req.getContextPath() + "/admin/branch/list");
	}
}
