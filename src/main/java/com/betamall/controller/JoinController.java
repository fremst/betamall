package com.betamall.controller;

import com.betamall.dao.MemberDao;
import com.betamall.dto.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/join")
@SuppressWarnings("serial")
public class JoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mainPageTitle", "Betamall - 회원가입");
        req.setAttribute("mainPage", "/views/home/join.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String mbrId = req.getParameter("id");
        String mbrPwd = req.getParameter("pwd");
        String mbrName = req.getParameter("name");
        Date mbrBd = Date.valueOf(req.getParameter("bd"));
        String mbrTel = req.getParameter("tel");
        String mbrAdr = req.getParameter("postno") + "/" + req.getParameter("addr") + "/" + req.getParameter("addr1")
                + "/" + req.getParameter("addr2");
        String mbrEmail = req.getParameter("email");
        MemberDto dto = new MemberDto(0, mbrName, mbrTel, mbrAdr, mbrEmail, mbrId, mbrPwd, mbrBd, null, 0);
        MemberDao dao = MemberDao.getInstance();
        int n = dao.insert(dto);
        if (n > 0) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("mainPage", "/views/home/join.jsp");
            req.setAttribute("mainPageTitle", "Betamall - 회원가입");
            req.setAttribute("errMsg", "양식을 다시 작성해 주세요");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
        }
    }
}