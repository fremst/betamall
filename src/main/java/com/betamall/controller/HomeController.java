package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
@SuppressWarnings("serial")
public class HomeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("mainPage", "/views/common/main.jsp");
		req.setAttribute("mainPageTitle", "Betamall에 오신 것을 환영합니다!");
		req.getRequestDispatcher("views/common/layout.jsp").forward(req, resp);
		
	}
}