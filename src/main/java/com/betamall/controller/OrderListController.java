package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dao.OrderInfoDao;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.OrderInfoDto;
import com.betamall.service.ChangeOrder;

@WebServlet("/admin/order/list")
@SuppressWarnings("serial")
public class OrderListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String query = "";
        
        if(req.getParameter("ordStaChk")!=null) {
    		query += "AND ORDSTA = '" + req.getParameter("ordSta") + "' ";
        }
        
    	OrderInfoDao orderInfoDao = OrderInfoDao.getInstance();
    	ManagerDto mgrDto = ManagerDao.getInstance().selectById((String)req.getSession().getAttribute("id"));
    	int brNo = mgrDto.getBrNo();
        ArrayList<OrderInfoDto> orderInfoList = orderInfoDao.selectByBrNQuery(brNo, query);
        req.setAttribute("brName", BranchDao.getInstance().select(brNo).getBrName());
        req.setAttribute("orderList", orderInfoList);
        
        req.setAttribute("mainPage", "/views/admin/order/orderList.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String sordNo = req.getParameter("ordNo");
    	if(sordNo != null) {
    		int ordNo = Integer.parseInt(sordNo);
        	String ordSta = req.getParameter("ordSta");
        	int n = ChangeOrder.changeOrdSta(ordNo, ordSta);
        	
        	if(n>0) {
        		resp.sendRedirect(req.getContextPath() + "/admin/order/list");
        	}
    	}else {
    		resp.sendRedirect(req.getContextPath() + "/admin/order/list");
    	}
    }
	
}
