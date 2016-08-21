package com.kisankrishibazar.dao;

import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;

public interface RetailerDAO 
{
    public Boolean registerNewUser(User user);
    
    public OrderAvailable getOrderAvailable(OrderAvailable orderAvailable);
    
    public OrderHistory getOrderHistory(String username);
	
}
