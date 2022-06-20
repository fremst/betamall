package com.betamall.controller;

import com.betamall.dao.ManagerDao;
import com.betamall.dao.MemberDao;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/emailcheck")
public class EmailCheckController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int exist = 0;
        String email =	req.getParameter("email");
        if(email == null){
            exist = -1;
        }else if(MemberDao.getInstance().checkEmail(email) == 1) {
            exist = 1;
        }else if(ManagerDao.getInstance().selectById(email) != null){
            exist = 1;
        }

        JSONObject result = new JSONObject();
        result.put("exist", exist);

        PrintWriter pw = resp.getWriter();
        pw.print(result);
        pw.close();
    }
}