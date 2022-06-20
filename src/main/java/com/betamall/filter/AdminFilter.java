package com.betamall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ManagerDao;
@WebFilter("/admin/*")
public class AdminFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("id") != null && ManagerDao.getInstance().selectById((String) session.getAttribute("id")) != null) {
			req.setAttribute("adminPage", "true");
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect((req.getContextPath() + "/login"));
		}
	}
}