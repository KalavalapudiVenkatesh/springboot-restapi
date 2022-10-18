package com.example.demowar.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:hibernate.properties")
@ConditionalOnProperty(name = "spring.dbconnection", havingValue = "Native", matchIfMissing = true)
public class DataSourceNATIVEConfig {

	

	@Primary
	@Bean(name = "primary")
	@ConfigurationProperties(prefix = "db1")
	public DataSource primaryDs() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "secondary")
	@ConfigurationProperties(prefix = "db2")
	public DataSource secondaryDs() {
		return DataSourceBuilder.create().build();
	}

	
}
