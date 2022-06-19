package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.MemberDao;

@WebServlet("/member/delete")
@SuppressWarnings("serial")
public class MbrDeleteController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String mbrId = (String) session.getAttribute("id");
		MemberDao mbrDao = MemberDao.getInstance();
		int n = mbrDao.delete(mbrId);
		if (n > 0) {
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			req.setAttribute("errMsg", "작업에 실패했습니다. 다시 실행해 주세요.");
			req.setAttribute("mainPageTitle", "Betamall - 마이 페이지");
			req.setAttribute("mypageMain", "/member/mypage/mypageMain.jsp");
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}

}
