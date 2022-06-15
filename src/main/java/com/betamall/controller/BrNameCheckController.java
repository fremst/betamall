package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.betamall.dao.BranchDao;

@WebServlet("/brnamecheck")
@SuppressWarnings("serial")
public class BrNameCheckController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int exist = 0;
		String brName = req.getParameter("brName");
		
		if(brName == null){
			exist = -1;
		}else if(BranchDao.getInstance().selectByBrName(brName) != null) {
			exist = 1;
		}
		
		JSONObject result = new JSONObject();
		result.put("exist", exist);

		PrintWriter pw = resp.getWriter();
		pw.print(result);
		pw.close();
		
	}
}