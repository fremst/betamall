package com.betamall.dto;

import java.sql.Date;

public class OrderDto {

	private int ordNo;
	private int mbrNo;
	private int brNo;
	private Date ordDate;
	private String ordSta;
	private String ordName;
	private String ordAdr;
	private String ordTel;
	
	public OrderDto() {}

	public OrderDto(int ordNo, int mbrNo, int brNo, Date ordDate, String ordSta, String ordName, String ordAdr,
			String ordTel) {
		
		this.ordNo = ordNo;
		this.mbrNo = mbrNo;
		this.brNo = brNo;
		this.ordDate = ordDate;
		this.ordSta = ordSta;
		this.ordName = ordName;
		this.ordAdr = ordAdr;
		this.ordTel = ordTel;
	}

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public int getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(int mbrNo) {
		this.mbrNo = mbrNo;
	}

	public int getBrNo() {
		return brNo;
	}

	public void setBrNo(int brNo) {
		this.brNo = brNo;
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

	public String getOrdName() {
		return ordName;
	}

	public void setOrdName(String ordName) {
		this.ordName = ordName;
	}

	public String getOrdAdr() {
		return ordAdr;
	}

	public void setOrdAdr(String ordAdr) {
		this.ordAdr = ordAdr;
	}

	public String getOrdTel() {
		return ordTel;
	}

	public void setOrdTel(String ordTel) {
		this.ordTel = ordTel;
	}

	@Override
	public String toString() {
		return "OrderDto [ordNo=" + ordNo + ", mbrNo=" + mbrNo + ", brNo=" + brNo + ", ordDate=" + ordDate + ", ordSta="
				+ ordSta + ", ordName=" + ordName + ", ordAdr=" + ordAdr + ", ordTel=" + ordTel + "]\n";
	}
	
}
