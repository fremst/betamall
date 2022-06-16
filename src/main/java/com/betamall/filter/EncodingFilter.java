package com.betamall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = {"/*"},
		initParams = {
			@WebInitParam(name="encoding", value="utf-8")
		})
public class EncodingFilter implements Filter{
	String encoding = null;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(encoding == null) {
			request.setCharacterEncoding("utf-8");
		}else {
			request.setCharacterEncoding(encoding);
		}
		
		filterChain.doFilter(request, response);
	}
}
