package com.kisankrishibazar.dao.impl;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kisankrishibazar.dao.DataSourceConfiguration;

@Configuration
@Profile("default")
public class LocalDataSourceConfiguration implements DataSourceConfiguration
{

	@Bean
	public DataSource dataSource() throws Exception
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setPassword("admin");
		dataSource.setUrl("jdbc:mysql://localhost:3306/krishi?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

}
