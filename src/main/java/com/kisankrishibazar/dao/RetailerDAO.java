package com.kisankrishibazar.dao;

import java.util.List;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;
import com.kisankrishibazar.model.UserDetailWithItem;

public interface RetailerDAO 
{
    public Boolean registerNewUser(User user);
    
    public List<UserDetailWithItem> getOrderAvailable(String item , int quantity , Double lat, Double longitude);
    
    public OrderHistory getOrderHistory(String username);
    
    public List<CommodityListBean> getCommodityList();
	
}
