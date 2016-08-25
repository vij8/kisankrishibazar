package com.kisankrishibazar.model;

import com.google.gson.annotations.Expose;

public class UserWithItem {
	@Expose
	private String username;
	
	@Expose
	private int orderAvailableId;

	private float lat;

	private float longt;

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLongt() {
		return longt;
	}

	public void setLongt(float longt) {
		this.longt = longt;
	}
    @Expose
	private String name;
    @Expose
	private String address;
    @Expose
	private String phone;
    @Expose
	private Double distance;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Expose
	private float price;
	 @Expose
	private int quantity;

	public int getOrderAvailableId() {
		return orderAvailableId;
	}

	public void setOrderAvailableId(int orderAvailableId) {
		this.orderAvailableId = orderAvailableId;
	}

}
