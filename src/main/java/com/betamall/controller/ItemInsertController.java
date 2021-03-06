package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.ItemDao;
import com.betamall.dao.McatDao;
import com.betamall.dao.ScatDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.McatDto;
import com.betamall.dto.ScatDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/item/insert")
@SuppressWarnings("serial")
public class ItemInsertController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		McatDao mcatDao = McatDao.getInstance();
		ArrayList<McatDto> mcatDtos = mcatDao.selectNDel();
		ScatDao scatDao=ScatDao.getInstance();
		ArrayList<ScatDto> scatDtos = scatDao.selectNDel();
		
		req.setAttribute("mDtos", mcatDtos);
		req.setAttribute("sDtos", scatDtos);
		req.setAttribute("mainPage", "/views/admin/item/itemInsertForm.jsp");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = req.getServletContext();
		String saveDir = application.getRealPath("/resources/uploads/admin/item");
		
		MultipartRequest mr = new MultipartRequest(
			req,
			saveDir,
			1024*1024*5, // 업로드 가능 최대 용량 5MB
			"utf-8",
			new DefaultFileRenamePolicy()
		);
		
		
//		@SuppressWarnings("unchecked")
//		Enumeration<String> em= mr.getFileNames();
//		while(em.hasMoreElements()){
//			String file=em.nextElement(); //전송된 파일의 파라미터이름 얻어오기
//			String orgfilename=mr.getOriginalFileName(file);
//			String savefilename=mr.getFilesystemName(file);
	
//		}
//		String orgFileName = mr.getOriginalFileName("tImg");
		String saveFileName = mr.getFilesystemName("tImg");
		String saveFileName2 = mr.getFilesystemName("detImg");
		
		ScatDao scatDao = ScatDao.getInstance();
		ScatDto scatDto = scatDao.selectByName(mr.getParameter("cats"));
		
		int mcatNo = scatDto.getMcatNo();
		int scatNo = scatDto.getScatNo();
		String itemName = mr.getParameter("itemName");
		String tImg = saveFileName;
		String detImg = saveFileName2;
		String hash = mr.getParameter("hash");
		int price = Integer.parseInt(mr.getParameter("price"));
		boolean itemDel = false;
		
		ItemDao itemDao = ItemDao.getInstance();
		
		itemDao.insert(new ItemDto(0, mcatNo, scatNo, itemName, tImg, detImg, hash, price, itemDel));
		req.setAttribute("mainPageTitle", "Betamall - 상품 목록");
		resp.sendRedirect(req.getContextPath() + "/admin/item/list");
		
	}
}
