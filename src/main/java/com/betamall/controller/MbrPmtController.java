package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.MemberDao;
import com.betamall.dao.OrdItemDao;
import com.betamall.dao.OrderDao;
import com.betamall.dao.PmtDao;
import com.betamall.dto.MemberDto;
import com.betamall.dto.OrderDto;
import com.betamall.dto.PmtDto;

@WebServlet("/member/payment")
@SuppressWarnings("serial")
public class MbrPmtController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		ArrayList<ItemDto> ordItemList = new ArrayList<ItemDto>();
//    	ArrayList<BranchDto> ordBrList = new ArrayList<BranchDto>();
//    	ArrayList<Integer> ordCntList = new ArrayList<Integer>();
//    	ArrayList<Integer> ordItemPerBr = new ArrayList<Integer>();
//		
//		TreeMap<ArrayList<Integer>, Integer> cart = new TreeMap<ArrayList<Integer>, Integer>(new Comparator<ArrayList<Integer>>() {
//			@Override
//			public int compare(ArrayList<Integer> ordKey1, ArrayList<Integer> ordKey2) {
//				
//				int brNo1 = ordKey1.get(0);
//				int itemNo1 = ordKey1.get(1);
//				int brNo2 = ordKey2.get(0);
//				int itemNo2 = ordKey2.get(1);
//				
//				if(brNo1 != brNo2) {
//					return brNo1 - brNo2; 
//				}else {
//					return itemNo1 - itemNo2;
//				}
//			}
//		});
//		
//		ArrayList<Integer> ordKey = new ArrayList<>(Arrays.asList(brNo, itemNo));
//		if(cart.get(ordKey) == null) {
//			cart.put(ordKey, ordCnt);
//		}else {
//			cart.put(ordKey, cart.get(ordKey) + ordCnt);
//		}
//		
//		req.setAttribute("ordBrList", ordBrList);
//		req.setAttribute("ordItemList", ordItemList);
//		req.setAttribute("ordCntList", ordCntList);
//		req.setAttribute("ordItemPerBr", ordItemPerBr);
		
    	
		OrderDao ordDao = OrderDao.getInstance();
		OrdItemDao ordItemDao = OrdItemDao.getInstance();
		
		HttpSession session = req.getSession();
		
		MemberDao mbrDao = MemberDao.getInstance();
		String mbrId = (String)session.getAttribute("id");
		int mbrNo = mbrDao.selectById(mbrId).getMbrNo();
		
		int totAmt = 0;
		int discAmt = 0;
		int delFee = 2500;
		
		ArrayList<Integer> ordNos = ordDao.getIpOrdNos(mbrNo);
		
		for(int ordNo:ordNos) {
			totAmt += ordItemDao.getTotPmt(ordNo)-discAmt+delFee;
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
		
		int discAmt = Integer.parseInt(req.getParameter("discAmt"));
		int delFee = Integer.parseInt(req.getParameter("delFee"));
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

			// 트리거로 처리 
			MemberDto mbrDto = mbrDao.selectById(mbrId);
			mbrDto.setTotAmt(mbrDto.getTotAmt()+ordItemDao.getTotPmt(ordDto.getOrdNo())-discAmt+delFee);
			mbrDao.update(mbrDto);
		}
    	
    	session.removeAttribute("cart");
		session.removeAttribute("IpOrd");
		resp.sendRedirect(req.getContextPath() + "/item/search");
		
	}
}
