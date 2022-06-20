package com.betamall.controller;

import com.betamall.dao.MemberDao;
import com.betamall.dto.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/admin/mbrlist")
@SuppressWarnings("serial")
public class MbrListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String spageNum = req.getParameter("pageNum");
        String field = req.getParameter("field");
        String keyword = req.getParameter("keyword");

        int pageNum = 1;
        if (spageNum != null) {
            pageNum = Integer.parseInt(spageNum);
        }
        int startRow = (pageNum - 1) * 10 + 1;
        int endRow = startRow + 9;

        MemberDao mbrDao = MemberDao.getInstance();
        ArrayList<MemberDto> mbrList = mbrDao.selectFromTo(startRow, endRow, field, keyword);

        int pageCount = (int) Math.ceil(mbrDao.getCount(field, keyword) / 10.0);
        int startPage = (pageNum - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        if (endPage > pageCount) {
            endPage = pageCount;
        }
        req.setAttribute("mbrList", mbrList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("field", field);
        req.setAttribute("keyword", keyword);
        req.setAttribute("mainPageTitle", "Betamall - 회원 조회");
        req.setAttribute("mainPage", "/views/admin/mbrlist/mbrList.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }
}
