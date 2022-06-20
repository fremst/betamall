package com.betamall.dto;

public class ItemDetailDto {
	private int itemNo;
	private String itemName;
	private String tImg;
	private int mcatNo;
	private int scatNo;
	private String mcatName;
	private String scatName;
	private String hash;
	private int price;
	private String brName;
	private int stkCnt;
	
	public ItemDetailDto() {}

	public ItemDetailDto(int itemNo, String itemName, String tImg, int mcatNo, int scatNo, String mcatName,
			String scatName, String hash, int price, String brName, int stkCnt) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.tImg = tImg;
		this.mcatNo = mcatNo;
		this.scatNo = scatNo;
		this.mcatName = mcatName;
		this.scatName = scatName;
		this.hash = hash;
		this.price = price;
		this.brName = brName;
		this.stkCnt = stkCnt;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String gettImg() {
		return tImg;
	}

	public void settImg(String tImg) {
		this.tImg = tImg;
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

	public String getMcatName() {
		return mcatName;
	}

	public void setMcatName(String mcatName) {
		this.mcatName = mcatName;
	}

	public String getScatName() {
		return scatName;
	}

	public void setScatName(String scatName) {
		this.scatName = scatName;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	public int getStkCnt() {
		return stkCnt;
	}

	public void setStkCnt(int stkCnt) {
		this.stkCnt = stkCnt;
	}

	@Override
	public String toString() {
		return "ItemDetailDto [itemNo=" + itemNo + ", itemName=" + itemName + ", tImg=" + tImg + ", mcatNo=" + mcatNo
				+ ", scatNo=" + scatNo + ", mcatName=" + mcatName + ", scatName=" + scatName + ", hash=" + hash
				+ ", price=" + price + ", brName=" + brName + ", stkCnt=" + stkCnt + "]\n";
	}

}