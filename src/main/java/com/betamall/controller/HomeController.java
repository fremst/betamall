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
		req.setAttribute("mainPage", "/views/home/main.jsp");
		// 임의의 결과페이지->추후 수정
		req.getRequestDispatcher("views/common/layout.jsp").forward(req, resp);
	}
}