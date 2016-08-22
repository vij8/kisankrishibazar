package com.kisankrishibazar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.RetailerDAO;
import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;
import com.kisankrishibazar.model.UserDetailWithItem;
import com.kisankrishibazar.model.UserWithItem;

@Repository
public class RetailerDAOImpl implements RetailerDAO
{
	@Inject
	private JdbcTemplate jdbcTemplate;

	public Boolean registerNewUser(User user)
	{
		String query = "INSERT INTO Login (UserName, Name , Password , Type , Lat, Longt, Address, Phone) VALUES (?,?,?,?,?,?,?,?)";
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DOUBLE,
				Types.DOUBLE, Types.VARCHAR, Types.VARCHAR };
		int row = jdbcTemplate.update(query, new Object[] { user.getUsername(), user.getName(), user.getPassword(),
				user.getType(), user.getLat(), user.getLongt(), user.getAddress(), user.getPhone() }, types);
		if (row > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public Boolean saveOrderHistory(OrderHistory orderHistory)
	{
		String selectQuery = "select id from Commodity where English = ? ";
		int id = jdbcTemplate.queryForObject(selectQuery,new Object[] {orderHistory.getItem()}, Integer.class);						
		String query = "INSERT INTO OrderSuccesfulHistory (FrmrUserName, RetailerUserName , Date , id , Price, Qty) VALUES (?,?,?,?,?,?)";
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.INTEGER, Types.FLOAT,Types.INTEGER};
		Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		int row = jdbcTemplate.update(query, new Object[] { orderHistory.getFrmrusername(),orderHistory.getRetailerusername(),startDate,id,orderHistory.getPrice(),orderHistory.getQty()}, types);
		if (row > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List<UserDetailWithItem> getOrderAvailable(String item, int quantity, Double lat, Double longitude)
	{
		String sql = "select  l.lat,l.longt,o.username from Commodity c, OrderAvailable o , login l where  c.id=o.id  and  l.username = o .userName  and c.English = ?";
		List<UserWithItem> rows = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserWithItem.class));
		Map<String, Double> distanceMapUser = new HashMap<String, Double>();
		for (UserWithItem userinfo : rows) {
			haversine(userinfo.getUserName(), lat, userinfo.getLat(), longitude, userinfo.getLongt(), distanceMapUser);
		}
		distanceMapUser = sortByValues(distanceMapUser);
		Double quantitySum = 0.0;
		List<String> userAvailableList = new ArrayList<String>();
		for (final Entry<String, Double> entry : distanceMapUser.entrySet()) {
			if (quantitySum < quantity) {
				quantitySum = quantitySum + entry.getValue();
				userAvailableList.add(entry.getKey());
			}
		}

		for (String userAvailable : userAvailableList) {
			String returnFarmerQuery = "select l.Name , l.Address , l.Phone , o.Price , o.Qty  From Login l, OrderAvailable o , Commodity c Where userName =? AND c.id = o.id AND English = ?";
			return jdbcTemplate.query(returnFarmerQuery, new Object[] { userAvailable, item },
					new BeanPropertyRowMapper(UserDetailWithItem.class));
		}

		return null;

	}

	public static void haversine(final String key, final Double latreq, final Double latnode, final double lonreq,
			final double lonnode, final Map<String, Double> sortedDistanceMap)
	{
		if ((latnode != 0.0) && (lonnode != 0.0)) {
			final double R = 6372.8; // In kilometers chane
			final double Miles = 0.621371;
			double lat1 = latreq;
			final double lon1 = lonreq;
			double lat2 = latnode;
			final double lon2 = lonnode;
			final double dLat = Math.toRadians(lat2 - lat1);
			final double dLon = Math.toRadians(lon2 - lon1);
			lat1 = Math.toRadians(lat1);
			lat2 = Math.toRadians(lat2);

			final double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2))
					+ (Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2));
			final double c = 2 * Math.asin(Math.sqrt(a));

			if ((latreq != 0.0) && (lonreq != 0.0)) {
				sortedDistanceMap.put(key, R * c * Miles);
			}

		}

	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(final Map<K, V> sortedDistanceMap)
	{
		final List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(sortedDistanceMap.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{

			@SuppressWarnings("unchecked")
			@Override
			public int compare(final Entry<K, V> o1, final Entry<K, V> o2)
			{
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		final Map<K, V> map = new LinkedHashMap<K, V>();

		for (final Map.Entry<K, V> entry : entries) {
			sortedDistanceMap.put(entry.getKey(), entry.getValue());
		}

		return map;
	}

	public OrderHistory getOrderHistory(String username)
	{

		String sql = "SELECT * FROM OrderSuccesfulHistory where retailerusername = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new OrderHistoryMapper());
	}

	public static final class OrderHistoryMapper implements RowMapper<OrderHistory>
	{
		@Override
		public OrderHistory mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			OrderHistory orderHistory = new OrderHistory();
			orderHistory.setFrmrusername(rs.getString("frmrusername"));
			orderHistory.setDate(rs.getDate("date"));
			orderHistory.setId(rs.getInt("id"));
			orderHistory.setPrice(rs.getFloat("price"));
			orderHistory.setQty(rs.getInt("qty"));
			return orderHistory;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityListBean> getCommodityList()
	{
		List<CommodityListBean> retCommodityListBeans = new ArrayList<CommodityListBean>();
		String commodityQuery = "SELECT English,Price,Quantity From Commodity c,Mcx m where m.id=c.id";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(commodityQuery);
		for(Map row : rows){
			CommodityListBean commodityListBean = new CommodityListBean();
			commodityListBean.setItem((String) row.get("English"));
			commodityListBean.setPrice((float) row.get("Price"));
			commodityListBean.setQuantity((int) row.get("Quantity"));
			retCommodityListBeans.add(commodityListBean);
		}
		return retCommodityListBeans;
	}

	@Override
	public User getFarmerDetails(String username)
	{
		String sql = "SELECT * FROM Login where UserName = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new UserMapper());

	}

	public static final class UserMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			User customer = new User();
			customer.setAddress(rs.getString("Address"));
			customer.setPhone(rs.getString("Phone"));
			customer.setLongt(rs.getDouble("Longt"));
			customer.setLat(rs.getDouble("Lat"));
			customer.setName(rs.getString("Name"));
			customer.setPassword(rs.getString("Password"));
			customer.setUsername(rs.getString("UserName"));
			customer.setType(rs.getString("Type"));
			return customer;
		}
	}
}
