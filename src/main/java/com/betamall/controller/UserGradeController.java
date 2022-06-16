package com.betamall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betamall.dao.MbrCouponDao;
import com.betamall.dto.MbrCouponDto;


@WebServlet("/member/userGrade")
@SuppressWarnings("serial")
public class UserGradeController extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 HttpSession session = req.getSession();
	 String mbrId = (String) session.getAttribute("id");
	 MbrCouponDao mbrCouponDao = MbrCouponDao.getInstance();
	 MbrCouponDto mbrCouponDto = mbrCouponDao.haveCoupon(mbrId);
	 req.setAttribute("mbrCouponDto", mbrCouponDto);
	 if(mbrCouponDto == null) {
		 req.setAttribute("errMsg", "보유하고 계신 쿠폰이 없습니다.");
	 }
	 
	 req.setAttribute("mainPageTitle", "Betamall - 회원 등급/쿠폰 조회");
	 req.setAttribute("mainPage", "/views/member/mypage/userGrade.jsp");
	 req.getRequestDispatcher("/views/common/layout.jsp").forward(req, resp);

    }
}
