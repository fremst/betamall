package com.betamall.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betamall.dao.MemberDao;
import com.betamall.dto.MemberDto;

@WebServlet("/join")
@SuppressWarnings("serial")
public class JoinController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mainPageTitle", "Betamall - 회원가입");
        req.setAttribute("mainPage", "/views/home/join.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String mbrId = req.getParameter("id");
        String mbrPwd = req.getParameter("pwd");
        String mbrName = req.getParameter("name");
        Date mbrBd = Date.valueOf(req.getParameter("bd"));
        String mbrTel = req.getParameter("tel");
        String mbrAdr = "(" + req.getParameter("postno") + ")" + req.getParameter("addr") + "/"
                + req.getParameter("addr1") + "/" + req.getParameter("addr2");
        String mbrEmail = req.getParameter("email");
        MemberDto dto = new MemberDto(0, mbrName, mbrTel, mbrAdr, mbrEmail, mbrId, mbrPwd, mbrBd, null, null, 0);
        MemberDao dao = MemberDao.getInstance();
        int n = dao.insert(dto);
        if (n > 0) {
<<<<<<< Updated upstream
        	req.setAttribute("mainPage", "/views/common/main.jsp");
            req.setAttribute("mainPageTitle", "Betamall에 오신 것을 환영합니다!");
        } else {
            req.setAttribute("errMsg", "가입에 실패하였습니다. 다시 작성해주세요");
            req.getRequestDispatcher("/views/home/join.jsp").forward(req, resp);
=======
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("mainPage", "/views/home/join.jsp");
            req.setAttribute("mainPageTitle", "Betamall - 회원가입");
            req.setAttribute("errMsg", "양식을 다시 작성해 주세요");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
>>>>>>> Stashed changes
        }
        req.getRequestDispatcher("views/common/layout.jsp").forward(req, resp);
    }

}