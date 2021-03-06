package com.kisankrishibazar.dao;

import java.util.List;
import java.util.Map;

import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.FarmerOrderAvailable;
import com.kisankrishibazar.model.FarmerOrderInsert;
import com.kisankrishibazar.model.NegotiationDetails;
import com.kisankrishibazar.model.User;

public interface FarmerDAO {

	List<CommodityListBean> getCommodityList(String languageReq);

	boolean insertOrderAvailable(FarmerOrderInsert farmerOrderInsert);

	List<FarmerOrderAvailable> getOrderAvailable(String languageReq,String username);

	Map<String, String> getTranslation(String language);

	boolean isValidUser(String username);

	boolean deleteOrder(int id);
	
	List<NegotiationDetails> getNegotiationDetails(String languageReq,String username);
	
	boolean updateNegotiateOrder(int id,String status,int negotiatedPrice);
	
	int getNegotiateCount(String frmrUserName);

}
