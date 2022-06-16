package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join/termsofuse")
@SuppressWarnings("serial")
public class TermsOfUseController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("mainPageTitle", "Betamall - 회원 가입 이용 동의");
		req.setAttribute("mainPage", "/views/home/termsOfUse.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		
	}
}