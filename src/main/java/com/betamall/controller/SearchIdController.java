package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.MemberDao;

@WebServlet("/login/searchId")
@SuppressWarnings("serial")
public class SearchIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPageTitle", "Betamall - 아이디찾기");
		req.setAttribute("mainPage", "/views/home/searchId.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String mbrEmail = req.getParameter("email");
		String mbrTel = req.getParameter("tel");
		MemberDao dao = MemberDao.getInstance();
		String id = dao.searchId(mbrEmail, mbrTel);
		if (id != null) {
			req.setAttribute("mainPage", "/views/home/searchId.jsp");
			req.setAttribute("id", "아이디는" + id + "입니다.");
		} else {
			req.setAttribute("mainPage", "/views/home/searchId.jsp");
			req.setAttribute("id", "다시 입력해 주세요.");
		}
		req.setAttribute("mainPageTitle", "Betamall - 아이디찾기");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}