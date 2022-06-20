package com.betamall.dto;

import java.sql.Date;

public class PmtDto {
	int ordNo;
	int pmtAmt;
	String pmtType;
	Date pmtDate;
	
	public PmtDto() {}

	public PmtDto(int ordNo, int pmtAmt, String pmtType, Date pmtDate) {
		this.ordNo = ordNo;
		this.pmtAmt = pmtAmt;
		this.pmtType = pmtType;
		this.pmtDate = pmtDate;
	}

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public int getPmtAmt() {
		return pmtAmt;
	}

	public void setPmtAmt(int pmtAmt) {
		this.pmtAmt = pmtAmt;
	}

	public String getPmtType() {
		return pmtType;
	}

	public void setPmtType(String pmtType) {
		this.pmtType = pmtType;
	}

	public Date getPmtDate() {
		return pmtDate;
	}

	public void setPmtDate(Date pmtDate) {
		this.pmtDate = pmtDate;
	}

	@Override
	public String toString() {
		return "PmtDto [ordNo=" + ordNo + ", pmtAmt=" + pmtAmt + ", pmtType=" + pmtType + ", pmtDate=" + pmtDate + "]\n";
	}
	
}
