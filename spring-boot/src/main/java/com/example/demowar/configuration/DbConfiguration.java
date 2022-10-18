/**
 * 
 */
package com.example.demowar.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:hibernate.properties")
public class DbConfiguration {

	@Autowired
	@Qualifier("primary")
	private DataSource primary;

	@Autowired
	@Qualifier("secondary")
	private DataSource secondary;

	private Properties props = hibernateProperties();

	// =========================primary start ====================
	@Primary
	@Bean(name = "primarysession")
	public LocalSessionFactoryBean primarySessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(primary);
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Primary
	@Bean(name = "primaryTransactionManager")
	public HibernateTransactionManager primaryTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(primarySessionFactory().getObject());
		return txManager;
	}

//	//=========================secondary start ====================

	@Bean(name = "secondarysession")
	public LocalSessionFactoryBean secondarySessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(secondary);
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}


	@Bean(name = "secondaryTransactionManager")
	public HibernateTransactionManager secondaryTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(secondarySessionFactory().getObject());
		return txManager;
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
