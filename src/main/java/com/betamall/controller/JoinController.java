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
public class JoinController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/home/join.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String mbrId = req.getParameter("id");
        String mbrPwd = req.getParameter("pwd");
        String pwdCheck = req.getParameter("pwdCheck");
        String mbrName = req.getParameter("name");
        Date mbrBd = Date.valueOf(req.getParameter("bd"));
        String mbrTel = req.getParameter("tel");
        String mbrAdr = req.getParameter("addr");
        String mbrEmail = req.getParameter("email");
        MemberDto dto = new MemberDto(0, mbrName, mbrTel, mbrAdr, mbrEmail, mbrId, mbrPwd, mbrBd, null, null, 0);
        MemberDao dao = MemberDao.getInstance();
        int n = dao.insert(dto);
        if (n > 0) {
            req.setAttribute("result", "success");
        } else {
            req.setAttribute("result", "fail");
        }
        // 결과값 확인을 위해 임의로 설정.
        req.getRequestDispatcher("views/home/result.jsp").forward(req, resp);
    }

}
