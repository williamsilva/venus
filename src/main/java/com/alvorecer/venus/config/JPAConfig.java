package com.alvorecer.venus.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alvorecer.venus.model.Attendance;
import com.alvorecer.venus.repository.Attendances;

@Configuration
@EnableJpaRepositories(basePackageClasses = Attendances.class, enableDefaultTransactions = false)
@ComponentScan(basePackageClasses = Attendances.class)
@EnableTransactionManagement
public class JPAConfig {

//	@Profile("local")
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/venusDB");
	}

//	@Profile("prodHeroku")
//	@Bean
//	public DataSource dataSourceProd() throws URISyntaxException {
//		URI jdbUri = new URI(System.getenv("JAWSDB_URL"));
//
//	    String username = jdbUri.getUserInfo().split(":")[0];
//	    String password = jdbUri.getUserInfo().split(":")[1];
//	    String port = String.valueOf(jdbUri.getPort());
//	    String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
//	    
//	    BasicDataSource dataSource = new BasicDataSource();
//	    dataSource.setUrl(jdbUrl);
//	    dataSource.setUsername(username);
//	    dataSource.setPassword(password);
//	    dataSource.setInitialSize(10);
//	    return dataSource;
//	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Attendance.class.getPackage().getName());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
