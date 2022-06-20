package com.betamall.dto;

import java.sql.Date;

public class MbrCouponInfoDto {
    private int mbrNo;
    private int cpnNo;
    private String cond;
    private int discRate;
    private int mbrCpnCnt;
    private int minord;
    private Date expDate;
    private Date period;
    private boolean active;
    private boolean issue;
    private int scatNo;
    private int mactNo;

    public MbrCouponInfoDto() {
    }

    public MbrCouponInfoDto(int mbrNo, int cpnNo, String cond, int discRate, int mbrCpnCnt, int minord, Date expDate,
            Date period, boolean active, boolean issue, int scatNo, int mactNo) {
        this.mbrNo = mbrNo;
        this.cpnNo = cpnNo;
        this.cond = cond;
        this.discRate = discRate;
        this.mbrCpnCnt = mbrCpnCnt;
        this.minord = minord;
        this.expDate = expDate;
        this.period = period;
        this.active = active;
        this.issue = issue;
        this.scatNo = scatNo;
        this.mactNo = mactNo;
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getMinord() {
        return minord;
    }

    public void setMinord(int minord) {
        this.minord = minord;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public boolean isIssue() {
        return issue;
    }

    public void setIssue(boolean issue) {
        this.issue = issue;
    }

    public int getScatNo() {
        return scatNo;
    }

    public void setScatNo(int scatNo) {
        this.scatNo = scatNo;
    }

    public int getMactNo() {
        return mactNo;
    }

    public void setMactNo(int mactNo) {
        this.mactNo = mactNo;
    }

    public int getDiscRate() {
        return discRate;
    }

    public void setDiscRate(int discRate) {
        this.discRate = discRate;
    }

    @Override
    public String toString() {
        return "MbrCouponInfoDto{" +
                "mbrNo=" + mbrNo +
                ", cpnNo=" + cpnNo +
                ", cond='" + cond + '\'' +
                ", discRate=" + discRate +
                ", mbrCpnCnt=" + mbrCpnCnt +
                ", minord=" + minord +
                ", expDate=" + expDate +
                ", period=" + period +
                ", active=" + active +
                ", issue=" + issue +
                ", scatNo=" + scatNo +
                ", mactNo=" + mactNo +
                '}';
    }
}