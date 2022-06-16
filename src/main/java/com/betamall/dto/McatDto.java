package com.betamall.dto;

public class McatDto {
	private int mcatNo;
	private String mcatName;
	private boolean mcatDel;
	
	public McatDto() {}

	public McatDto(int mcatNo, String mcatName, boolean mcatDel) {
		this.mcatNo = mcatNo;
		this.mcatName = mcatName;
		this.mcatDel = mcatDel;
	}

	public int getMcatNo() {
		return mcatNo;
	}

	public void setMcatNo(int mcatNo) {
		this.mcatNo = mcatNo;
	}

	public String getMcatName() {
		return mcatName;
	}

	public void setMcatName(String mcatName) {
		this.mcatName = mcatName;
	}

	public boolean isMcatDel() {
		return mcatDel;
	}

	public void setMcatDel(boolean mcatDel) {
		this.mcatDel = mcatDel;
	}

	@Override
	public String toString() {
		return "McatDto [mcatNo=" + mcatNo + ", mcatName=" + mcatName + ", mcatDel=" + mcatDel + "]\n";
	}
	
}
