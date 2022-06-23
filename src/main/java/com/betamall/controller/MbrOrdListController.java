package com.betamall.controller;

import com.betamall.dao.MemberDao;
import com.betamall.dao.OrderInfoDao;
import com.betamall.dto.OrderInfoDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
@WebServlet("/member/ordList")
public class MbrOrdListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mbrId = (String) session.getAttribute("id");
        String query = "";

        MemberDao mbrDao = MemberDao.getInstance();
        OrderInfoDao mbrOrderInfoDao = OrderInfoDao.getInstance();


        if (req.getParameter("ordStartDate") != null) {
            query += "AND ORDDATE >= TRUNC(TO_DATE('" + req.getParameter("ordStartDate") + "','YYYY-MM-DD'),'DD') ";
        }
        if (req.getParameter("ordEndDate") != null) {
            query += "AND ORDDATE < TRUNC(TO_DATE('" + req.getParameter("ordEndDate") + "','YYYY-MM-DD'),'DD')+1 ";
        }


        int mbrNo = mbrDao.selectById((String) session.getAttribute("id")).getMbrNo();
        OrderInfoDto orderInfoDto = mbrOrderInfoDao.selectByMbrNo(mbrNo);
        ArrayList<OrderInfoDto> mbrOrderList = mbrOrderInfoDao.selectByMbrNQuery(mbrNo, query);


        req.setAttribute("mbrNo", mbrNo);
        req.setAttribute("query", query);
        req.setAttribute("mbrId", mbrId);
        req.setAttribute("orderInfoDto", orderInfoDto);
        req.setAttribute("mbrOrderList", mbrOrderList);

        if (mbrOrderList.size() == 0) {
            req.setAttribute("errMsg", "주문하신 상품이 없습니다.");
        }
        req.setAttribute("mainPageTitle", "Betamall - 주문/배송조회");
        req.setAttribute("mainPage", "/views/member/mypage/ordList.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }
}
