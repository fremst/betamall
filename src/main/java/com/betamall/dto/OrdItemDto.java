package com.betamall.dto;

import java.sql.Date;

public class OrdItemDto {

	private int ordNo;
	private int itemNo;
	private int ordCnt;
	private String review;
	private int rate;
	private Date revDate;
	
	public OrdItemDto() {}
	
	public OrdItemDto(int ordNo, int itemNo, int ordCnt, String review, int rate, Date revDate) {
		this.ordNo = ordNo;
		this.itemNo = itemNo;
		this.ordCnt = ordCnt;
		this.review = review;
		this.rate = rate;
		this.revDate = revDate;
	}

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Date getRevDate() {
		return revDate;
	}

	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}

	@Override
	public String toString() {
		return "OrdItem [ordNo=" + ordNo + ", itemNo=" + itemNo + ", ordCnt=" + ordCnt + ", review=" + review
				+ ", rate=" + rate + ", revDate=" + revDate + "]\n";
	}
}
