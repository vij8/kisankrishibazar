package com.kisankrishibazar.dao;

import javax.sql.DataSource;

public interface DataSourceConfiguration
{
	 DataSource dataSource() throws Exception;

}
	