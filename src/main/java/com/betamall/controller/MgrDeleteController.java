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

		ManagerDao mgrDao = ManagerDao.getInstance();
		
		HttpSession session = req.getSession();
		//임시. 아이디 admin0로 시작하는지 확인 필요 (회원/미가입)
		/* ------------------------------------------------------ */
		session.setAttribute("id","admin0");
		/* ------------------------------------------------------ */
		String loginId = (String) session.getAttribute("id");
		int selectedMgrNo = Integer.parseInt(req.getParameter("mgrNo"));
		
		ManagerDto loginMgrDto = mgrDao.selectById(loginId);
		ManagerDto selectedMgrDto = mgrDao.select(selectedMgrNo);
		
		if (loginMgrDto.getMgrNo() == 0) {
			
			int n = mgrDao.delete(selectedMgrNo);
			if(n>0) {
				// 삭제 성공 시 메시지 추가
				System.out.println(n);
				ServletContext application = req.getServletContext();
				String saveDir = application.getRealPath("/resources/uploads/admin/manager");
				new File(saveDir, selectedMgrDto.getMgrImg()).delete();
			}else {
				// 삭제 실패 시 에러 및 이유 추가
				System.out.println(n);
			}
		}else {
			req.setAttribute("status", "detail");
		}
		resp.sendRedirect(req.getContextPath() + "/admin/manager/list");
	}
}
