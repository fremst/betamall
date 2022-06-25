package com.betamall.controller;

import com.betamall.dao.BranchDao;
import com.betamall.dao.McatDao;
import com.betamall.dao.SalesInfoDao;
import com.betamall.dao.ScatDao;
import com.betamall.dto.SalesInfoDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/admin/sales/list")
public class SalesListController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = "";
        if (req.getParameter("catNameChk") != null) {
            if (req.getParameter("cat").equals("mcat")) {
                query = query + "AND MCATNAME = '" + req.getParameter("mcatName") + "' ";
            } else if (req.getParameter("cat").equals("scat")) {
                query = query + "AND SCATNAME = '" + req.getParameter("scatName") + "' ";
            }
        }

        if (req.getParameter("brNameChk") != null) {
            query = query + "AND BRNAME = '" + req.getParameter("brName") + "' ";
        }

        if (req.getParameter("ordDateChk") != null) {
            if (!req.getParameter("ordStartDate").equals("")) {
                query = query + "AND ORDDATE >= TRUNC(TO_DATE('" + req.getParameter("ordStartDate") + "','YYYY-MM-DD'),'DD') ";
            }

            if (!req.getParameter("ordEndDate").equals("")) {
                query = query + "AND ORDDATE < TRUNC(TO_DATE('" + req.getParameter("ordEndDate") + "','YYYY-MM-DD'),'DD')+1 ";
            }
        }

        if (req.getParameter("pmtDateChk") != null) {
            if (!req.getParameter("pmtStartDate").equals("")) {
                query = query + "AND PMTDATE >= TRUNC(TO_DATE('" + req.getParameter("pmtStartDate") + "','YYYY-MM-DD'),'DD') ";
            }

            if (!req.getParameter("pmtEndDate").equals("")) {
                query = query + "AND ORDDATE < TRUNC(TO_DATE('" + req.getParameter("pmtEndDate") + "','YYYY-MM-DD'),'DD')+1 ";
            }
        }

        if (req.getParameter("itemNameChk") != null) {
            query = query + "AND ITEMNAME LIKE '%" + req.getParameter("itemName") + "%'";
        }

        SalesInfoDao salesInfoDao = SalesInfoDao.getInstance();
        ArrayList<SalesInfoDto> salesInfoList = salesInfoDao.selectByQuery(query);
        new JSONObject();
        req.setAttribute("salesList", salesInfoList);
        req.setAttribute("mcatList", McatDao.getInstance().selectAll());
        req.setAttribute("scatList", ScatDao.getInstance().selectAll());
        req.setAttribute("branchList", BranchDao.getInstance().selectAll());
        req.setAttribute("mainPage", "/views/admin/sales/salesList.jsp");
        req.setAttribute("mainPageTitle", "Betamall - 매출 조회");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = "";
        if (req.getParameter("catNameChk") != null) {
            if (req.getParameter("cat").equals("mcat")) {
                query = query + "AND MCATNAME = '" + req.getParameter("mcatName") + "' ";
            } else if (req.getParameter("cat").equals("scat")) {
                query = query + "AND SCATNAME = '" + req.getParameter("scatName") + "' ";
            }
        }

        if (req.getParameter("brNameChk") != null) {
            query = query + "AND BRNAME = '" + req.getParameter("brName") + "' ";
        }

        if (req.getParameter("ordDateChk") != null) {
            if (!req.getParameter("ordStartDate").equals("")) {
                query = query + "AND ORDDATE >= TRUNC(TO_DATE('" + req.getParameter("ordStartDate") + "','YYYY-MM-DD'),'DD') ";
            }

            if (!req.getParameter("ordEndDate").equals("")) {
                query = query + "AND ORDDATE < TRUNC(TO_DATE('" + req.getParameter("ordEndDate") + "','YYYY-MM-DD'),'DD')+1 ";
            }
        }

        if (req.getParameter("pmtDateChk") != null) {
            if (!req.getParameter("pmtStartDate").equals("")) {
                query = query + "AND PMTDATE >= TRUNC(TO_DATE('" + req.getParameter("pmtStartDate") + "','YYYY-MM-DD'),'DD') ";
            }

            if (!req.getParameter("pmtEndDate").equals("")) {
                query = query + "AND ORDDATE < TRUNC(TO_DATE('" + req.getParameter("pmtEndDate") + "','YYYY-MM-DD'),'DD')+1 ";
            }
        }

        if (req.getParameter("itemNameChk") != null) {
            query = query + "AND ITEMNAME LIKE '%" + req.getParameter("itemName") + "%'";
        }

        SalesInfoDao salesInfoDao = SalesInfoDao.getInstance();
        ArrayList<SalesInfoDto> salesInfoList = salesInfoDao.selectByQuery(query);
        JSONObject data = new JSONObject();
        data.put("salesList", salesInfoList);
        data.put("mcatList", McatDao.getInstance().selectAll());
        data.put("scatList", ScatDao.getInstance().selectAll());
        data.put("branchList", BranchDao.getInstance().selectAll());
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print(data);
        pw.close();
    }
}
