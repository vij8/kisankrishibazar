package com.kisankrishibazar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

	public OrderAvailable getOrderAvailable(OrderAvailable orderAvailableDetails) {
		String sql = "SELECT * FROM OrderAvailable where UserName = ? AND Password = ?";
		// return jdbcTemplate.queryForObject(sql, new Object[] { userName,
		// Password }, new UserMapper());
		return null;

	}

	public OrderHistory getOrderHistory(String username) {

		String sql = "SELECT * FROM OrderSuccesfulHistory where userName = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username },
				new OrderHistoryMapper());
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
		List<CommodityListBean> returnCommodityListBean = new ArrayList<CommodityListBean>();
		String commodityQuery = "SELECT English,Price,Quantity From Commodity c,Mcx m where m.id=c.id";
		return jdbcTemplate.query(commodityQuery,
				new BeanPropertyRowMapper(CommodityListBean.class));
	}
}
