package com.kisankrishibazar.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.FarmerDAO;
import com.kisankrishibazar.dao.impl.LoginDAOImpl.UserMapper;
import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.FarmerOrderAvailable;
import com.kisankrishibazar.model.FarmerOrderInsert;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;

@Repository
public class FarmerDAOImpl implements FarmerDAO {
	@Inject
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CommodityListBean> getCommodityList(String languageReq) {

		List<CommodityListBean> returnCommodityListBean = new ArrayList<CommodityListBean>();
		String commodityQuery = "Select tr."
				+ languageReq
				+ " , cd.Quantity, mc.Price From translation tr, Commodity cd,MCX mc where cd.English=tr.English AND cd.id=mc.id";

		List<Map<String, Object>> rows = jdbcTemplate
				.queryForList(commodityQuery);
		for (Map row : rows) {
			CommodityListBean commodityBean = new CommodityListBean();
			commodityBean.setItem((String) row.get(languageReq));
			commodityBean.setQuantity((int) row.get("Quantity"));
			commodityBean.setPrice((float) row.get("Price"));
			returnCommodityListBean.add(commodityBean);
		}
		return returnCommodityListBean;
	}

	public boolean insertOrderAvailable(FarmerOrderInsert farmerOrderInsert) {
		String selectQuery = "select c.id from translation t,commodity c where c.English=t.English and t."
				+ farmerOrderInsert.getLanguage() + "= ?";
		int id = jdbcTemplate.queryForObject(selectQuery,
				new Object[] { farmerOrderInsert.getItem() }, Integer.class);
		String query = "INSERT INTO OrderAvailable (UserName, id , estimatedPrice , quotedPrice , Date, Qty) VALUES (?,?,?,?,?,?)";
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.FLOAT,
				Types.FLOAT, Types.DATE, Types.INTEGER };
		Calendar calendar = Calendar.getInstance();
		java.sql.Date startDate = new java.sql.Date(calendar.getTime()
				.getTime());
		int row = jdbcTemplate.update(query,
				new Object[] { farmerOrderInsert.getUsername(), id,
						farmerOrderInsert.getEstimatedprice(),
						farmerOrderInsert.getQuotedprice(), startDate,
						farmerOrderInsert.getQty() }, types);
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteOrder(int orderId) {

		String query = "Delete from OrderAvailable where orderAvailableId = ?";
		int[] types = new int[] { Types.INTEGER };
		int row = jdbcTemplate.update(query, new Object[] { orderId }, types);
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<FarmerOrderAvailable> getOrderAvailable(String languageReq,
			String username) {
		String getOrderQuery = "SELECT o.EstimatedPrice,o.OrderAvailableId,tr.? ,o.quotedPrice , o.Date, o.Qty FROM orderAvailable o,Translation tr,Commodity co where UserName = ? and o.id=co.id and co.English = tr.English";
		// return jdbcTemplate.queryForObject(sql, new Object[] {
		// languageReq,username },
		// new OrderMapper());
		List<FarmerOrderAvailable> returnFarmerOrderAvailable = new ArrayList<FarmerOrderAvailable>();
		List<Map<String, Object>> rows = jdbcTemplate
				.queryForList(getOrderQuery);
		for(Map row :rows){
			FarmerOrderAvailable farmerOrderAvailable = new FarmerOrderAvailable();
			farmerOrderAvailable.setOrderId((int) row.get("orderId"));
			farmerOrderAvailable.setEstimatedprice((float) row.get("estimatedprice"));
			farmerOrderAvailable.setQuotedprice((float) row.get("quotedprice"));
			farmerOrderAvailable.setDate((Date) row.get("date"));
			farmerOrderAvailable.setQty((int) row.get("qty"));
			farmerOrderAvailable.setId((int) row.get("id"));
			returnFarmerOrderAvailable.add(farmerOrderAvailable);
		}
		return null;
	}

	@Override
	public Map<String, String> getTranslation(String language) {

		String getTranslationQuery = "Select Label," + language
				+ " from Translation";
		List<Map<String, Object>> map = jdbcTemplate
				.queryForList(getTranslationQuery);
		Map<String, String> newMap = new HashMap<String, String>();
		for (Map row : map) {
			newMap.put((String) row.get("Label"), (String) row.get(language));

		}
		return newMap;
	}

	@Override
	public boolean isValidUser(String username) {
		boolean userAlreadyExists = false;
		String validateUserQuery = "Select name from login where username = ?";
		String name = jdbcTemplate.queryForObject(validateUserQuery,
				new Object[] { username }, String.class);
		if (null != name) {
			userAlreadyExists = true;
		}
		return userAlreadyExists;
	}
}
