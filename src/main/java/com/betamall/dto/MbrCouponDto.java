package com.betamall.dto;

public class MbrCouponDto {
    private int mbrNo;
    private String mbrId;
    private int cpnNo;
    private int mbrCpnCnt;
    private String cond;

    public MbrCouponDto(){}
    
    public MbrCouponDto(String mbrId, String cond) {
    	this.mbrId = mbrId;
    	this.cond=cond;
    }

    public MbrCouponDto(int mbrNo, int cpnNo, int mbrCpnCnt) {
        this.mbrNo = mbrNo;
        this.cpnNo = cpnNo;
        this.mbrCpnCnt = mbrCpnCnt;
    }
    
    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    
    public int getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(int mbrNo) {
        this.mbrNo = mbrNo;
    }

    public int getCpnNo() {
        return cpnNo;
    }

    public void setCpnNo(int cpnNo) {
        this.cpnNo = cpnNo;
    }

    public int getMbrCpnCnt() {
        return mbrCpnCnt;
    }

    public void setMbrCpnCnt(int mbrCpnCnt) {
        this.mbrCpnCnt = mbrCpnCnt;
    }

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
}
