package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
@SuppressWarnings("serial")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = getServletContext();
		application.setAttribute("cp", req.getContextPath());
		req.getRequestDispatcher("views/home/main.jsp").forward(req, resp); //로그인뒤 경로 수정해야함 
	}
}