package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.betamall.dao.ManagerDao;
import com.betamall.dao.MemberDao;

@WebServlet("/idcheck")
@SuppressWarnings("serial")
public class IdCheckController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int exist = 0;
		String id =	req.getParameter("id");
		if(id == null){
			exist = -1;
		}else if(MemberDao.getInstance().checkId(id) == 1) {
			exist = 1;
		}else if(ManagerDao.getInstance().selectById(id) != null){
			exist = 1;
		}

		JSONObject result = new JSONObject();
		result.put("exist", exist);

		PrintWriter pw = resp.getWriter();
		pw.print(result);
		pw.close();

	}
}