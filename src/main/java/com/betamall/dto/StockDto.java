package com.betamall.dto;

public class StockDto {

	private int itemNo;
	private int brNo;
	private int stkCnt;
	
	public StockDto(int itemNo, int brNo, int stkCnt) {
		this.itemNo = itemNo;
		this.brNo = brNo;
		this.stkCnt = stkCnt;
	}
	
	public int getItemNo() {
		return itemNo;
	}
	
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	
	public int getBrNo() {
		return brNo;
	}
	
	public void setBrNo(int brNo) {
		this.brNo = brNo;
	}
	
	public int getStkCnt() {
		return stkCnt;
	}
	
	public void setStkCnt(int stkCnt) {
		this.stkCnt = stkCnt;
	}

	@Override
	public String toString() {
		return "StockDto [itemNo=" + itemNo + ", brNo=" + brNo + ", stkCnt=" + stkCnt + "]\n";
	}
}
