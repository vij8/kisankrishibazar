package com.kisankrishibazar.model;

import java.sql.Date;

public class FarmerOrderAvailable {
	
	int orderId;
	
	String item ;
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	float estimatedprice;
	
	float quotedprice;
	
	Date date;
	
	int qty;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
