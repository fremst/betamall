package com.betamall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.BranchDao;
import com.betamall.dao.McatDao;
import com.betamall.dao.SalesInfoDao;
import com.betamall.dao.ScatDao;
import com.betamall.dto.SalesInfoDto;

@WebServlet("/admin/sales/list")
@SuppressWarnings("serial")
public class SalesListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String query = "";
        
        if(req.getParameter("catNameChk")!=null) {
        	if(req.getParameter("cat").equals("mcat")) {
        		query += "AND MCATNAME = '" + req.getParameter("mcatName") + "' ";
        	}else if(req.getParameter("cat").equals("scat")) {
        		query += "AND SCATNAME = '" + req.getParameter("scatName") + "' ";
        	}
        }
        if(req.getParameter("brNameChk")!=null) {
    		query += "AND BRNAME = '" + req.getParameter("brName") + "' ";
        }
        
        if(req.getParameter("ordDateChk")!=null) {
        	if(!req.getParameter("ordStartDate").equals("")) {
        		query += "AND ORDDATE >= TO_DATE('" + req.getParameter("ordStartDate") + "','YYYY-MM-DD') ";
        	}
        	if(!req.getParameter("ordEndDate").equals("")) {
        		query += "AND ORDDATE <= TO_DATE('" + req.getParameter("ordEndDate") + "','YYYY-MM-DD') ";
        	}
        }
        if(req.getParameter("pmtDateChk")!=null) {
        	if(!req.getParameter("pmtStartDate").equals("")) {
        		query += "AND PMTDATE >= TO_DATE('" + req.getParameter("pmtStartDate") + "','YYYY-MM-DD') ";
        	}
        	if(!req.getParameter("pmtEndDate").equals("")) {
        		query += "AND PMTDATE <= TO_DATE('" + req.getParameter("pmtEndDate") + "','YYYY-MM-DD') ";
        	}
        }
        if(req.getParameter("itemNameChk")!=null) {
    		query += "AND ITEMNAME LIKE '%" + req.getParameter("itemName") + "%'";
        }
        
        System.out.println(query);
    	
    	SalesInfoDao salesInfoDao = SalesInfoDao.getInstance();
        ArrayList<SalesInfoDto> salesInfoList = salesInfoDao.selectByQuery(query);
        
        req.setAttribute("salesList", salesInfoList);
        req.setAttribute("mcatList", McatDao.getInstance().selectAll());
        req.setAttribute("scatList", ScatDao.getInstance().selectAll());
        req.setAttribute("branchList", BranchDao.getInstance().selectAll());

        req.setAttribute("mainPage", "/views/admin/sales/salesList.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }
}
