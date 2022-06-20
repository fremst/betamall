package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDetailDao;
import com.betamall.dto.ItemDetailDto;

@WebServlet("/item/search")
@SuppressWarnings("serial")
public class ItemSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemDetailDao itemDetailDao = ItemDetailDao.getInstance();
		
		String field = req.getParameter("field");
		String keyword = req.getParameter("keyword");
		
		ArrayList<ItemDetailDto> itemDetailDtos = new ArrayList<ItemDetailDto>();
		if(field == null || keyword == null || field.equals("") || keyword.equals("")) {
			itemDetailDtos = itemDetailDao.selectAll();
		}else{
			itemDetailDtos = itemDetailDao.selectByKeyword(field, keyword);
		}
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.setAttribute("iDtos", itemDetailDtos);
		req.setAttribute("mainPage", "/views/home/search.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 상품 검색");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
}