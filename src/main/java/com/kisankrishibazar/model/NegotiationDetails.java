package com.kisankrishibazar.model;

public class NegotiationDetails {

	private int orderId;
	
	private float estimatedPrice;
	
	private float negotiationPrice;
	
	private float quotedPrice;
	
	private String frmrUserName;
	
	private String retailerUserName;
	
	private String item;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public float getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(float estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public float getNegotiationPrice() {
		return negotiationPrice;
	}

	public void setNegotiationPrice(float negotiationPrice) {
		this.negotiationPrice = negotiationPrice;
	}

	public float getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(float quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public String getFrmrUserName() {
		return frmrUserName;
	}

	public void setFrmrUserName(String frmrUserName) {
		this.frmrUserName = frmrUserName;
	}

	public String getRetailerUserName() {
		return retailerUserName;
	}

	public void setRetailerUserName(String retailerUserName) {
		this.retailerUserName = retailerUserName;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	
	
	
	
	
	
}
