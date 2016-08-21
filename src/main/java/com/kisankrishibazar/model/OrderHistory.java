package com.kisankrishibazar.model;

import java.sql.Date;

public class OrderHistory
{

	private String frmrusername;

	private String retailerusername;

	private Date date;

	private int id;

	private float price;

	private int qty;

	private String name;

	private String address;

	private String phone;

	public String getFrmrusername()
	{
		return frmrusername;
	}

	public void setFrmrusername(String frmrusername)
	{
		this.frmrusername = frmrusername;
	}

	public String getRetailerusername()
	{
		return retailerusername;
	}

	public void setRetailerusername(String retailerusername)
	{
		this.retailerusername = retailerusername;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

}
