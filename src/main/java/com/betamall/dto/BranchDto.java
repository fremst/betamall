package com.betamall.dto;

import java.sql.Date;

public class BranchDto {
	private int brNo;
	private String brName;
	private String brAddr;
	private String brTel;
	private Date brDate;
	private String brImg;
	
	public BranchDto() {}
	
	public BranchDto(int brNo, String brName, String brAddr, String brTel, Date brDate, String brImg) {
		this.brNo = brNo;
		this.brName = brName;
		this.brAddr = brAddr;
		this.brTel = brTel;
		this.brDate = brDate;
		this.brImg = brImg;
	}
	
	public int getBrNo() {
		return brNo;
	}
	public void setBrNo(int brNo) {
		this.brNo = brNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getBrAddr() {
		return brAddr;
	}
	public void setBrAddr(String brAddr) {
		this.brAddr = brAddr;
	}
	public String getBrTel() {
		return brTel;
	}
	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}
	public Date getBrDate() {
		return brDate;
	}
	public void setBrDate(Date brDate) {
		this.brDate = brDate;
	}
	public String getBrImg() {
		return brImg;
	}
	public void setBrImg(String brImg) {
		this.brImg = brImg;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.brNo == ((BranchDto)obj).getBrNo();
	}
	
	@Override
	public String toString() {
		return "BranchDto [brNo=" + brNo + ", brName=" + brName + ", brAddr=" + brAddr + ", brTel=" + brTel
				+ ", brDate=" + brDate + ", brImg=" + brImg + "]\n";
	}
	
}