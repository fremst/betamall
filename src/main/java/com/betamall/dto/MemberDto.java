package com.betamall.dto;

import java.sql.Date;

public class MemberDto {
    private int mbrNo;
    private String mbrName;
    private String mbrTel;
    private String mbrAdr;
    private String mbrEmail;
    private String mbrId;
    private String mbrPwd;
    private Date mbrBd;
    private Date mbrRegdate;
    private String mbrGrade;
    private int totAmt;

    public MemberDto() {}

    public MemberDto(int mbrNo, String mbrName, String mbrTel, String mbrAdr, String mbrEmail, String mbrId, String mbrPwd, Date mbrBd, Date mbrRegdate, String mbrGrade, int totAmt) {
        this.mbrNo = mbrNo;
        this.mbrName = mbrName;
        this.mbrTel = mbrTel;
        this.mbrAdr = mbrAdr;
        this.mbrEmail = mbrEmail;
        this.mbrId = mbrId;
        this.mbrPwd = mbrPwd;
        this.mbrBd = mbrBd;
        this.mbrRegdate = mbrRegdate;
        this.mbrGrade = mbrGrade;
        this.totAmt = totAmt;
    }


    public int getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(int mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getMbrName() {
        return mbrName;
    }

    public void setMbrName(String mbrName) {
        this.mbrName = mbrName;
    }

    public String getMbrTel() {
        return mbrTel;
    }

    public void setMbrTel(String mbrTel) {
        this.mbrTel = mbrTel;
    }

    public String getMbrAdr() {
        return mbrAdr;
    }

    public void setMbrAdr(String mbrAdr) {
        this.mbrAdr = mbrAdr;
    }

    public String getMbrEmail() {
        return mbrEmail;
    }

    public void setMbrEmail(String mbrEmail) {
        this.mbrEmail = mbrEmail;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrPwd() {
        return mbrPwd;
    }

    public void setMbrPwd(String mbrPwd) {
        this.mbrPwd = mbrPwd;
    }

    public Date getMbrBd() {
        return mbrBd;
    }

    public void setMbrBd(Date mbrBd) {
        this.mbrBd = mbrBd;
    }

    public Date getMbrRegdate() {
        return mbrRegdate;
    }

    public void setMbrRegdate(Date mbrRegdate) {
        this.mbrRegdate = mbrRegdate;
    }

    public String getMbrGrade() {
        return mbrGrade;
    }

    public void setMbrGrade(String mbrGrade) {
        this.mbrGrade = mbrGrade;
    }

    public int getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(int totAmt) {
        this.totAmt = totAmt;
    }
}
