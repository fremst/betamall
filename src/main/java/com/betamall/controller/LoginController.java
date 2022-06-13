package com.betamall.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.MemberDao;

import java.io.IOException;
import java.util.HashMap;


@WebServlet("/login")
public class LoginController extends HttpServlet {     @Override 
                                                                                                            // 
                                                                                                            // 
                                                                                                            // 
                                                                                                            // 
proteted  void doGet(HttpServletRequest req
getRequestDispatcher("/views/home/login.jsp").forverridee         String pwd=req.getParameter("pwd");
        //db에 해당 회원이 존재하는지 검사
        MemberDao dao=MemberDao.getInstance();
        if(dao.isMember(id,pwd)!=null) { //아이디와 비밀번호가 맞는 경우 - 로그인하기(세션에 아이디저장)
            HttpSession session=req.getSession();//세션객체 얻어오기
            session.setAttribute("id",id);
            resp.sendRedirect(req.getContextPath() +"/home");
        }else { //아이디 또는 비밀번호가 틀린 경우 - 로그인페이지로 이동하기
            req.setAttribute("errMsg","아이디 또는 비밀번호가 맞지 않아요");    
    		req.setAttribute("mainPage", "/views/home/login.jsp");
    		req.getRequestDispatcher("views/common/layout.jsp").forward(req, resp);
        }
    }
}