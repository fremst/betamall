package com.betamall.dto;

import java.sql.Date;

public class SalesInfoDto {
	
	private int ordNo;
	private String mcatName;
	private String scatName;
	private String itemName;
	private int ordCnt;
	private String brName;
	private Date ordDate;
	private String ordSta;
	
	public SalesInfoDto() {}
	
	public SalesInfoDto(int ordNo, String mcatName, String scatName, String itemName, int ordCnt, String brName,
			Date ordDate, String ordSta) {
		this.ordNo = ordNo;
		this.mcatName = mcatName;
		this.scatName = scatName;
		this.itemName = itemName;
		this.ordCnt = ordCnt;
		this.brName = brName;
		this.ordDate = ordDate;
		this.ordSta = ordSta;
	}
	
	public int getOrdNo() {
		return ordNo;
	}
	
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}
	
	public String getMcatName() {
		return mcatName;
	}
	
	public void setMcatName(String mcatName) {
		this.mcatName = mcatName;
	}
	
	public String getScatName() {
		return scatName;
	}
	
	public void setScatName(String scatName) {
		this.scatName = scatName;
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
	
	public String getBrName() {
		return brName;
	}
	
	public void setBrName(String brName) {
		this.brName = brName;
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
	
	@Override
	public String toString() {
		return "SalesInfoDto [ordNo=" + ordNo + ", mcatName=" + mcatName + ", scatName=" + scatName + ", itemName="
				+ itemName + ", ordCnt=" + ordCnt + ", brName=" + brName + ", ordDate=" + ordDate + ", ordSta=" + ordSta
				+ "]\n";
	}
}
