package com.kisankrishibazar.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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

}
