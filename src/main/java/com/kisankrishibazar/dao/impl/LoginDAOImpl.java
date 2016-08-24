package com.kisankrishibazar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.LoginDAO;
import com.kisankrishibazar.model.User;

@Repository
public class LoginDAOImpl implements LoginDAO
{

	@Inject
	private JdbcTemplate  jdbcTemplate;

	@Override
	public User getUserDetail(String userName, String Password)
	{
		String sql = "SELECT * FROM Login where UserName = ? AND Password = ? AND Type='R' ";
		return jdbcTemplate.queryForObject(sql, new Object[] { userName, Password }, new UserMapper());

	}

	public User getFarmerUserDetail(String userName, String Password)
	{
		String sql = "SELECT * FROM Login where UserName = ? AND Password = ? AND Type='F' ";
		return jdbcTemplate.queryForObject(sql, new Object[] { userName, Password }, new UserMapper());

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
			customer.setUsername(rs.getString("UserName"));
			customer.setType(rs.getString("Type"));
			return customer;
		}
	}

}
