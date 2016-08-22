package com.kisankrishibazar.model;

public class FarmerOrderInsert {
	private String language;
	
	private String username;
	
	private String item;	
	
	private float estimatedprice;
	
	private float quotedprice;
	
	private int qty;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public float getEstimatedprice() {
		return estimatedprice;
	}

	public void setEstimatedprice(float estimatedprice) {
		this.estimatedprice = estimatedprice;
	}

	public float getQuotedprice() {
		return quotedprice;
	}

	public void setQuotedprice(float quotedprice) {
		this.quotedprice = quotedprice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
