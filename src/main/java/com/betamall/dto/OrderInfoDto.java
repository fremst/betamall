package com.betamall.dto;

import java.sql.Date;

public class OrderInfoDto {

	private int ordNo;
	private int itemNo;
	private String itemName;
	private int ordCnt;
	private int price;
	private Date ordDate;
	private String ordSta;
	private String mbrId;
	private String ordName;
	private String ordTel;
	private String ordAddr;
	
	public OrderInfoDto() {}

	public OrderInfoDto(int ordNo, int itemNo, String itemName, int ordCnt, int price, Date ordDate, String ordSta,
			String mbrId, String ordName, String ordTel, String ordAddr) {
		
		this.ordNo = ordNo;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.ordCnt = ordCnt;
		this.price = price;
		this.ordDate = ordDate;
		this.ordSta = ordSta;
		this.mbrId = mbrId;
		this.ordName = ordName;
		this.ordTel = ordTel;
		this.ordAddr = ordAddr;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public String getOrdSta() {
		return ordSta;
	}

	public void setOrdSta(String ordSta) {
		this.ordSta = ordSta;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getOrdName() {
		return ordName;
	}

	public void setOrdName(String ordName) {
		this.ordName = ordName;
	}

	public String getOrdTel() {
		return ordTel;
	}

	public void setOrdTel(String ordTel) {
		this.ordTel = ordTel;
	}

	public String getOrdAddr() {
		return ordAddr;
	}

	public void setOrdAddr(String ordAddr) {
		this.ordAddr = ordAddr;
	}

	@Override
	public String toString() {
		return "OrdInfoDto [ordNo=" + ordNo + ", itemNo=" + itemNo + ", itemName=" + itemName + ", ordCnt=" + ordCnt
				+ ", price=" + price + ", ordDate=" + ordDate + ", ordSta=" + ordSta + ", mbrId=" + mbrId + ", ordName="
				+ ordName + ", ordTel=" + ordTel + ", ordAddr=" + ordAddr + "]\n";
	}
}
