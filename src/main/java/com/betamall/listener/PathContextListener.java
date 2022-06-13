package com.betamall.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PathContextListener implements ServletContextListener{
	
	// 컨텍스트가 생성될 때 호출되는 메소드
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		// 서블릿 컨텍스트 얻어오기
		ServletContext application = sce.getServletContext();
		application.setAttribute("cp", application.getContextPath());
	}
}