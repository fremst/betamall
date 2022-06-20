package com.betamall.dto;

import java.sql.Date;

public class QnaDto {
	private int qnaNo;
	private int mbrNo;
	private int itemNo;
	private String qnaCat;
	private String qnaTitle;
	private String qnaCon;
	private String qnaFile;
	private boolean secret;
	private Date qnaWdate;
	private Date qnaMdate; 
	private boolean qnaDel;
	
	public QnaDto() {}

	public QnaDto(int qnaNo, int mbrNo, int itemNo, String qnaCat, String qnaTitle, String qnaCon, String qnaFile,
			boolean secret, Date qnaWdate, Date qnaMdate, boolean qnaDel) {
		super();
		this.qnaNo = qnaNo;
		this.mbrNo = mbrNo;
		this.itemNo = itemNo;
		this.qnaCat = qnaCat;
		this.qnaTitle = qnaTitle;
		this.qnaCon = qnaCon;
		this.qnaFile = qnaFile;
		this.secret = secret;
		this.qnaWdate = qnaWdate;
		this.qnaMdate = qnaMdate;
		this.qnaDel = qnaDel;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public int getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(int mbrNo) {
		this.mbrNo = mbrNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getQnaCat() {
		return qnaCat;
	}

	public void setQnaCat(String qnaCat) {
		this.qnaCat = qnaCat;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaCon() {
		return qnaCon;
	}

	public void setQnaCon(String qnaCon) {
		this.qnaCon = qnaCon;
	}

	public String getQnaFile() {
		return qnaFile;
	}

	public void setQnaFile(String qnaFile) {
		this.qnaFile = qnaFile;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	public Date getQnaWdate() {
		return qnaWdate;
	}

	public void setQnaWdate(Date qnaWdate) {
		this.qnaWdate = qnaWdate;
	}

	public Date getQnaMdate() {
		return qnaMdate;
	}

	public void setQnaMdate(Date qnaMdate) {
		this.qnaMdate = qnaMdate;
	}

	public boolean isQnaDel() {
		return qnaDel;
	}

	public void setQnaDel(boolean qnaDel) {
		this.qnaDel = qnaDel;
	}

	@Override
	public String toString() {
		return "QnaDto [qnaNo=" + qnaNo + ", mbrNo=" + mbrNo + ", itemNo=" + itemNo + ", qnaCat=" + qnaCat
				+ ", qnaTitle=" + qnaTitle + ", qnaCon=" + qnaCon + ", qnaFile=" + qnaFile + ", secret=" + secret
				+ ", qnaWdate=" + qnaWdate + ", qnaMdate=" + qnaMdate + ", qnaDel=" + qnaDel + "]";
	}

	
}
