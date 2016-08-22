package com.kisankrishibazar.dao;

import java.util.List;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.FarmerOrderAvailable;
import com.kisankrishibazar.model.FarmerOrderInsert;
import com.kisankrishibazar.model.User;

public interface FarmerDAO {

	List<CommodityListBean> getCommodityList(String languageReq);
	
	boolean insertOrderAvailable(FarmerOrderInsert farmerOrderInsert);
	
	FarmerOrderAvailable getOrderAvailable(String username);
	
	boolean deleteOrder(int id);

}
