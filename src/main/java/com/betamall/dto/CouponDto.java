package com.betamall.dto;

import java.sql.Date;
import java.util.concurrent.CountDownLatch;

public class CouponDto {
    private int cpnNo;
    private int discRate;
    private Date expDate;
    private Date period;
    private boolean active;
    private int minOrd;
    private String cond;
    private boolean issued;
    private int mcatNo;
    private int scatNo;

    public CouponDto(){}

    public CouponDto(int cpnNo, int discRate, Date expDate, Date period, boolean active, int minOrd, String cond, boolean issued, int mcatNo, int scatNo) {
        this.cpnNo = cpnNo;
        this.discRate = discRate;
        this.expDate = expDate;
        this.period = period;
        this.active = active;
        this.minOrd = minOrd;
        this.cond = cond;
        this.issued = issued;
        this.mcatNo = mcatNo;
        this.scatNo = scatNo;
    }

    public int getCpnNo() {
        return cpnNo;
    }

    public void setCpnNo(int cpnNo) {
        this.cpnNo = cpnNo;
    }

    public int getDiscRate() {
        return discRate;
    }

    public void setDiscRate(int discRate) {
        this.discRate = discRate;
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

    public int getMinOrd() {
        return minOrd;
    }

    public void setMinOrd(int minOrd) {
        this.minOrd = minOrd;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public int getMcatNo() {
        return mcatNo;
    }

    public void setMcatNo(int mcatNo) {
        this.mcatNo = mcatNo;
    }

    public int getScatNo() {
        return scatNo;
    }

    public void setScatNo(int scatNo) {
        this.scatNo = scatNo;
    }
}
