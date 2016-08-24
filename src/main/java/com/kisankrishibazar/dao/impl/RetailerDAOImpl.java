package com.kisankrishibazar.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.RetailerDAO;
import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;
import com.kisankrishibazar.model.UserWithItem;

@Repository
public class RetailerDAOImpl implements RetailerDAO {
	@Inject
	private JdbcTemplate jdbcTemplate;

	public Boolean registerNewUser(User user) {
		String query = "INSERT INTO Login (UserName, Name , Password , Type , Lat, Longt, Address, Phone) VALUES (?,?,?,?,?,?,?,?)";
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.DOUBLE, Types.DOUBLE, Types.VARCHAR,
				Types.VARCHAR };
		int row = jdbcTemplate.update(
				query,
				new Object[] { user.getUsername(), user.getName(),
						user.getPassword(), user.getType(), user.getLat(),
						user.getLongt(), user.getAddress(), user.getPhone() },
				types);
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean saveOrderHistory(OrderHistory orderHistory) {
		String selectQuery = "select id from Commodity where English = ? ";
		int id = jdbcTemplate.queryForObject(selectQuery,
				new Object[] { orderHistory.getItem() }, Integer.class);
		String query = "INSERT INTO OrderSuccesfulHistory (FrmrUserName, RetailerUserName , Date , id , Price, Qty) VALUES (?,?,?,?,?,?)";
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DATE,
				Types.INTEGER, Types.FLOAT, Types.INTEGER };
		Calendar calendar = Calendar.getInstance();
		java.sql.Date startDate = new java.sql.Date(calendar.getTime()
				.getTime());
		int row = jdbcTemplate
				.update(query, new Object[] { orderHistory.getFrmrusername(),
						orderHistory.getRetailerusername(), startDate, id,
						orderHistory.getPrice(), orderHistory.getQty() }, types);
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserWithItem> getStock(String item, int quantity,
			float lat, float longitude) {
		List<UserWithItem> returnUserWithItem = new ArrayList<UserWithItem>();
		String sql = "select l.lat,l.longt,l.name,l.Address,l.Phone, o.username,o.quotedPrice,o.Qty from Commodity c, OrderAvailable o LEFT JOIN OrderSuccesfulHistory os ON( o.username != os.FrmrUserName) , login l where c.id=o.id and l.type='F' and l.username = o .userName  and  c.English = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] { item });

		for (Map row : rows) {
			UserWithItem userwithitem = new UserWithItem();
			userwithitem.setAddress((String) row.get("Address"));
			userwithitem.setQuantity((int) row.get("Qty"));
			if (null != row.get("quotedPrice")) userwithitem.setPrice((float) row.get("quotedPrice"));
			userwithitem.setLat((float) row.get("Lat"));
			userwithitem.setLongt((float) row.get("Longt"));
			userwithitem.setName((String) row.get("Name"));
			userwithitem.setPhone((String) row.get("Phone"));
			userwithitem.setUsername((String) row.get("UserName"));
			returnUserWithItem.add(userwithitem);
		}

		for (UserWithItem userinfo : returnUserWithItem) {
			haversine(userinfo.getUsername(), lat, userinfo.getLat(),
					longitude, userinfo.getLongt(), userinfo);
		}
		returnUserWithItem = sortByValues(returnUserWithItem);
		Double quantitySum = 0.0;
		List<UserWithItem> returnSortedUserWithItem = new ArrayList<UserWithItem>();
		for (UserWithItem returnUserWithItemEntry : returnUserWithItem) {
			if (quantitySum < quantity) {
				quantitySum = quantitySum
						+ returnUserWithItemEntry.getQuantity();
				returnSortedUserWithItem.add(returnUserWithItemEntry);
			}
			if (quantitySum >= quantity)
				break;

		}
		return returnSortedUserWithItem;

	}

	public static List<UserWithItem> sortByValues(
			List<UserWithItem> returnUserWithItem) {

		Collections.sort(returnUserWithItem, new Comparator<UserWithItem>() {

			@Override
			public int compare(UserWithItem o1, UserWithItem o2) {
				return o1.getDistance().compareTo(o2.getDistance());

			}

		});

		return returnUserWithItem;

	}

	public static void haversine(final String key, final double latreq,
			final double latnode, final double lonreq, final double lonnode,
			UserWithItem userinfo) {
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
					+ (Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math
							.cos(lat2));
			final double c = 2 * Math.asin(Math.sqrt(a));

			if ((latreq != 0.0) && (lonreq != 0.0)) {
				userinfo.setDistance(R * c * Miles);
			}

		}

	}

	public List<OrderHistory> getOrderHistory(String username) {

		List<OrderHistory> returnRetailerHistory = new ArrayList<OrderHistory>();
		String sql = "Select * from orderSuccesfulHistory where retailerUserName=?";
		List<Map<String, Object>>  map = jdbcTemplate.queryForList(sql,new Object[] {username});
		for(Map row : map){
			OrderHistory orderHistory = new OrderHistory();
			orderHistory.setDate((Date)row.get("Date"));
			orderHistory.setFrmrusername((String)row.get("FrmrUserName"));
			orderHistory.setPrice((float)row.get("Price"));
			orderHistory.setQty((int)row.get("Qty"));
			returnRetailerHistory.add(orderHistory);
		}
		return returnRetailerHistory;
	}

	public static final class OrderHistoryMapper implements
			RowMapper<OrderHistory> {
		@Override
		public OrderHistory mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			OrderHistory orderHistory = new OrderHistory();
			orderHistory.setFrmrusername(rs.getString("frmrusername"));
			orderHistory.setDate(rs.getDate("date"));
			orderHistory.setId(rs.getInt("id"));
			orderHistory.setPrice(rs.getFloat("price"));
			orderHistory.setQty(rs.getInt("qty"));
			return orderHistory;
		}
	}

	@Override
	public List<CommodityListBean> getCommodityList() {
		List<CommodityListBean> retCommodityListBeans = new ArrayList<CommodityListBean>();
		String commodityQuery = "SELECT English,Price,Quantity From Commodity c,Mcx m where m.id=c.id";
		List<Map<String, Object>> rows = jdbcTemplate
				.queryForList(commodityQuery);
		for (Map row : rows) {
			CommodityListBean commodityListBean = new CommodityListBean();
			commodityListBean.setItem((String) row.get("English"));
			commodityListBean.setPrice((float) row.get("Price"));
			commodityListBean.setQuantity((int) row.get("Quantity"));
			retCommodityListBeans.add(commodityListBean);
		}
		return retCommodityListBeans;
	}

	@Override
	public User getFarmerDetails(String username) {
		String sql = "SELECT * FROM Login where UserName = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username },
				new UserMapper());

	}

	public static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
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
