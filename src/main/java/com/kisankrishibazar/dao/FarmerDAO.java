package com.kisankrishibazar.dao;

import java.util.List;
import java.util.Map;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.FarmerOrderAvailable;
import com.kisankrishibazar.model.FarmerOrderInsert;
import com.kisankrishibazar.model.User;

public interface FarmerDAO {

	List<CommodityListBean> getCommodityList(String languageReq);
	
	boolean insertOrderAvailable(FarmerOrderInsert farmerOrderInsert);
	
	FarmerOrderAvailable getOrderAvailable(String username);
	
	Map<String,String> getTranslation(String language);
	
	boolean deleteOrder(int id);

}
