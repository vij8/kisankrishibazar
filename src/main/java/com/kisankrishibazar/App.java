package com.kisankrishibazar;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.kisankrishibazar.config.WebConfig;
import com.kisankrishibazar.dao.LoginDAO;
import com.kisankrishibazar.dao.impl.JdbcConfiguration;
import com.kisankrishibazar.services.impl.KisankrishiServices;

@Configuration
@ComponentScan({ "com.kisankrishibazar" })
public class App
{

	public static void main(String[] args) throws SQLException
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
		Map<String, Object> bean = new HashMap<String, Object>();
		ctx.getEnvironment().setActiveProfiles("default");
		ctx.scan(JdbcConfiguration.class.getPackage().getName());		
		bean.put("loginDao", ctx.getBean(LoginDAO.class));
		new WebConfig(ctx.getBean(KisankrishiServices.class), bean);
		ctx.registerShutdownHook();
		
	}

}
