package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ItemDao;
import com.betamall.dao.StockDao;
import com.betamall.dto.BranchDto;
import com.betamall.dto.ItemDto;
import com.betamall.dto.StockDto;


@WebServlet(urlPatterns = {"/admin/item/detail","/item/detail"})
@SuppressWarnings("serial")
public class ItemDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		ItemDao dao = ItemDao.getInstance();
		ItemDto dto = dao.select(itemNo);
		req.setAttribute("dto", dto);
		
		StockDao stkDao = StockDao.getInstance();
		ArrayList<StockDto> stkDtos = stkDao.selectByItemNo(itemNo);
		req.setAttribute("stkDtos", stkDtos);
		
		BranchDao brDao = BranchDao.getInstance();
		ArrayList<BranchDto> brDtos = new ArrayList<BranchDto>();
		for (StockDto s:stkDtos) {
			brDtos.add(brDao.select(s.getBrNo()));
		}
		req.setAttribute("brDtos", brDtos);
		
		req.setAttribute("mainPage", "/views/admin/item/itemDetail.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 제품 판매 상세정보");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		
	}
}

