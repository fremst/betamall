package com.betamall.dto;

import java.sql.Date;

public class OrderDto {

	private int ordNo;
	private int mbrNo;
	private int brNo;
	private Date ordDate;
	private String ordSta;
	private String ordName;
	private String ordArrd;
	private String ordTel;
	
	public OrderDto() {}

	public OrderDto(int ordNo, int mbrNo, int brNo, Date ordDate, String ordSta, String ordName, String ordArrd,
			String ordTel) {
		this.ordNo = ordNo;
		this.mbrNo = mbrNo;
		this.brNo = brNo;
		this.ordDate = ordDate;
		this.ordSta = ordSta;
		this.ordName = ordName;
		this.ordArrd = ordArrd;
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

	public String getOrdArrd() {
		return ordArrd;
	}

	public void setOrdArrd(String ordArrd) {
		this.ordArrd = ordArrd;
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
				+ ordSta + ", ordName=" + ordName + ", ordArrd=" + ordArrd + ", ordTel=" + ordTel + ", getOrdNo()="
				+ getOrdNo() + ", getMbrNo()=" + getMbrNo() + ", getBrNo()=" + getBrNo() + ", getOrdDate()="
				+ getOrdDate() + ", getOrdSta()=" + getOrdSta() + ", getOrdName()=" + getOrdName() + ", getOrdArrd()="
				+ getOrdArrd() + ", getOrdTel()=" + getOrdTel() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]\n";
	}
}