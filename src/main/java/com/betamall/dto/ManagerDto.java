package com.betamall.dto;

public class ManagerDto {
	private int mgrNo;
	private int brNo;
	private String mgrId;
	private String mgrPwd;
	private String mgrName;
	private String mgrTel;
	private String mgrEmail;
	private String mgrImg;
	
	public ManagerDto() {}
	
	public ManagerDto(int mgrNo, int brNo, String mgrId, String mgrPwd, String mgrName, String mgrTel, String mgrEmail,
			String mgrImg) {
		this.mgrNo = mgrNo;
		this.brNo = brNo;
		this.mgrId = mgrId;
		this.mgrPwd = mgrPwd;
		this.mgrName = mgrName;
		this.mgrTel = mgrTel;
		this.mgrEmail = mgrEmail;
		this.mgrImg = mgrImg;
	}
	
	public int getMgrNo() {
		return mgrNo;
	}
	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}
	public int getBrNo() {
		return brNo;
	}
	public void setBrNo(int brNo) {
		this.brNo = brNo;
	}
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	public String getMgrPwd() {
		return mgrPwd;
	}
	public void setMgrPwd(String mgrPwd) {
		this.mgrPwd = mgrPwd;
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
	public String getMgrImg() {
		return mgrImg;
	}
	public void setMgrImg(String mgrImg) {
		this.mgrImg = mgrImg;
	}
	
	@Override
	public String toString() {
		return "ManagerDto [mgrNo=" + mgrNo + ", brNo=" + brNo + ", mgrId=" + mgrId + ", mgrPwd=" + mgrPwd
				+ ", mgrName=" + mgrName + ", mgrTel=" + mgrTel + ", mgrEmail=" + mgrEmail + ", mgrImg=" + mgrImg + "]\n";
	}
	
}
