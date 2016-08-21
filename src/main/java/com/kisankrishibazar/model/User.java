package com.kisankrishibazar.model;

import java.util.regex.Pattern;

import spark.utils.StringUtils;

public class User
{

	private String username;

	private String name;

	private Double lat;

	private Double longt;

	private String phone;

	private String address;

	private String type;

	private String password;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String validate()
	{
		String error = null;

		if (StringUtils.isEmpty(username)) {
			error = "You have to enter a username";
		}
		else if (StringUtils.isEmpty(password)) {
			error = "You have to enter a password";
		}
			return error;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Double getLat()
	{
		return lat;
	}

	public void setLat(Double lat)
	{
		this.lat = lat;
	}

	public Double getLongt()
	{
		return longt;
	}

	public void setLongt(Double longt)
	{
		this.longt = longt;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}
