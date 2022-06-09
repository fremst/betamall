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
	
	// 요청시마다 실행
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(encoding == null) {
			request.setCharacterEncoding("utf-8");
		}else {
			request.setCharacterEncoding(encoding);
		}
		
		// 다음에 수행할 필터를 호출하고 필터가 없으면 사용자가 요청한 페이지로 이동
		filterChain.doFilter(request, response);
	}
}
