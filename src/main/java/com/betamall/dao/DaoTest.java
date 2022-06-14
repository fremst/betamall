package com.betamall.dao;

public class DaoTest {
	public static void main(String[] args) {
		NoticeDao dao=NoticeDao.getInstance();
//		System.out.println(dao.insert(new NoticeDto(0, 1, "공지", "타이틀", "내용", "0.png", null, null, null, null, true)));
//		System.out.println(dao.getCount("BRDCAT", "공지"));
		System.out.println(dao.list(3, 5, "BRDCAT", "공지"));
		System.out.println(dao.list(3, 5, "", null));
	}
}
