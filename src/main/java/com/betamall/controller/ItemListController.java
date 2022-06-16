package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDao;
import com.betamall.dto.ItemDto;

@WebServlet("/admin/item/list")
@SuppressWarnings("serial")
public class ItemListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String ipageNum = req.getParameter("pageNum");
		
		int pageNum=1;
		if(ipageNum!=null) {
			pageNum=Integer.parseInt(ipageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ItemDao dao= ItemDao.getInstance();
		ArrayList<ItemDto> list=dao.selectAll(startRow, endRow);
		int count=dao.getCount();
		int pageCount=(int)Math.ceil(count/10.0);//전체 페이지 갯수
		int startPageNum=((pageNum-1)/10*10) + 1;//시작페이지 번호
		int endPageNum=startPageNum+9;//끝페이지 번호
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("mainPage","/views/admin/item/itemList.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 상품 목록");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);		
	}
}
