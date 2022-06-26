package com.betamall.controller;

import com.betamall.dao.MemberDao;
import com.betamall.dao.OrderDao;
import com.betamall.dao.PmtDao;
import com.betamall.dto.MemberDto;
import com.betamall.dto.OrderDto;
import com.betamall.dto.PmtDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/confirmPurchase")
public class ConfirmPurchaseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ordNo = Integer.parseInt(req.getParameter("ordNo"));
        OrderDao ordDao = OrderDao.getInstance();
        OrderDto ordDto = ordDao.select(ordNo);
        String ordSta = req.getParameter("ordSta");
        
        MemberDao mbrDao = MemberDao.getInstance();
        MemberDto mbrDto = mbrDao.selectById((String)req.getSession().getAttribute("id"));
        
        PmtDao pmtDao = PmtDao.getInstance();
        PmtDto pmtDto = pmtDao.select(ordNo);

        ordDto.setOrdSta(ordSta);
        int n1 = ordDao.update(ordDto);
        
        mbrDto.setTotAmt(mbrDto.getTotAmt()+pmtDto.getPmtAmt());
        int n2 = mbrDao.update(mbrDto);

        if (n1*n2 > 0) {
            resp.sendRedirect(req.getContextPath() + "/member/ordList");
        } else {
            req.setAttribute("errMsg", "다시실행해주세요.");
            req.setAttribute("mainPage", "/views/member/mypage/ordList.jsp");
            req.setAttribute("mainPageTitle", "Betamall - 주문/배송 조회");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
        }
    }
}