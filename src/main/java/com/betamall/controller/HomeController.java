package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/home"})
@SuppressWarnings("serial")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// main에 보여질 페이지를 requestScope에 담기
		req.setAttribute("mainPage", "/views/common/main.jsp");
		// layout.jsp (header + main + footer) 호출
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}