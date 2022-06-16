package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.betamall.dao.ManagerInfoDao;
import com.betamall.dto.ManagerInfoDto;

@WebServlet(urlPatterns = {"/admin/manager/list", "/admin/manager/"})
@SuppressWarnings("serial")
public class MgrListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPageTitle", "Betamall - 점장 목록");
		req.setAttribute("mainPage", "/views/admin/manager/mgrList.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int currPageNum = 1;
		if(req.getParameter("pageNum")!=null) {
			currPageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		
		int rowCountPerPage = 3;
		int pageCountPerPage = 3;
		
		int startRow = (currPageNum - 1)*rowCountPerPage + 1;
		int endRow = startRow + rowCountPerPage - 1;
		
		ManagerInfoDao mgrInfoDao = ManagerInfoDao.getInstance();
		
		int mgrCount = mgrInfoDao.getCount();
		ArrayList<ManagerInfoDto> mgrInfoDtos = mgrInfoDao.selectFromTo(startRow, endRow);
		
		int pageCount = (mgrCount-1)/rowCountPerPage + 1;
		int startPageNum = (((currPageNum-1)/pageCountPerPage)*pageCountPerPage) + 1;
		int endPageNum = Math.min(startPageNum + pageCountPerPage - 1, pageCount);

		JSONArray data = new JSONArray();
		
		JSONObject pageData = new JSONObject();
		pageData.put("pageNum", currPageNum);
		pageData.put("startPageNum", startPageNum);
		pageData.put("endPageNum", endPageNum);
		pageData.put("pageCountPerPage", pageCountPerPage);
		pageData.put("pageCount", pageCount);
		
		JSONArray mgrsData = new JSONArray();
		for(ManagerInfoDto m:mgrInfoDtos) {
			JSONObject mgrData = new JSONObject();
			mgrData.put("mgrNo",m.getMgrNo());
			mgrData.put("mgrImg",m.getMgrImg());
			mgrData.put("mgrName",m.getMgrName());
			mgrData.put("brTel",m.getBrTel());
			mgrData.put("brAddr",m.getBrAddr());
			mgrData.put("brName",m.getBrName());
			mgrData.put("mgrTel",m.getMgrTel());
			mgrData.put("mgrEmail",m.getMgrEmail());
			mgrsData.put(mgrData);
		}
		data.put(pageData);
		data.put(mgrsData);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(data);
		pw.close();
	}
}
