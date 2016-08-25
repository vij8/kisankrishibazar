package com.kisankrishibazar.dao;

import java.util.List;
import java.util.Map;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.NegotiationDetails;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;
import com.kisankrishibazar.model.UserWithItem;

public interface RetailerDAO 
{
    public Boolean registerNewUser(User user);
    
    public List<UserWithItem> getStock(String item , int quantity , float lat, float longitude);
    
    public  List<OrderHistory> getOrderHistory(String username);
    
    public User getFarmerDetails(String username);
    
    public List<CommodityListBean> getCommodityList();
    
    public Boolean saveOrderHistory(OrderHistory orderHistory);
    
    public Boolean saveNegotiationDetails(NegotiationDetails negotiationDetails);
	
}
