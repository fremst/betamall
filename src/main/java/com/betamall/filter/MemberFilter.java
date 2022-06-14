package com.betamall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.betamall.dao.MemberDao;

@WebFilter("/member")
public class MemberFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean login = false;
		// 세션에 member id가 존재하면 member로 admin에 id가 존재하면 admin으로 로그인
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session != null && MemberDao.getInstance().checkId(session.getAttribute("id")) == 1) {
			String id = (String) session.getAttribute("id");
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect((request.getContentType() + "/login"));
		}
	}
}
