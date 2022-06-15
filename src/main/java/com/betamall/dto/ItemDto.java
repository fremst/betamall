package com.betamall.dto;

public class ItemDto {
	private int itemNo;
	private int mcatNo;
	private int scatNo;
	private String itemName;
	private String tImg;
	private String detImg;
	private String hash;
	private int price;
	private boolean itemDel;
	
	public ItemDto() {}

	public ItemDto(int itemNo, int mcatNo, int scatNo, String itemName, String tImg, String detImg, String hash,
			int price, boolean itemDel) {
		this.itemNo = itemNo;
		this.mcatNo = mcatNo;
		this.scatNo = scatNo;
		this.itemName = itemName;
		this.tImg = tImg;
		this.detImg = detImg;
		this.hash = hash;
		this.price = price;
		this.itemDel = itemDel;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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

	public String getDetImg() {
		return detImg;
	}

	public void setDetImg(String detImg) {
		this.detImg = detImg;
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

	public boolean isItemDel() {
		return itemDel;
	}

	public void setItemDel(boolean itemDel) {
		this.itemDel = itemDel;
	}

	@Override
	public String toString() {
		return "ItemDto [itemNo=" + itemNo + ", mcatNo=" + mcatNo + ", scatNo=" + scatNo + ", itemName=" + itemName
				+ ", tImg=" + tImg + ", detImg=" + detImg + ", hash=" + hash + ", price=" + price + ", itemDel="
				+ itemDel + "]\n";
	}
	
}
