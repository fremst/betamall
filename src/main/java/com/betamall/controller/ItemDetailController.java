package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDao;
import com.betamall.dto.ItemDto;


@WebServlet("/admin/item/detail")
@SuppressWarnings("serial")
public class ItemDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// itemNo를 QueryString으로 받는다.
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		// DB에서 dao를 통해 itemNo에 해당하는 정보를 가져와서 itemDto에 담는다.
		ItemDao dao=ItemDao.getInstance();
		ItemDto dto=dao.select(itemNo);
		// 4가지 저장소 중 request에 item 정보가 담겨있는 itemDto를 담는다.
		req.setAttribute("dto", dto);
		// view(itemDetail.jsp)로 forwarding한다. redirect도 가능? X 
		req.setAttribute("mainPage", "/views/admin/item/itemDetail.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 제품 판매 상세정보");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		
	}
}
