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

@WebServlet(urlPatterns = {"/admin/item/list", "/admin"})
@SuppressWarnings("serial")
public class ItemListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String ipageNum = req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		
		int pageNum=1;
		if(ipageNum!=null) {
			pageNum=Integer.parseInt(ipageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
	
		ItemDao itemDao= ItemDao.getInstance();
		McatDao mcatDao = McatDao.getInstance();
		ScatDao scatDao = ScatDao.getInstance();
		ManagerDao mgrDao = ManagerDao.getInstance();
		StockDao stockDao = StockDao.getInstance();
//		ArrayList<ItemDto> list = itemDao.selectAll(startRow, endRow);
		ArrayList<ItemDto> list = itemDao.selectByKeyword(startRow, endRow, field, keyword);
		
	    HttpSession session = req.getSession();
	    String mgrId = (String)session.getAttribute("id");
	    ManagerDto mgrDto = mgrDao.selectById(mgrId);
	    int brNo = mgrDto.getBrNo();
	    
//	   1. brNo를 얻어 오기
//	   - 로그인 정보(id)를 얻어 오기
//	     - session 객체를 얻어오기 HttpSession session = req.getSession();
//	     - session 객체에서 id를 꺼내기 String mgrId = session.getAttribute("id");
//	   - MGR Table에서 id에 해당하는 brNo를 가져온다.
//	     - dao 객체 생성 (ManagerDao mgrDao = ManagerDao.getInstance();)
//	     - dao로부터 데이터 얻어와 Dto에 저장 (ManagerDto mgrDto = mgrDao.selectById(mgrId));
//	     - mgrDto로부터 brNo를 추출해서 brNo에 저장 (int brNo = mgrDto.getBrNo();)
//
//	  2. brNo에 해당되는 재고 목록을 ArrayList에 담기
	    ArrayList<StockDto> stockList = stockDao.selectByBrNo(brNo);
		
		ArrayList<McatDto> mcatList = mcatDao.selectAll();
		ArrayList<ScatDto> scatList = scatDao.selectAll();
		int count=itemDao.getCount(field, keyword);
		int pageCount=(int)Math.ceil(count/10.0);//전체 페이지 갯수
		int startPageNum=((pageNum-1)/10*10) + 1;//시작페이지 번호
		int endPageNum=startPageNum+9;//끝페이지 번호
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("mcatList", mcatList);
		req.setAttribute("scatList", scatList);
		req.setAttribute("stockList", stockList);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("mainPage","/views/admin/item/itemList.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 상품 목록");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);		
		
		
		
		
	}
	
}

