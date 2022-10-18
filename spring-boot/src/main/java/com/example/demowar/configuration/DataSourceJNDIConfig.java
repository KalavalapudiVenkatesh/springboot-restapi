package com.example.demowar.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Component;


@Configuration
@PropertySource("classpath:hibernate.properties")
@ConditionalOnProperty(name = "spring.dbconnection", havingValue = "JNDI", matchIfMissing = true)
public class DataSourceJNDIConfig {
	

	
	private Properties props =hibernateProperties();
	

	@Primary
	@Bean("primary") 
	public DataSource primaryDs() {
		 JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		return lookup.getDataSource(props.getProperty("spring.datasource.db1.jndi-name"));
	}

	
	@Bean("secondary")
	public DataSource secondaryDs() {
		 JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		return lookup.getDataSource(props.getProperty("spring.datasource.db2.jndi-name"));
	}

	public static Properties hibernateProperties() {
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("hibernate.properties"));
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			return new Properties();
		}

	}
}
