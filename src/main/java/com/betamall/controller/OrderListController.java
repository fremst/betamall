package com.betamall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.betamall.dao.BranchDao;
import com.betamall.dao.ManagerDao;
import com.betamall.dao.OrderInfoDao;
import com.betamall.dto.ManagerDto;
import com.betamall.dto.OrderInfoDto;

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
        req.setAttribute("mainPageTitle", "Betamall - 주문 관리");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String query = "";
        
        if(req.getParameter("ordStaChk")!=null && req.getParameter("ordStaChk").equals("true")) {
    		query += "AND ORDSTA = '" + URLDecoder.decode(req.getParameter("ordSta"), "UTF-8") + "' ";
        }
        
    	OrderInfoDao orderInfoDao = OrderInfoDao.getInstance();
    	ManagerDto mgrDto = ManagerDao.getInstance().selectById((String)req.getSession().getAttribute("id"));
    	int brNo = mgrDto.getBrNo();
    	
        ArrayList<OrderInfoDto> orderInfoList = orderInfoDao.selectByBrNQuery(brNo, query);
    	JSONObject data = new JSONObject();
        data.put("brName", BranchDao.getInstance().select(brNo).getBrName());
        data.put("orderList", orderInfoList);
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print(data);
        pw.close();
        
    }
}
