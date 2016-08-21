package com.kisankrishibazar.dao.impl;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kisankrishibazar.dao.DataSourceConfiguration;

@Configuration
@EnableTransactionManagement
@Import({ LocalDataSourceConfiguration.class })
public class JdbcConfiguration
{
	@Inject
	private DataSourceConfiguration dataSourceConfiguration;

	@Bean
	public JdbcTemplate setupJdbcTemplate() throws Exception
	{
		return new JdbcTemplate(dataSourceConfiguration.dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception
	{
		return new DataSourceTransactionManager(dataSource);
	}

}
