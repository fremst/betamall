package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.MemberDao;

@WebServlet("/login/searchPwd")
@SuppressWarnings("serial")
public class SearchPwdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPage", "/views/home/searchPwd.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String mbrId = req.getParameter("id");
		String mbrEmail = req.getParameter("email");
		String mbrTel = req.getParameter("tel");
		MemberDao dao = MemberDao.getInstance();
		String pwd = dao.searchPwd(mbrId, mbrEmail, mbrTel);
		if (pwd != null) {
			req.setAttribute("mainPage", "/views/home/searchPwd.jsp");
			req.setAttribute("pwd", "비밀번호는" + pwd + "입니다.");
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		} else {
			req.setAttribute("mainPage", "/views/home/searchPwd.jsp");
			req.setAttribute("pwd", "다시 입력해 주세요.");
			req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		}
	}
}