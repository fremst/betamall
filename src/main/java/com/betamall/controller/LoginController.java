package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.ManagerDao;
import com.betamall.dao.MemberDao;
import com.betamall.dao.OrderDao;

@WebServlet("/login")
@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mainPageTitle", "Betamall - 로그인");
        req.setAttribute("mainPage", "/views/home/login.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        // db에 해당 회원이 존재하는지 검사
        MemberDao memberdao = MemberDao.getInstance();
        ManagerDao mgrdao = ManagerDao.getInstance();

        if (memberdao.isMember(id, pwd) != null) { // 아이디와 비밀번호가 맞는 경우 - 로그인하기(세션에 아이디저장)
            HttpSession session = req.getSession();// 세션객체 얻어오기
            session.setAttribute("id", id);
            session.setAttribute("role", "member");
            
            int mbrNo = MemberDao.getInstance().selectById(id).getMbrNo();
            session.setAttribute("mbrNo", mbrNo);
            
            OrderDao ordDao = OrderDao.getInstance();
            if((ordDao.getIpOrdNos(mbrNo) != null) && (ordDao.getIpOrdNos(mbrNo).size() != 0)) {
            	session.setAttribute("IpOrd", "true");
            }
            
            resp.sendRedirect(req.getContextPath() + "/home");
        } else if (mgrdao.isManager(id, pwd) != null) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            if (mgrdao.selectById(id).getMgrNo() == 0) {
                session.setAttribute("role", "admin0");
            } else {
                session.setAttribute("role", "admin");
            }
            resp.sendRedirect(req.getContextPath() + "/home");
        } else { // 아이디 또는 비밀번호가 틀린 경우 - 로그인페이지로 이동하기
            req.setAttribute("mainPage", "/views/home/login.jsp");
            req.setAttribute("errMsg", "아이디 또는 비밀번호가 맞지 않아요");
            req.setAttribute("mainPageTitle", "Betamall - 로그인");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
        }
    }
}