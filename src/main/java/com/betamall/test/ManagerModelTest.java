package com.betamall.test;

import com.betamall.dao.ManagerInfoDao;

public class ManagerModelTest {
	public static void main(String[] args) {
//		ManagerDao mgrDao = ManagerDao.getInstance();
	
//		ManagerDto mgrDto = new ManagerDto(0, 1, "아이디", "비번", "이름", "전화", "메일", "이미지");
//		System.out.println(mgrDao.insert(mgrDto));
//		System.out.println(mgrDao.selectAll());
		
		ManagerInfoDao mgrInfoDao = ManagerInfoDao.getInstance();
		System.out.println(mgrInfoDao.getCount());
//		System.out.println(mgrInfoDao.selectAll());
//		System.out.println(mgrInfoDao.selectFromTo(3,6));
	}
}
