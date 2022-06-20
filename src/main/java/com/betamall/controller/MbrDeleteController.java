package com.betamall.controller;

import com.betamall.dao.MemberDao;
import com.betamall.dto.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/member/delete")
@SuppressWarnings("serial")
public class MbrDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberDao dao = MemberDao.getInstance();
        String mbrId = (String) session.getAttribute("id");
        MemberDto dto = dao.selectById(mbrId);
        dto.setMbrId("1-" + dto.getMbrId());
        dto.setMbrEmail("0");
        dto.setMbrTel("0");
        int n = dao.update(dto);
        if (n > 0) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("errMsg", "다시실행해주세요.");
            req.setAttribute("mainPage", "/views/member/mypage/mypageMain.jsp");
            req.setAttribute("mainPageTitle", "Betamall - 마이 페이지");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
        }
    }

}
