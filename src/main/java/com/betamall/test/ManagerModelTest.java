package com.betamall.test;

import com.betamall.dao.ManagerDao;

public class ManagerModelTest {
	public static void main(String[] args) {
		ManagerDao mgrDao = ManagerDao.getInstance();
	
//		ManagerDto mgrDto = new ManagerDto(0, 1, "아이디", "비번", "이름", "전화", "메일", "이미지");
//		System.out.println(mgrDao.insert(mgrDto));
//		System.out.println(mgrDao.selectAll());
		
//		ManagerDto mgrDto = new ManagerDto(26, 6, "kim6", "1234", "김육번", "010-666-6666", "kim6@beta.com", "mgr62.jpg"); 
//		ManagerDto mgrDto = new ManagerDto(26, 6, "asdf", "ass1234", "김aa육번", "01sss0-666-6666", "sfskim6@beta.com", "mssgr62.jpg");
//		System.out.println(mgrDao.update(mgrDto));
//		System.out.println(mgrDao.selectAll());
		
		System.out.println(mgrDao.selectById("admin0"));
		
//		ManagerInfoDao mgrInfoDao = ManagerInfoDao.getInstance();
//		System.out.println(mgrInfoDao.getCount());
//		System.out.println(mgrInfoDao.selectAll());
//		System.out.println(mgrInfoDao.selectFromTo(3,6));
		
		
	}
}
