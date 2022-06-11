package com.betamall.dto;

public class ManagerInfoDto {
	private int mgrNo;
	private String mgrImg;
	private String brName;
	private String brTel;
	private String brAddr;
	private String mgrName;
	private String mgrTel;
	private String mgrEmail;
	
	public ManagerInfoDto() {}

	public ManagerInfoDto(int mgrNo, String mgrImg, String brName, String brTel, String brAddr, String mgrName,
			String mgrTel, String mgrEmail) {
		super();
		this.mgrNo = mgrNo;
		this.mgrImg = mgrImg;
		this.brName = brName;
		this.brTel = brTel;
		this.brAddr = brAddr;
		this.mgrName = mgrName;
		this.mgrTel = mgrTel;
		this.mgrEmail = mgrEmail;
	}

	public int getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}

	public String getMgrImg() {
		return mgrImg;
	}

	public void setMgrImg(String mgrImg) {
		this.mgrImg = mgrImg;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	public String getBrTel() {
		return brTel;
	}

	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}

	public String getBrAddr() {
		return brAddr;
	}

	public void setBrAddr(String brAddr) {
		this.brAddr = brAddr;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public String getMgrTel() {
		return mgrTel;
	}

	public void setMgrTel(String mgrTel) {
		this.mgrTel = mgrTel;
	}

	public String getMgrEmail() {
		return mgrEmail;
	}

	public void setMgrEmail(String mgrEmail) {
		this.mgrEmail = mgrEmail;
	}

	@Override
	public String toString() {
		return "ManagerInfoDto [mgrNo=" + mgrNo + ", mgrImg=" + mgrImg + ", brName=" + brName + ", brTel=" + brTel
				+ ", brAddr=" + brAddr + ", mgrName=" + mgrName + ", mgrTel=" + mgrTel + ", mgrEmail=" + mgrEmail + "]\n";
	}

	
	
}
