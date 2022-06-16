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

import com.betamall.dao.BranchDao;
import com.betamall.dto.BranchDto;

@WebServlet(urlPatterns = {"/admin/branch/list", "/admin/branch/"})
@SuppressWarnings("serial")
public class BrListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mainPage", "/views/admin/branch/brList.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 지점 목록");
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
		
		BranchDao brDao = BranchDao.getInstance();
		
		int brCount = brDao.getCount();
		ArrayList<BranchDto> brDtos = brDao.selectFromTo(startRow, endRow);
		
		int pageCount = (brCount-1)/rowCountPerPage + 1;
		int startPageNum = (((currPageNum-1)/pageCountPerPage)*pageCountPerPage) + 1;
		int endPageNum = Math.min(startPageNum + pageCountPerPage - 1, pageCount);

		JSONArray data = new JSONArray();
		
		JSONObject pageData = new JSONObject();
		pageData.put("pageNum", currPageNum);
		pageData.put("startPageNum", startPageNum);
		pageData.put("endPageNum", endPageNum);
		pageData.put("pageCountPerPage", pageCountPerPage);
		pageData.put("pageCount", pageCount);
		
		JSONArray brsData = new JSONArray();
		for(BranchDto b:brDtos) {
			JSONObject brData = new JSONObject();
			brData.put("brNo",b.getBrNo());
			brData.put("brName",b.getBrName());
			brData.put("brAddr",b.getBrAddr());
			brData.put("brTel",b.getBrTel());
			brData.put("brDate",b.getBrDate());
			brData.put("brImg",b.getBrImg());
			brsData.put(brData);
		}
		
		data.put(pageData);
		data.put(brsData);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(data);
		pw.close();
	}
}
