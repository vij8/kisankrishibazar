package com.kisankrishibazar.dao;

import java.util.List;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;

public interface RetailerDAO 
{
    public Boolean registerNewUser(User user);
    
    public OrderAvailable getOrderAvailable(OrderAvailable orderAvailable);
    
    public OrderHistory getOrderHistory(String username);
    
    public List<CommodityListBean> getCommodityList();
	
}
