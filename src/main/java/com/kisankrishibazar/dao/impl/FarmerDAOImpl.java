package com.kisankrishibazar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.FarmerDAO;
import com.kisankrishibazar.dao.RetailerDAO;
import com.kisankrishibazar.model.CommodityListBean;
import com.kisankrishibazar.model.OrderAvailable;
import com.kisankrishibazar.model.OrderHistory;
import com.kisankrishibazar.model.User;

@Repository
public class FarmerDAOImpl implements FarmerDAO {
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CommodityListBean> getCommodityList(String languageReq) {
		List<CommodityListBean> returnCommodityListBean = new ArrayList<CommodityListBean>();

		String commodityQuery = "Select '"+ languageReq +"' ,Quantity,Price From translation tr, Commodity cd,MCX mc where cd.English=tr.English AND cd.id=mc.id";
		return jdbcTemplate.query(commodityQuery,new BeanPropertyRowMapper(CommodityListBean.class));
	}
	

}
