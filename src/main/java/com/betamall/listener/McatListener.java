package com.betamall.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.betamall.dao.McatDao;
import com.betamall.dto.McatDto;

@WebListener
public class McatListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ArrayList<String> mcatNames = new ArrayList<String>(); 
		
		for(McatDto mcatDto : McatDao.getInstance().selectAll()) {
			mcatNames.add(mcatDto.getMcatName());
		}
		
		ServletContext application = sce.getServletContext();
		application.setAttribute("mcatNames", mcatNames);
	}
}