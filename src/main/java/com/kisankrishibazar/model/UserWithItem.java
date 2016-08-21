package com.kisankrishibazar.model;

public class UserWithItem {
	
	private String UserName;
	
	private Double lat;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLongt() {
		return longt;
	}

	public void setLongt(Double longt) {
		this.longt = longt;
	}

	private Double longt;
}
