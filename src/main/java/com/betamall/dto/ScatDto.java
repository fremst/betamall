package com.betamall.dto;

public class ScatDto {
	private int mcatNo;
	private int scatNo;
	private String scatName;
	private boolean scatDel;
	
	public ScatDto() {}

	public ScatDto(int mcatNo, int scatNo, String scatName, boolean scatDel) {
		super();
		this.mcatNo = mcatNo;
		this.scatNo = scatNo;
		this.scatName = scatName;
		this.scatDel = scatDel;
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

	public String getScatName() {
		return scatName;
	}

	public void setScatName(String scatName) {
		this.scatName = scatName;
	}

	public boolean isScatDel() {
		return scatDel;
	}

	public void setScatDel(boolean scatDel) {
		this.scatDel = scatDel;
	}

	@Override
	public String toString() {
		return "ScatDto [mcatNo=" + mcatNo + ", scatNo=" + scatNo + ", scatName=" + scatName + ", scatDel=" + scatDel
				+ "]\n";
	}
	
	
}
