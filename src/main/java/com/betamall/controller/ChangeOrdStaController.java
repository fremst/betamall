package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.betamall.service.ChangeOrder;

@WebServlet("/ChangeOrdSta")
@SuppressWarnings("serial")
public class ChangeOrdStaController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sordNo = req.getParameter("ordNo");
		if(sordNo != null) {
			int ordNo = Integer.parseInt(sordNo);
		  	String ordSta = URLDecoder.decode(req.getParameter("ordSta"), "UTF-8");
		  	int n = ChangeOrder.changeOrdSta(ordNo, ordSta);
	  	
	  	if(n>0) {
	    	JSONObject data = new JSONObject();
	        data.put("ordSta", ordSta);
	        resp.setContentType("text/plain;charset=utf-8");
	        PrintWriter pw = resp.getWriter();
	        pw.print(data);
	        pw.close();
	  		//resp.sendRedirect(req.getContextPath() + "/admin/order/list");
	  	}
		}else {
			//resp.sendRedirect(req.getContextPath() + "/admin/order/list");
		}
	}
	
}
