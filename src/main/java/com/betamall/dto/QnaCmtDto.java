package com.betamall.dto;

import java.sql.Date;

public class QnaCmtDto {
	private int qnaCmtNo;
	private int qnaNo;
	private String qnaCmtCon;
	private Date qnaCmtWdate;
	private boolean qnaCmtDel;
	
	public QnaCmtDto() {}

	public QnaCmtDto(int qnaCmtNo, int qnaNo, String qnaCmtCon, Date qnaCmtWdate, boolean qnaCmtDel) {
		super();
		this.qnaCmtNo = qnaCmtNo;
		this.qnaNo = qnaNo;
		this.qnaCmtCon = qnaCmtCon;
		this.qnaCmtWdate = qnaCmtWdate;
		this.qnaCmtDel = qnaCmtDel;
	}

	public int getQnaCmtNo() {
		return qnaCmtNo;
	}

	public void setQnaCmtNo(int qnaCmtNo) {
		this.qnaCmtNo = qnaCmtNo;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaCmtCon() {
		return qnaCmtCon;
	}

	public void setQnaCmtCon(String qnaCmtCon) {
		this.qnaCmtCon = qnaCmtCon;
	}

	public Date getQnaCmtWdate() {
		return qnaCmtWdate;
	}

	public void setQnaCmtWdate(Date qnaCmtWdate) {
		this.qnaCmtWdate = qnaCmtWdate;
	}

	public boolean isQnaCmtDel() {
		return qnaCmtDel;
	}

	public void setQnaCmtDel(boolean qnaCmtDel) {
		this.qnaCmtDel = qnaCmtDel;
	}

	@Override
	public String toString() {
		return "QnaCmtDto [qnaCmtNo=" + qnaCmtNo + ", qnaNo=" + qnaNo + ", qnaCmtCon=" + qnaCmtCon + ", qnaCmtWdate="
				+ qnaCmtWdate + ", qnaCmtDel=" + qnaCmtDel + "]";
	}
	
}
