package com.betamall.test;

import com.betamall.dao.BranchDao;

public class BranchModelTest {
	public static void main(String[] args) {
		
		
		BranchDao brDao = BranchDao.getInstance();
		
		//SelectAll Test
//		System.out.println(brDao.selectAll());
		
		//Insert Test
//		BranchDto brDto = new BranchDto(0, "서울역 2호점", "서울", "010", new Date(0), "이미지");
//		System.out.println(brDao.insert(brDto));
//		System.out.println(brDao.selectAll());
		
		//Select Test
//		System.out.println(brDao.select(3));
//		System.out.println(brDao.selectByBrName("서울역 2호점"));
		System.out.println(brDao.selectWoMgr());
		
	}
}