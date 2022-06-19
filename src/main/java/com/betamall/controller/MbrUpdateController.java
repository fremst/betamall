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
import java.sql.Date;

@WebServlet(urlPatterns = { "/member/mypage", "/member/update" })
@SuppressWarnings("serial")
public class MbrUpdateController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mbrId = (String) session.getAttribute("id");
        MemberDao mbrDao = MemberDao.getInstance();
        MemberDto mbrDto = mbrDao.select(mbrId);
        String str = mbrDto.getMbrAdr();
        String[] adresses = str.split("/");
        req.setAttribute("mbrDto", mbrDto);
        req.setAttribute("postno", adresses[0]);
        req.setAttribute("mbrAdr", adresses[1]);
        req.setAttribute("mbrAdr1", adresses[2]);
        req.setAttribute("mbrAdr2", adresses[3]);
        req.setAttribute("mainPageTitle", "Betamall - 마이 페이지");
        req.setAttribute("mainPage", "/views/member/mypage/mypageMain.jsp");
        req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int mbrNo = Integer.parseInt(req.getParameter("mbrNo"));
        String mbrName = req.getParameter("mbrName");
        String mbrTel = req.getParameter("mbrTel");
        String mbrAdr = req.getParameter("postno") + "/" + req.getParameter("addr") + "/" + req.getParameter("addr1")
                + "/" + req.getParameter("addr2");
        String mbrEmail = req.getParameter("mbrEmail");
        String mbrId = req.getParameter("mbrId");
        String mbrPwd = req.getParameter("mbrPwd");
        Date mbrBd = Date.valueOf(req.getParameter("mbrBd"));

        int totAmt = Integer.parseInt(req.getParameter("totAmt"));
        MemberDto dto = new MemberDto(mbrNo, mbrName, mbrTel, mbrAdr, mbrEmail, mbrId, mbrPwd, mbrBd, null, null,
                totAmt);
        MemberDao dao = MemberDao.getInstance();
        int n = dao.update(dto);
        if (n > 0) {
            req.setAttribute("errMsg", "정보수정이 완료되었습니다.");
            req.setAttribute("mainPage", "/views/member/mypage/mypageMain.jsp");
            req.setAttribute("mainPageTitle", "Betamall - 마이 페이지");
            req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/member/mypage");
        }

    }
}
