package com.betamall.dto;

public class OrderSheet {

	private int ordNo;
	private String brName;
	private int itemNo;
	private String itemName;
	private int price;
	private int ordCnt;
	
	public OrderSheet() {}

	public OrderSheet(int ordNo, String brName, int itemNo, String itemName, int price, int ordCnt) {
		this.ordNo = ordNo;
		this.brName = brName;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.price = price;
		this.ordCnt = ordCnt;
	}

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	@Override
	public String toString() {
		return "OrderSheet [ordNo=" + ordNo + ", brName=" + brName + ", itemNo=" + itemNo + ", itemName=" + itemName
				+ ", price=" + price + ", ordCnt=" + ordCnt + "]\n";
	}
	
}
