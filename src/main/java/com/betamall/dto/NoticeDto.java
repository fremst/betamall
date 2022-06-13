package com.betamall.dto;

import java.sql.Date;

public class NoticeDto {
	private int brdNo;
	private int mgrNo;
	private String brdCat;
	private String brdTitle;
	private String brdCon;
	private String brdImg;
	private Date brdWdate;
	private Date brdMdate; 
	private Date brdSdate;
	private Date brdFdate;
	private boolean popUp;
	
	public NoticeDto() {}

	public NoticeDto(int brdNo, int mgrNo, String brdCat, String brdTitle, String brdCon, String brdImg, Date brdWdate,
			Date brdMdate, Date brdSdate, Date brdFdate, boolean popUp) {
		super();
		this.brdNo = brdNo;
		this.mgrNo = mgrNo;
		this.brdCat = brdCat;
		this.brdTitle = brdTitle;
		this.brdCon = brdCon;
		this.brdImg = brdImg;
		this.brdWdate = brdWdate;
		this.brdMdate = brdMdate;
		this.brdSdate = brdSdate;
		this.brdFdate = brdFdate;
		this.popUp = popUp;
	}

	public int getBrdNo() {
		return brdNo;
	}

	public void setBrdNo(int brdNo) {
		this.brdNo = brdNo;
	}

	public int getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}

	public String getBrdCat() {
		return brdCat;
	}

	public void setBrdCat(String brdCat) {
		this.brdCat = brdCat;
	}

	public String getBrdTitle() {
		return brdTitle;
	}

	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}

	public String getBrdCon() {
		return brdCon;
	}

	public void setBrdCon(String brdCon) {
		this.brdCon = brdCon;
	}

	public String getBrdImg() {
		return brdImg;
	}

	public void setBrdImg(String brdImg) {
		this.brdImg = brdImg;
	}

	public Date getBrdWdate() {
		return brdWdate;
	}

	public void setBrdWdate(Date brdWdate) {
		this.brdWdate = brdWdate;
	}

	public Date getBrdMdate() {
		return brdMdate;
	}

	public void setBrdMdate(Date brdMdate) {
		this.brdMdate = brdMdate;
	}

	public Date getBrdSdate() {
		return brdSdate;
	}

	public void setBrdSdate(Date brdSdate) {
		this.brdSdate = brdSdate;
	}

	public Date getBrdFdate() {
		return brdFdate;
	}

	public void setBrdFdate(Date brdFdate) {
		this.brdFdate = brdFdate;
	}

	public boolean isPopUp() {
		return popUp;
	}

	public void setPopUp(boolean popUp) {
		this.popUp = popUp;
	}

	@Override
	public String toString() {
		return "NoticeDto [brdNo=" + brdNo + ", mgrNo=" + mgrNo + ", brdCat=" + brdCat + ", brdTitle=" + brdTitle
				+ ", brdCon=" + brdCon + ", brdImg=" + brdImg + ", brdWdate=" + brdWdate + ", brdMdate=" + brdMdate
				+ ", brdSdate=" + brdSdate + ", brdFdate=" + brdFdate + ", popUp=" + popUp + "]\n";
	}

}
