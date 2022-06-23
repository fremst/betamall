package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ItemDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dao.McatDao;
import com.betamall.dao.ScatDao;
import com.betamall.dao.StockDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.McatDto;
import com.betamall.dto.ScatDto;
import com.betamall.dto.StockDto;

@WebServlet("/admin/stock/update")
@SuppressWarnings("serial")
public class StockUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		ManagerDao mgrDao = ManagerDao.getInstance();
		StockDao stkDao = StockDao.getInstance();
		ItemDao dao=ItemDao.getInstance();
		ItemDto dto=dao.select(itemNo);
		McatDao mcatDao = McatDao.getInstance();
		ScatDao scatDao = ScatDao.getInstance();
		
		HttpSession session = req.getSession();
	    String mgrId = (String)session.getAttribute("id");
	    ManagerDto mgrDto = mgrDao.selectById(mgrId);
	    int brNo = mgrDto.getBrNo();
	    
	    StockDto stkDto = stkDao.select(itemNo, brNo);
	    
	    if(stkDto == null) {
	    	String nullStk = "true";
	    	req.setAttribute("nullStk", nullStk);
	    }
	    
		ArrayList<McatDto> mcatList = mcatDao.selectAll();
		ArrayList<ScatDto> scatList = scatDao.selectAll();

		req.setAttribute("dto", dto);
		req.setAttribute("stkDto", stkDto);
		req.setAttribute("brNo", brNo);
		req.setAttribute("mcatList", mcatList);
		req.setAttribute("scatList", scatList);

		req.setAttribute("mainPage", "/views/admin/stock/stockModForm.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 제품 판매 상세정보");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StockDao stkDao = StockDao.getInstance();
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		int brNo = Integer.parseInt(req.getParameter("brNo"));
		int deltaStk = Integer.parseInt(req.getParameter("deltaStk"));
		
		StockDto stkDto = stkDao.select(itemNo, brNo);
		
		int n=-1;
		
		if(stkDto != null) {
			int currStkCnt = stkDto.getStkCnt();
			int nextStkCnt = currStkCnt + deltaStk;
			if(nextStkCnt >= 0) {
				stkDto.setStkCnt(nextStkCnt);
				n = stkDao.update(stkDto);
			}
		}else {
			n = stkDao.insert(new StockDto(itemNo, brNo, 0+deltaStk));
		}
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/admin/item/list");
		}else {
			resp.sendRedirect(req.getContextPath() + "/admin/stock/update");
		}
	}
}

