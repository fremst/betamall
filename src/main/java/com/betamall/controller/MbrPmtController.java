package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ItemDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.OrdItemDao;
import com.betamall.dao.OrderDao;
import com.betamall.dao.PmtDao;
import com.betamall.dto.ItemDto;
import com.betamall.dto.MemberDto;
import com.betamall.dto.OrdItemDto;
import com.betamall.dto.OrderDto;
import com.betamall.dto.OrderSheet;
import com.betamall.dto.PmtDto;

@WebServlet("/member/payment")
@SuppressWarnings("serial")
public class MbrPmtController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		MemberDao mbrDao = MemberDao.getInstance();
		OrderDao ordDao = OrderDao.getInstance();
		OrdItemDao ordItemDao = OrdItemDao.getInstance();
		ItemDao itemDao = ItemDao.getInstance();
		
		String mbrId = (String)session.getAttribute("id");
		int mbrNo = mbrDao.selectById(mbrId).getMbrNo();

		ArrayList<Integer> ordNos = ordDao.getIpOrdNos(mbrNo);
		ArrayList<OrderSheet> orderSheets = new ArrayList<OrderSheet>();
		
		for(int ordNo:ordNos) {
			String brName = BranchDao.getInstance().select(ordDao.select(ordNo).getBrNo()).getBrName();
			ArrayList<OrdItemDto> ordItemDtos = ordItemDao.selectByOrdNo(ordNo);
			for(OrdItemDto o:ordItemDtos) {

				ItemDto itemDto = itemDao.select(o.getItemNo());
				
				orderSheets.add(new OrderSheet(
						ordNo,
						brName,
						itemDto.getItemNo(),
						itemDto.getItemName(),
						itemDto.getPrice(),
						o.getOrdCnt()
						)
					);
			}
		}

		req.setAttribute("orderSheets", orderSheets);
		
		int totAmt = 0;
		int discAmt = 0;
		int delFee = 2500;
		
		for(int ordNo:ordNos) {
			totAmt += ordItemDao.getTotPmt(ordNo);
		}
		
		MemberDto mbrDto = mbrDao.selectById(mbrId);
		String str = mbrDto.getMbrAdr();
		String[] adresses = str.split("/");
		
		req.setAttribute("recName", mbrDto.getMbrName());
		req.setAttribute("recTel", mbrDto.getMbrTel());
		
		req.setAttribute("recpostno", adresses[0]);
		req.setAttribute("recAdr", adresses[1]);
		req.setAttribute("recAdr1", adresses[2]);
		req.setAttribute("recAdr2", adresses[3]);
		
		req.setAttribute("totAmt", totAmt);
		req.setAttribute("discAmt", discAmt);
		req.setAttribute("delFee", delFee);
		req.setAttribute("ordNos", ordNos);
		
        req.setAttribute("mainPage", "/views/member/payment.jsp");
		req.setAttribute("mainPageTitle", "Betamall - 결제");
		req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OrderDao ordDao = OrderDao.getInstance();
		PmtDao pmtDao = PmtDao.getInstance(); 
		OrdItemDao ordItemDao = OrdItemDao.getInstance();
		MemberDao mbrDao = MemberDao.getInstance();
		
		HttpSession session = req.getSession();
		String mbrId = (String)session.getAttribute("id");
		
		int discAmt = 0;
		int delFee = 0;
		String[] sordNos = req.getParameterValues("ordNos");

		String recName = req.getParameter("recName");
		String recTel = req.getParameter("recTel");
		
    	for(int i = 0; i< sordNos.length; i++) {
    		
    		int ordNo = Integer.parseInt(sordNos[i]);
    		
			String recFullAdr = "(" + req.getParameter("recpostno") + ") " + req.getParameter("recAdr") + req.getParameter("recAdr1") + req.getParameter("recAdr2");
			
			OrderDto ordDto = ordDao.select(ordNo);
			pmtDao.insert(new PmtDto(ordNo, ordItemDao.getTotPmt(ordDto.getOrdNo())-discAmt+delFee, "카드", null));
			ordDto.setOrdDate(ordDao.select(ordNo).getOrdDate());
			ordDao.update(new OrderDto(ordNo, ordDto.getMbrNo(), ordDto.getBrNo(), ordDto.getOrdDate(), "결제완료", recName, recFullAdr, recTel));

			MemberDto mbrDto = mbrDao.selectById(mbrId);
			mbrDto.setTotAmt(mbrDto.getTotAmt()+ordItemDao.getTotPmt(ordDto.getOrdNo())-discAmt+delFee);
			mbrDao.update(mbrDto);
		}
    	
    	session.removeAttribute("cart");
		session.removeAttribute("IpOrd");
		resp.sendRedirect(req.getContextPath() + "/member/ordList");
		
	}
}
