package com.kisankrishibazar.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kisankrishibazar.dao.FarmerDAO;
import com.kisankrishibazar.model.CommodityListBean;

@Repository
public class FarmerDAOImpl implements FarmerDAO {
	@Inject
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CommodityListBean> getCommodityList(String languageReq) {
		

		String commodityQuery = "Select tr."+languageReq+" , cd.Quantity, mc.Price From translation tr, Commodity cd,MCX mc where cd.English=tr.English AND cd.id=mc.id";
		return jdbcTemplate.query(commodityQuery,new BeanPropertyRowMapper(CommodityListBean.class));
	}
	

}
